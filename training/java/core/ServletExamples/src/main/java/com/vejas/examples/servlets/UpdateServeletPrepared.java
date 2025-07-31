package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateServeletPrepared
 */
@WebServlet("/updateServeletPrepared")
public class UpdateServeletPrepared extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "root");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String f_name = request.getParameter("first_name");
			String l_name = request.getParameter("second_name");
			String email = request.getParameter("email");
			String pass=request.getParameter("password");
			String query = "update user set firstname=? , lastname=? ,password=? where email=?";
			try (PreparedStatement pstmt = connection.prepareStatement(query)) {
				pstmt.setString(1, f_name);
				pstmt.setString(2, l_name);
				pstmt.setString(3, pass);
				pstmt.setString(4, email);
				int insert =pstmt.executeUpdate();
				if (insert > 0) {
					out.println(insert + " user(s) updated successfully.");
				} else {
					out.println("User not updated.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
