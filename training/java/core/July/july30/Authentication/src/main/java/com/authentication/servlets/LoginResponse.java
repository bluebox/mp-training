package com.authentication.servlets;

import java.io.IOException;
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
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginResponse")
public class LoginResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;
	private PreparedStatement select;

	public LoginResponse() {
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
		String password = request.getParameter("password");
		response.setContentType("text/html");

		try {
			select.setString(1, gmail);
			ResultSet rs = select.executeQuery();

			if (rs.next()) {

				String gmailValue = rs.getString(1);
				String passwordValue = rs.getString(2);

				if (passwordValue.equals(password)) {

					HttpSession session = request.getSession();
					session.setAttribute("gmail", gmailValue);
					RequestDispatcher rd = request.getRequestDispatcher("homeServlet");
					rd.forward(request, response);

				} else {
					RequestDispatcher rd = request.getRequestDispatcher("login.html");
					rd.include(request, response);
				}

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		try {

			if (conn != null && !conn.isClosed()) {
				conn.close();
				select.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
