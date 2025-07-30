package com.Servlet.World;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetrieveSrvlet
 */
@WebServlet("/RetrieveSrvlet")
public class RetrieveSrvlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("");
		try {
			out.println("<html><body>");
            out.println("<h3>User Details</h3>");
            out.println("<table border=1><tr>" + "<td><b>First Name</b></td>" + "<td><b>LastName</b></td>"
                    + "<td><b>Email</b></td>" + "<td><b>Password</b></td></tr>");
			PreparedStatement pt = con.prepareStatement("select * from user");
			ResultSet rs = pt.executeQuery();
			while(rs.next()) {
				String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                out.println("<tr>" + "<td>" + fname + "</td>" + "<td>" + lname + "</td>" + "<td>" + email + "</td>"
                        + "<td>" + password + "</td></tr>");
            }
            out.println("</table></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
