package utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

	private static Connection connection;

	public static void initializeConnection() {
		if (connection != null) {
			return;
		}

		MysqlDataSource dataSource = new MysqlDataSource();

		dataSource.setPort(Integer.parseInt(System.getenv("port")));
		dataSource.setServerName(System.getenv("server"));
		dataSource.setUser(System.getenv("user"));
		dataSource.setPassword(System.getenv("Password"));
		dataSource.setDatabaseName(System.getenv("database"));

		try {
			connection = dataSource.getConnection();
			System.out.println("Connected successfully");
		} catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
			connection = null;
		}
	}

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				initializeConnection();
			}
		} catch (SQLException e) {
			System.out.println("Error checking connection state: " + e.getMessage());
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
