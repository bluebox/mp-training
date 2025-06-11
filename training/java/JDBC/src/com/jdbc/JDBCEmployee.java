package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCEmployee {
	
	public static void main(String[] args) {
		
		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee(2,21,20000));
		list.add(new Employee(3,23,25000));
		list.add(new Employee(4,26,32000));
		list.add(new Employee(5,22,40000));
		list.add(new Employee(6,25,15000));
		
		String url = "jdbc:mysql://localhost:3306/EmployeeSchema";
		String username = "devuser";
		String password = "Medplus@123";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			Statement statement = connection.createStatement();
			statement.execute("select * from employeeDetails ");
			
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employeeDetails value(?,?,?)");
			for(Employee emp : list) {
				preparedStatement.setInt(1, emp.getId());
				preparedStatement.setInt(2, emp.getAge());
				preparedStatement.setDouble(3, emp.getSalary());
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
