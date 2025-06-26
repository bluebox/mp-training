package com.library.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dutil {

	private static final String URL = "jdbc:mysql://localhost:3306/mylibrary";
	private static final String USER = "root";
	private static final String PASSWORD = "Medplus@321";

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			return conn;
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
		return null;
	}

}
