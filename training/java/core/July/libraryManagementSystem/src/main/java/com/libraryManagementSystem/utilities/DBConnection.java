package com.libraryManagementSystem.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.libraryManagementSystem.exceptions.InvalidException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

	private DBConnection() {

	}

	private static Connection connection;

	private static void initializeConnection() throws InvalidException {

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
			throw new InvalidException("Connection failed: " + e.getMessage());
		}
	}

	public static Connection getConnection() throws InvalidException {
		try {
			if (connection == null || connection.isClosed()) {
				initializeConnection();
			}
		} catch (SQLException e) {
			throw new InvalidException("Error checking connection state: " + e.getMessage());
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

	public static void SetAutoCommit(Boolean commit) {
		try {
			if (connection != null) {
				connection.setAutoCommit(commit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit() throws SQLException {
		connection.commit();
	}

	public static void rollback() throws SQLException {
		connection.rollback();
	}
}
