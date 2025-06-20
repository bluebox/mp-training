package com.renu.userapp.servlets;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			st = con.prepareStatement("select * from users");
			ResultSet rs = st.executeQuery();
			PrintWriter out = response.getWriter();
			out.println("<h1>All Users</h1>");
//			out.println("<h2>ID|NAME|DEPARTMENT|SALARY</h2>");
			while(rs.next()) {
//				out.println("<h2>"+rs.getInt("id")+"|"+rs.getString("name")+"|"+rs.getString("dept")+"|"+rs.getDouble("salary")+"</h2>");
				out.println("<h2>ID : "+rs.getInt("id")+"</h2>");
				out.println("<h2>Name : "+rs.getString("name")+"</h2>");
				out.println("<h2>Department : "+rs.getString("dept")+"</h2>");
				out.println("<h2>Salary : "+rs.getDouble("salary")+" LPA"+"</h2>");
				out.println("-----------------------------------------------------");

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
