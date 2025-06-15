package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	
	public Connection getConnection()

	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(null);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return connection;
	}
}
