package com.libraryManagementSystem.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {

	private DBConnection() {
	}

	private static Connection connection;

	private static void initializeConnection() throws DatabaseConnectionException {
		if (connection != null) {
			return;
		}

		try (InputStream inputStream = new FileInputStream("config.properties")) {
			MysqlDataSource dataSource = new MysqlDataSource();

			Properties properties = new Properties();
			properties.load(inputStream);

			dataSource.setPort(Integer.parseInt(properties.getProperty("port")));
			dataSource.setServerName(properties.getProperty("server"));
			dataSource.setUser(properties.getProperty("user"));
			dataSource.setPassword(properties.getProperty("password"));
			dataSource.setDatabaseName(properties.getProperty("database"));

			connection = dataSource.getConnection();
			System.out.println("Connected successfully");

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Failed to connect to database.", e);
		} catch (IOException e) {
			throw new DatabaseConnectionException("Failed to read database configuration file.", e);
		}
	}

	public static Connection getConnection() throws DatabaseConnectionException {
		try {
			if (connection == null || connection.isClosed()) {
				initializeConnection();
			}
		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error checking database connection state.", e);
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("Error closing database connection: " + e.getMessage());
		}
	}

	public static void setAutoCommit(boolean commit) {
		try {
			if (connection != null) {
				connection.setAutoCommit(commit);
			}
		} catch (SQLException e) {
			System.err.println("Error setting auto-commit: " + e.getMessage());
		}
	}

	public static void commit() throws SQLException {
		if (connection != null) {
			connection.commit();
		}
	}

	public static void rollback() throws SQLException {
		if (connection != null) {
			connection.rollback();
		}
	}
}
