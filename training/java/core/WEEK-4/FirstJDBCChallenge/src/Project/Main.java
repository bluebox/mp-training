package Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.time.LocalDate;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {
	public static void insertData(Connection connection, Statement statement, String databaseName) throws SQLException {
		
		String sqlOrder="insert into %s(ORDERDATE) values(?)".formatted(databaseName);
		String sqlDetails="insert into shop.order_details(ORDERID,PRODUCTNAME,PRODUCTPRICE,PRODUCTDESCRIPTION) values(?,?,?,?)";
		PreparedStatement psOrder=connection.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
		PreparedStatement psDetails=connection.prepareStatement(sqlDetails);
		String[] productNames={"Pen", "Pencil", "Scale"};
		double[] productPrices= {5.45, 3.45, 10.25};
		String[] productDescriptions={"This is very good pen", "This is very good pencil", "This is very good scale"};
		for(int i=0; i<3; i++) {
			psOrder.setDate(1, Date.valueOf(LocalDate.now()));
			psOrder.executeUpdate();
			ResultSet rOrder=psOrder.getGeneratedKeys();
			int key=i;
			if(rOrder.next()) {
				key=rOrder.getInt(1);
			}
			psDetails.setInt(1, key);
			psDetails.setString(2, productNames[i]);
			psDetails.setDouble(3, productPrices[i]);
			psDetails.setString(4, productDescriptions[i]);
			psDetails.executeUpdate();
		}
	}
	
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
		if(!flag) {
			createSchema(connection, statement, databaseName);
		}
	}
	
	public static void createSchema(Connection connection, Statement statement, String databaseName) {
		try {
			String query1="create table %s(ORDERID int auto_increment primary key, ORDERDATE date)".formatted(databaseName);
			statement.execute(query1);
			String query2="create table shop.order_details(ORDERID int, PRODUCTNAME varchar(30), PRODUCTPRICE DOUBLE, PRODUCTDESCRIPTION varchar(50), constraint fk_orders foreign key(ORDERID) references shop.order(ORDERID) on delete cascade)";
			statement.execute(query2);
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public static void deleteRows(Connection connection, Statement statement, String databaseName) throws SQLException {
		String query1="delete from %s where orderid in (1,2)".formatted(databaseName);
		statement.executeUpdate(query1);
//		String query2="delete from shop.order_details where orderid in (1,2)".formatted(databaseName);
//		statement.executeUpdate(query2);
	}
	
	public static void main(String [] args) {
		var dataSource=new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306");
		dataSource.setUser("root");
		dataSource.setPassword(System.getenv("USER_PASSWORD"));
		try(Connection connection=dataSource.getConnection();
			Statement statement=connection.createStatement()){
			connection.setAutoCommit(false);
			selectAllRows(connection, statement, "shop.order");
			insertData(connection, statement, "shop.order");
			selectAllRows(connection, statement, "shop.order");
			deleteRows(connection, statement, "shop.order");
			selectAllRows(connection, statement, "shop.order");
		}catch(SQLException e) {
			System.err.println(e.getSQLState());
			System.err.println(e.getErrorCode());
			System.err.println(e.getMessage());
		}
	}
}
