package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/addUserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();

	    String f_name = request.getParameter("first_name");
	    String l_name = request.getParameter("last_name");
	    String email = request.getParameter("email");
	    String pass = request.getParameter("password");

	    String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
	        pstmt.setString(1, f_name);
	        pstmt.setString(2, l_name);
	        pstmt.setString(3, email);
	        pstmt.setString(4, pass);

	        int insert = pstmt.executeUpdate();

	        if (insert > 0) {
	            out.println(insert + " user(s) added successfully.");
	        } else {
	            out.println("User not added.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        out.println("Database error occurred.");
	    }
	}
	
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
