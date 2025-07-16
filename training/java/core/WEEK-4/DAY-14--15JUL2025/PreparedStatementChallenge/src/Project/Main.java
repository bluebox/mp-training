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
		boolean flag;
		try {
			String query1="select * from %s".formatted(databaseName);
			String query2="select * from shop.order_details";
			flag=statement.execute(query1);
			flag=statement.execute(query2);
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
			flag=false;
			
		}
	}
	
	public static List<Order> readFile(Path path) {
		List<Order> orderList=new ArrayList<>();
		try(Scanner sc=new Scanner(path)){
			sc.useDelimiter("\n");
			sc.tokens()
				.map(String::strip)
				.map(s -> Arrays.asList(s.split(",")))
				.forEach(l -> {
					OrderDetails orderDetails=new OrderDetails(l.get(1), Integer.parseInt(l.get(2)));
					Order order=new Order(LocalDate.parse(l.get(0),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),orderDetails);
					orderList.add(order);
				});
		}catch(IOException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public static int insertOrders(Connection conn, Statement statement, List<Order> orderList) {
		String inOrder="insert into shop.order(orderdate) values(?)";
		String inDetails="insert into shop.order_details(orderid,productname,productprice,productdescription) values(?,?,?,?)";
		try(PreparedStatement ps1=conn.prepareStatement(inOrder,Statement.RETURN_GENERATED_KEYS);
			PreparedStatement ps2=conn.prepareStatement(inDetails)){
			for(Order order:orderList) {
				ps1.setDate(1, Date.valueOf(order.getDate()));
				ps1.addBatch();
			}
			int [] countArr=ps1.executeBatch();
			ResultSet keys=ps1.getGeneratedKeys();
			
			int totalOrdersInserted=Arrays.stream(countArr).reduce(0,(a,b) -> a+b);
		}catch(SQLException e) {
			e.printStackTrace();
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
			conn.setAutoCommit(false);
			int totalOrdersInserted=insertOrders(conn,statement,orderList);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
