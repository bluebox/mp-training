package com.library.utilities;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionMaker {

	private static final MysqlDataSource dataSource = new MysqlDataSource();
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(new FileInputStream("config.properties"));
			dataSource.setUrl("jdbc:mysql://localhost:3306/library");
			dataSource.setUser(properties.getProperty("userName"));
			dataSource.setPassword(properties.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
