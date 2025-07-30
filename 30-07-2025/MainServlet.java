package com.Servlet.World;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	public void init() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/myservlet", "root", "pranamya@2004");
	        System.out.println("Connection established: " + (con != null));
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Init error: " + e.getMessage());
	    }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String query = "INSERT INTO user(firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			int result = pstmt.executeUpdate();
			//Statement stmt = con.createStatement();
			//String query = "INSERT INTO user(firstname, lastname, email, password) VALUES('Pranav', 'prana', 'pranav@mail.com', 'pranav')";
			//int result = stmt.executeUpdate(query);
			if(result>0) {
				out.print("insert sucess....");
				}else {
					out.print("insert unsucess....");
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<h3>This servlet only handles POST requests. Please use the form to submit data.</h3>");
	}
	public void destroy() {}
}
