package com.servlet.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns = "/deletion")
public class DeleteUser extends HttpServlet {

	Connection con;
	Statement stmt;

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

			stmt = con.createStatement();
			String sql = "delete from users where username='" + user + "'";
			stmt.execute(sql);
			res.getWriter().println("deleted user :" + user);
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
