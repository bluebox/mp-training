package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DbConnection extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
	Statement stmt;

	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try {
			

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlets", "root", "Gopi@2507");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			
			String user = req.getParameter("user");
			String pass = req.getParameter("pass");

			
			stmt=con.createStatement();
			String sql = "INSERT INTO users (username, password) VALUES ('"+user+"' , '"+pass+"')";
			stmt.execute(sql);
			res.getWriter().println("Succesfully added user ");	
			res.getWriter().println("username : "+user);	
//			
//			String sql1="Select * from users";
//			ResultSet rs=stmt.executeQuery(sql1);
//			res.getWriter().println(rs.getString("username") + ":" + rs.getString("password"));	
//			
//			
//			while(rs.next()) {
//				res.getWriter().println(rs.getString("username") + ":" + rs.getString("password"));	
//			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void destroy() {
		try {
			con.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}