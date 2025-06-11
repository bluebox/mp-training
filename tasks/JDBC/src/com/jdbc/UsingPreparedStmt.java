package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsingPreparedStmt {

	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/EmployeeSchema";
		String username = "devuser";
		String password = "Medplus@123";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,username,password);
			
			Statement statement = connection.createStatement();
			statement.executeQuery("select * from Employee");
			
			String query1 = "select * from Employee a where a.name like ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setString(1, "s%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			 while (resultSet.next()) {
	                System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name")); 
	            }
	            
		} 
		catch (ClassNotFoundException e) {
			System.out.println("JDBc error not found");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
