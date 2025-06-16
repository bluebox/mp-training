package com.LibraryManagement.utilites;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	
	
	public static  Connection connectDB() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		Properties props=new Properties();
//		FileInputStream fis=new FileInputStream("props.properties");
//		props.load(fis);
//		String url=props.getProperty("url");
//		String username=props.getProperty("username");
//		String password=props.getProperty("password");
//		Connection con=DriverManager.getConnection(url,username,password);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms_task","root","root");
		return con;
	}
}