package com.renu.userapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String url = "jdbc:mysql://localhost:3306/mydb"; 
    String username = "renaiah"; 
    String password = "renaiahMysql";
    Connection con;
	PreparedStatement st;
	
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			st = con.prepareStatement("insert into users values(?, ?, ?, ?)");
			st.setInt(1, Integer.parseInt(request.getParameter("id")));
			st.setString(2, request.getParameter("name"));
			st.setString(3, request.getParameter("dept"));
			st.setDouble(4, Double.parseDouble(request.getParameter("salary")));
			
			int result = st.executeUpdate();
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<html><body><b>Successfully Inserted</b></body></html>");
			} else {
				out.println("<html><body><b>Insert Failed</b></body></html>");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void destroy() {
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
