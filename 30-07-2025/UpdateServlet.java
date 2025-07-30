package com.Servlet.World;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
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
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String query = "UPDATE user SET password=? WHERE email=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			int result = pstmt.executeUpdate();
			if(result>0) {
				out.print("update sucess....");
				}else {
					out.print("update unsucess....");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
 }
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
