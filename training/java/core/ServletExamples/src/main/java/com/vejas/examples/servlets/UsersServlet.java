package com.vejas.examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Statement query = connection.createStatement();
			try (ResultSet resultSet = query.executeQuery("select * from user")) {
				if (resultSet != null) {
					out.println("First  Name\t Second Name  \t Email <br><br>");
					while (resultSet.next()) {
						String first_name = resultSet.getString("firstname");
						String last_name = resultSet.getString("lastname");
						String email = resultSet.getString("email");
						out.println(first_name+"\t"+last_name+"\t"+email+"<br><br>");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
