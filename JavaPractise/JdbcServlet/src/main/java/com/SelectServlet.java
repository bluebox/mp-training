package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Akash@123");
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name,branch from student");

			out.println("<html><body>");
			out.println("<h2>Data from MySQL:</h2>");
			out.println("<table border='1'>");
			out.println("<tr><th>ID</th><th>Name</th><th>Branch</th></tr>");

			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>"
						+ rs.getString("branch") + "</td></tr>");
			}

			out.println("</table>");
			out.println("</body></html>");

		} catch (ClassNotFoundException | SQLException e) {
			out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
}
