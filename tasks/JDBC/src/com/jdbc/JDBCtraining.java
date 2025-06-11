package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class JDBCtraining {
	
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/EmployeeSchema";
		String username = "devuser";
		String password = "Medplus@123";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			
//			Statement statement = connection.createStatement();
			
//			statement.execute("create table employeeDetails(id int, age int, salary decimal(5,2))");
			
//			PreparedStatement preparedStatement = connection.prepareStatement("Insert into employeeDetails(id,age,salary) values(1,21,925)");
//			preparedStatement.execute();
			
//			PreparedStatement preparedStatement1 = connection.prepareStatement("delete from employeeDetails where id = ?");
//			preparedStatement1.setInt(1, 1);
//			preparedStatement1.execute();
			
			PreparedStatement preparedStatement = connection.prepareStatement("alter table employeeDetails modify column salary decimal(7,2)");
			System.out.println(preparedStatement.executeUpdate());
			
			PreparedStatement preparedStatement1 = connection.prepareStatement("Select * from Employee join employeeDetails"); 
			ResultSet resultset = preparedStatement1.executeQuery();
			
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
			while(resultset.next()) {
				
				System.out.println("id: "+resultset.getInt("id") + " name: "+resultset.getString("name") + 
						" age: "+resultset.getInt("age") + " salary: "+resultset.getInt("salary"));
				
			}
			
//			statement.execute("drop table employeeDetails");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("Exception in SQL");
			e.printStackTrace();
		}
	}
}
