package com.library.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionMaker {
	public static Connection conn=null;
	public static Connection getConnection()
	{
		MysqlDataSource dataSource= new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/library");
		dataSource.setUser("manoj");
		dataSource.setPassword("Manoj@123");
		
		try {
			 conn= dataSource.getConnection();
			 System.out.println("Connection Established Sucessfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}
	public static void setAutoCommit(boolean flag)
	{
		try {
			conn.setAutoCommit(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback()
	{
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit()
	{
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}