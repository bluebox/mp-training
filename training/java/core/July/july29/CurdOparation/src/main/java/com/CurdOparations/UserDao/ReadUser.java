package com.CurdOparations.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReadUser")
public class ReadUser extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ResultSet res = st.executeQuery("select * from user");
			PrintWriter writer = response.getWriter();
			while (res.next()) {
				writer.println("firstname :" + res.getString("firstname") + "last name : " + res.getString("lastname")
						+ "email :" + res.getString("email"));
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
