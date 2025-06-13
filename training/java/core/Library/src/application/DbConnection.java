package application;

import java.sql.*;

public class DbConnection {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/training";

		final String USER = "root";
		final String PASS = "Training@1";

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Established successfully");
		
	}
	
	
	

}
