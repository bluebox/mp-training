package com.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/dblearnjava"; // replace with your DB name
		String user = "root"; // your username
		String password = "Medplus@321"; // your password

		try {
			// 1. Load and register JDBC driver (optional for modern versions)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Create connection
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the database!");

			// 3. Create a statement
			Statement stmt = conn.createStatement();

			// 4. Execute query
			ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

			// 5. Process the result
			while (rs.next()) {
				System.out.println("name : " + rs.getString("name")+" Age : "+rs.getInt("Age"));
				// Add more columns as needed
			}
			String sql = "INSERT INTO Employee (name,Age) VALUES (?,?)";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "Ajay");
			statement.setInt(2, 42);
//			int Check = statement.executeUpdate();
//			if (Check > 0 ) {
//				System.out.println("Insertion done !!!");
//			}else {
//				System.out.println("Some Error Occured ");
//			}
			String delete = "DELETE FROM Employee where name = ?";
			PreparedStatement deleteStatement = conn.prepareStatement(delete);
			deleteStatement.setString(1, "Ajay");
			deleteStatement.executeUpdate();
			System.out.println("Deletion done !!!");

			// 6. Close resources
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver not found: " + e.getMessage());
		}

	}
}
