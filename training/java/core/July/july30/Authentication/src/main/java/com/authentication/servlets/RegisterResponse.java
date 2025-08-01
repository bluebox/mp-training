package com.authentication.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerResponse")
public class RegisterResponse extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Connection conn;
	private PreparedStatement insert;
	private PreparedStatement select;

	public RegisterResponse() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		ServletContext context = config.getServletContext();

		String url = context.getInitParameter("dbUrl");
		String user = context.getInitParameter("dbUser");
		String password = context.getInitParameter("dbPassword");

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			insert = conn.prepareStatement("insert into tastdata.users values(?,?)");
			select = conn.prepareStatement("select * from tastdata.users where email=?");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String gmail = request.getParameter("gmail");
		String pass = request.getParameter("password");
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		try {
			select.setString(1, gmail);
			ResultSet resultSet = select.executeQuery();

			if (resultSet.next()) {
				writer.println("<p>User Already Exists Please Login...</p><br>");
				writer.println("<a href='login.html'>Go To Login</a>");
				return;
			}

			insert.setString(1, gmail);
			insert.setString(2, pass);

			int countInsert = insert.executeUpdate();
			if (countInsert <= 0) {

				RequestDispatcher rd = request.getRequestDispatcher("index.html");

				rd.include(request, response);

			} else {

				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {

			if (conn != null && !conn.isClosed()) {
				conn.close();
				insert.close();
				select.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
