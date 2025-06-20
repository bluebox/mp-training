package com.renu.userapp;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/userRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String url = "jdbc:mysql://localhost:3306/mydb"; 
    String username = "renaiah"; 
    String password = "renaiahMysql";
    


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established successfully");

			PreparedStatement st = con.prepareStatement("insert into users values(?, ?, ?, ?)");
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

			st.close();
			con.close();
			System.out.println("Connection closed!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

