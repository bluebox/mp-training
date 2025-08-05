package com.LibraryManagement.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException , IOException{
		Properties properties=new Properties();
		InputStream input=new FileInputStream("C:\\Users\\gopin\\eclipse-workspace\\LMS\\src\\main\\java\\com\\LibraryManagement\\Utils\\config.properties");
		properties.load(input);
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUser(properties.getProperty("dbuser"));
		dataSource.setPassword(properties.getProperty("dbpassword"));
		dataSource.setUrl(properties.getProperty("dburl"));
		return dataSource.getConnection();
	}
}
