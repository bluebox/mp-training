package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String url = "jdbc:mysql://localhost:3306/demo";
	private static final String user = "root";
	private static final String password = "Akash@123";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String idParam = request.getParameter("id");
		String name = request.getParameter("name");
		String branch = request.getParameter("branch");

		int id = 0;
		try {
			id = Integer.parseInt(idParam);
		} catch (NumberFormatException e) {
			out.println("<h2>Invalid ID format. Please enter a number.</h2>");
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,password);
			System.out.println("Connection established");
			String sql = "insert into student (id,name,branch) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, branch);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				out.println("<h2>User inserted successfully!</h2>");
			} else {
				out.println("<h2>Failed to insert user.</h2>");
			}
		} catch (Exception e) {
			out.println("<h2>Some error occured</h2>");
		}
	}

}
