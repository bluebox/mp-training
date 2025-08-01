package com.library_management.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;
import com.mysql.cj.jdbc.MysqlDataSource;


public class DBConnection {
	private static Connection conn;
	private static Statement statement;
	
	public static void setConn(Connection conn) {
		DBConnection.conn=conn;
	}
	
	public static Connection getConn() {
		return DBConnection.conn;
	}
	
	public static void setStatement(Statement statement) {
		DBConnection.statement=statement;
	}
	
	public static Statement getStatement() {
		return DBConnection.statement;
	}
	
	public static void closeStatement() {
		try {
			DBConnection.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConn() {
		try {
			DBConnection.getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void connectToDB(String connectionString) {
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUser(System.getenv("DATABASE_USER"));
		dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
		dataSource.setURL(connectionString);
		try {
			Connection conn=dataSource.getConnection();
			Statement statement=conn.createStatement();
			setConn(conn);
			setStatement(statement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
