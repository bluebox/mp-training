package com.CurdOparations.UserDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private Statement st;

	@Override
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tastdata", "Medplus", "Satheesh@54");
			st = con.createStatement();
			System.out.println("Connected Sucessfull");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");

		try {
			int res = st.executeUpdate("update user set firstname='" + firstname + "',lastname='" + lastname
					+ "' where email='" + email + "'");
			if (res > 0) {
				response.getWriter().println("User data Updated sucessfully");
			} else {
				response.getWriter().println("user details not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			if (con != null) {
				con.close();
				st.close();
				System.out.println("Disconnected Connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
