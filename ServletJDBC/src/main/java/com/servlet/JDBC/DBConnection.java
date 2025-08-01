package com.servlet.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("/insert")
public class DBConnection extends HttpServlet {
		
		Connection con;
		Statement stmt;
		
		public void init() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdb","root","root");
				System.out.println("connected");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
			
			String user=req.getParameter("user");
			String pass=req.getParameter("pass");
			try {
				stmt =con.createStatement();
				String sql="INSERT INTO users (username,password) VALUES ('"+user+"','"+pass+"')";
				stmt.execute(sql);
				res.getWriter().println("successfully added user");
				res.getWriter().println("username :"+user);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
//			try {
//				ResultSet rs = stmt.executeQuery(sql);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
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
