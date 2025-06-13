package com.storefront.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtility {

	private static final String dbURL = "jdbc:mysql://localhost:3306/storefront";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbURL, USER, PASSWORD);
	}

}
