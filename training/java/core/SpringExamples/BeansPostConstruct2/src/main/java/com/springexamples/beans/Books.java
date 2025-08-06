package com.springexamples.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Books {
	private final Connect connect;
	private Connection conn;
	
	@Autowired
	public Books(Connect connect) {
		this.connect=connect;
	}
	
	@PostConstruct
	public void getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(connect.getDriver());
		conn=DriverManager.getConnection(connect.getUrl(),connect.getUser(),connect.getPass());
	}
	
	public ResultSet getALLbooks() throws SQLException {
		Statement statement=conn.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from books");
		return resultSet;
	}
	@PreDestroy
	public void destroy() throws SQLException {
		conn.close();
	}
}
