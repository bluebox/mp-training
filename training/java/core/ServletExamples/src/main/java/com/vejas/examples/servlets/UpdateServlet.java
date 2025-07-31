package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/updateUserServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServletContext context=getServletContext();
			connection=DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("user"),context.getInitParameter("pass"));
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
	    String l_name = request.getParameter("second_name");
	    String email = request.getParameter("email");

	    try (Statement pstmt = connection.createStatement()) {
	    	
	        int insert = pstmt.executeUpdate("update user set firstname='"+f_name+"' ,lastname='"+l_name+"' where email='"+email+"'");

	        if (insert > 0) {
	            out.println(insert + " user(s) updated successfully.");
	        } else {
	            out.println("User not updated.");
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
