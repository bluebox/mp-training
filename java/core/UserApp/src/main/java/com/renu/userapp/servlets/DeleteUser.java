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
 * Servlet implementation class DeleteUser
 */
@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
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
			st = con.prepareStatement("delete from users where id=(?)");
			st.setInt(1, Integer.parseInt(request.getParameter("id")));
			int result = st.executeUpdate();
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.println("<h1>Deleted succesufully!</h1>");
			} else {
				out.println("<h1>There is no ID or Not deleted...</h1>");
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
