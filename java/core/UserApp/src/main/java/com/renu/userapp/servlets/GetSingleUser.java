package com.renu.userapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSingleUser
 */
@WebServlet("/getSingleUser")
public class GetSingleUser extends HttpServlet {
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
			st = con.prepareStatement("select * from users where id=(?)");
			st.setInt(1, Integer.parseInt(request.getParameter("id")));
			ResultSet rs = st.executeQuery();
			PrintWriter out = response.getWriter();
			out.println("<h1>User Details</h1>");
			while(rs.next()) {
				out.println("<h2>ID : "+rs.getInt("id")+"</h2>");
				out.println("<h2>Name : "+rs.getString("name")+"</h2>");
				out.println("<h2>Department : "+rs.getString("dept")+"</h2>");
				out.println("<h2>Salary : "+rs.getDouble("salary")+" LPA"+"</h2>");
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
