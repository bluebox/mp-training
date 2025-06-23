package com.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFirstChallenge {
	
	public static void main(String[] args) {
		
		 String url = "jdbc:mysql://localhost:3306/EmployeeSchema";
		 String username = "devuser";
		 String password = "Medplus@123";
		 
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(url, username, password); 
	            Statement statement = connection.createStatement(); 
	           
	            ResultSet resultSet1 = statement.executeQuery("select * from Employee e where e.name = 'sri' ");
	            
	            while( resultSet1.next()) {
	            	System.out.println("ID: " + resultSet1.getInt("id") + ",  Name: " + resultSet1.getString("name"));
	            }
	            
	            
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee"); 

	            while (resultSet.next()) {
	                System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name")); 
	            }
	            
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } catch (ClassNotFoundException e) {
	            System.err.println("JDBC Driver not found: " + e.getMessage());
	        } catch (SQLException e) {
	            System.err.println("SQL Error: " + e.getMessage());
	        }
	}
}

