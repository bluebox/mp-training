package Project;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Arrays;

import com.mysql.cj.jdbc.MysqlDataSource;



public class Main {
	
	public static void selectAllRows(Connection connection, Statement statement, String databaseName) {
		try {
			String query1="select * from %s".formatted(databaseName);
			String query2="select * from shop.order_details";
			ResultSet resultSet1=statement.getResultSet();
			ResultSet resultSet2=statement.getResultSet();
			System.out.printf("%7s %-10s %n","ORDERID", "ORDERDATE");
			
			while(resultSet1.next()) {
				int orderId=resultSet1.getInt(1);
				LocalDate orderDate=resultSet1.getDate(2).toLocalDate();
				System.out.printf("%d %-10s %n",orderId, String.valueOf(orderDate));
			}
			
			System.out.printf("%7s %-15s %5s %-35s %n","ORDERID", "PRODUCTNAME", "PRODUCTPRICE", "PRODUCTDESCRIPTION");
			
			while(resultSet2.next()) {
				int orderId=resultSet2.getInt(1);
				String productName=resultSet2.getString(2);
				double productPrice=resultSet2.getDouble(3);
				String productDescription=resultSet2.getString(4);
				System.out.printf("%d %-15s %s %-35s%n",orderId, productName, String.valueOf(productPrice), productDescription);
			}

		}catch(SQLException e) {
			System.err.println(e.getErrorCode());
			System.err.println(e.getMessage());
			
		}
	}
	
	public static List<Order> readFile(Path path) {
		List<Order> orderList=new ArrayList<>();
		try(Scanner sc=new Scanner(path)){
			sc.useDelimiter("\n");
			sc.tokens()
				.map(String::strip)
				.map(s -> Arrays.asList(s.split(",")))
				.collect(Collectors.groupingBy(s -> s.get(0), Collectors.mapping(l -> l, Collectors.toList())))
				.forEach((k,v) -> {
//					OrderDetails orderDetails=new OrderDetails(l.get(1), Double.parseDouble(l.get(2)), Integer.parseInt(l.get(3)));
//					Order order=new Order(LocalDate.parse(l.get(0),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),orderDetails);
//					orderList.add(order);
					List<OrderDetails> orderDetails=new ArrayList<>();
					for(var orderDetail:v) {
						OrderDetails orderDetailObj=new OrderDetails(orderDetail.get(1), Integer.parseInt(orderDetail.get(2)));
						orderDetails.add(orderDetailObj);
					}
					Order order=new Order(LocalDate.parse(k,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),orderDetails);
					orderList.add(order);
				});
		}catch(IOException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public static void insertOrders(Connection conn, Statement statement, List<Order> orderList) throws SQLException {
		String inOrder="insert into shop.order(orderdate) values(?)";
		String inDetails="insert into shop.order_details(orderid,productname,productquantity) values(?,?,?)";
		try(PreparedStatement ps1=conn.prepareStatement(inOrder,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2=conn.prepareStatement(inDetails)){
			conn.setAutoCommit(false);
			for(Order order:orderList) {
				ps1.setDate(1, Date.valueOf(order.getDate()));
				ps1.addBatch();
			}
			int [] countArr=ps1.executeBatch();
			ResultSet keys=ps1.getGeneratedKeys();
			int orderIndex=0;
			while(keys.next()) {
				int key=keys.getInt(1);
				List<OrderDetails> orderDetailsList=orderList.get(orderIndex).getOrderDetails();
				for(int i=0; i<orderDetailsList.size(); i++) {
					ps2.setInt(1, key);
					ps2.setString(2, orderDetailsList.get(i).getProductName());
					ps2.setInt(3, orderDetailsList.get(i).getQuantity());
					ps2.addBatch();
				}
				countArr=ps2.executeBatch();
			}
			conn.commit();
//			int totalOrdersInserted=Arrays.stream(countArr).reduce(0,(a,b) -> a+b);
		}catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
		
	}
	
	public static void printRecords(Connection conn, Statement statement) throws SQLException {
		String query1="select * from shop.order";
		String query2="select * from shop.order_details";
		System.out.println("Orders :");
		ResultSet rs1=statement.executeQuery(query1);
		System.out.println("ORDERID			ORDERDATE");
		while(rs1.next()) {
			System.out.println(rs1.getInt(1)+"			"+rs1.getDate(2));
		}
		
		ResultSet rs2=statement.executeQuery(query2);
		System.out.println("ORDERID			PRODUCTNAME		PRODUCTQUANTITY");
		while(rs2.next()) {
			System.out.println(rs2.getInt(1)+"			"+rs2.getString(2)+"			"+rs2.getInt(3));
		}
	}
	
	public static void main(String [] args) {
		List<Order> orderList=readFile(Path.of("input.csv"));
		var dataSource=new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306");
		dataSource.setUser("root");
		dataSource.setPassword(System.getenv("USER_PASSWORD"));
		try(Connection conn=dataSource.getConnection();
			Statement statement=conn.createStatement()){
			insertOrders(conn,statement,orderList);
			printRecords(conn,statement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
