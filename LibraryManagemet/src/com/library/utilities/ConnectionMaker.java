package com.library.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionMaker {

	public static Connection getConnection()
	{
		MysqlDataSource dataSource= new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/library");
		dataSource.setUser("mourya");
		dataSource.setPassword("Mourya@1234");
		Connection conn=null;
		try {
			 conn= dataSource.getConnection();
			 System.out.println("Connection Established Sucessfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;





	}

}
