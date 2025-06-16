package com.LibraryManagement.utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private DBConnection() {

	}

	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
		return  DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","Anand","1925112816@Aa");
	}
}
