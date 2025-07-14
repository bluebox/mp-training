package dev.tulasidhar.july14.jdbcStoreFront;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStoreFront {
	public static void main(String[] args) {
		//create connection
		//create database storefront
		//create tables order and order details
		//insert basic data (first order and then order details)
		//now delete the single order and that should delete the corresponding order details (order id is foreign Key
		
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "root@pokemon";
		
		try(Connection con = DriverManager.getConnection(url,user,password)){
			//st.execute("Create Database StoreFront");
			//createTablesWithCascade(con);
			//createTablesWithoutCascade(con);
			//insertDataIntoTables(con);
			deleteOrder(con,1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	


	private static void createTablesWithCascade(Connection con) throws SQLException {
		Statement st = con.createStatement();
		st.execute("""
				Create table StoreFront.orders (orderId int PRIMARY KEY,
												orderDate DateTime);
				""");
		
		st.execute("""
				Create table StoreFront.orderDetails (orderDetailId int AUTO_INCREMENT PRIMARY KEY,
														orderId int,
														orderDesc VARCHAR(40),	
														FOREIGN KEY(orderId) REFERENCES orders(orderId)
														ON DELETE CASCADE
													);
				""");
		
		System.out.println("succesfully created the tables");
	}
	
	private static void createTablesWithoutCascade(Connection con) throws SQLException {
		Statement st = con.createStatement();
		st.execute("""
				Create table StoreFront.orders (orderId int PRIMARY KEY,
												orderDate DateTime);
				""");
		
		st.execute("""
				Create table StoreFront.orderDetails (orderDetailId int AUTO_INCREMENT PRIMARY KEY,
														orderId int,
														orderDesc VARCHAR(40),	
														FOREIGN KEY(orderId) REFERENCES orders(orderId)
													);
				""");
		
		System.out.println("succesfully created the tables");
	}
	
	private static void insertDataIntoTables(Connection con) throws SQLException{
		PreparedStatement prepStatement= con.prepareStatement("INSERT INTO StoreFront.orders(orderId , orderDate) Values (?,?)");
		prepStatement.setInt(1, 1);
		prepStatement.setString(2,"2025-04-20 20:32:56");
		prepStatement.execute();
		
		PreparedStatement orderDetails = con.prepareStatement("INSERT INTO StoreFront.orderDetails(orderId , orderDesc) VALUES (?,?)");
		orderDetails.setInt(1,1);
		orderDetails.setString(2,"Cookies and cream");
		orderDetails.execute();
		
		orderDetails.setInt(1,1);
		orderDetails.setString(2,"MilkShake");
		orderDetails.execute();
		
		orderDetails.setInt(1,1);
		orderDetails.setString(2,"Biryani Rice");
		orderDetails.execute();
	}
	
	private static void deleteOrder(Connection con, int id) throws SQLException {
		PreparedStatement deleteChildren= con.prepareStatement("DELETE FROM StoreFront.orderDetails WHERE orderId = ?");
		deleteChildren.setInt(1, id);
		deleteChildren.execute();
		
		PreparedStatement prepStatement= con.prepareStatement("DELETE FROM StoreFront.orders WHERE orderId = ?");
		prepStatement.setInt(1, id);
		prepStatement.execute();
	}
}
