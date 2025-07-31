package com.jdbc.jdbcBatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/jdbcbatch")
public class JdbcBatchSample extends HttpServlet{
	
	Connection conn;
	Statement stmt;
	
	@Override
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
			stmt=conn.createStatement();
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
		try {
			
			stmt.addBatch("Insert into userdetails values('nissi','paul','np@e.com','brooo')");
			stmt.addBatch("Insert into userdetails values('bindu','hima','bp@e.com','sisss')");
			stmt.addBatch("Insert into userdetails values('abhi','nav','an@e.com','hisssss')");
			int[] result=stmt.executeBatch();
			PrintWriter out=resp.getWriter();
			for(int i=0;i<result.length;i++) {
				out.println("row "+i+" inserted successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void destroy() {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	
	
	
	

}
