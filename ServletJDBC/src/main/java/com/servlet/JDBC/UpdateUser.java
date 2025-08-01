package com.servlet.JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet(urlPatterns = "/updateUser")
public class UpdateUser extends HttpServlet {
	Connection con;
	PreparedStatement ps;

	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletdb", "root", "root");
			System.out.println("connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			String user = req.getParameter("user");
			String pass = req.getParameter("pass");
			
			String sql = "update users set password=? where username=?";
			ps=con.prepareStatement(sql);
			ps.setString(2, user);
			ps.setString(1,pass);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			con.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
