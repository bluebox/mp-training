package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

	private DBConnection() {

	}

	private static Connection connection;

	private static void initializeConnection() {

		if (connection != null) {
			return;
		}

		try {
			MysqlDataSource dataSource = new MysqlDataSource();

			Properties properties = new Properties();
			InputStream inputStream = new FileInputStream("config.properties");
			properties.load(inputStream);

			dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
			dataSource.setServerName(properties.getProperty("server"));
			dataSource.setUser(properties.getProperty("user"));
			dataSource.setPassword(properties.getProperty("password"));
			dataSource.setDatabaseName(properties.getProperty("database"));

			connection = dataSource.getConnection();
			System.out.println("Connected successfully");

			inputStream.close();

		} catch (SQLException | IOException e) {
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
