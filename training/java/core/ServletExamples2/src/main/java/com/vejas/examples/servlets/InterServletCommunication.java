package com.vejas.examples.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/validateUser")
public class InterServletCommunication extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn;
    private Statement statement;
    
    @Override
    public void init() throws ServletException {
    	try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "root");
			statement=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password=request.getParameter("password");
		try {
			ResultSet result = statement.executeQuery("select * from user where email='"+email+"'and password='"+password+"'");
			if(result.next()) {
				if(result.getString("email").equals(email) && result.getString("password").equals(password)) {
			
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("/successServlet");
				requestDispatcher.forward(request, response);
			}
			else {
				response.getWriter().println("invalid inputs");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("loginUser.html");
				requestDispatcher.include(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
