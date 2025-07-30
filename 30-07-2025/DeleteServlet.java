package com.Servlet.World;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try {
			String email = request.getParameter("email");
			String query = "delete from user where email=?";
			PreparedStatement pt = con.prepareStatement(query);
			pt.setString(1, email);
			int result = pt.executeUpdate();
			if(result>0) {
				PrintWriter out = response.getWriter();
				out.print("Delete sucess....");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
