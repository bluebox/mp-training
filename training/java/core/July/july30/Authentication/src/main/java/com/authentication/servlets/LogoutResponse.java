package com.authentication.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logoutResponse")
public class LogoutResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutResponse() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter writer = response.getWriter();) {

			HttpSession session = request.getSession();
			response.setContentType("text/html");
			session.invalidate();
			writer.print("<p>User LoggedOut Succesfully...</p><br>");
			writer.println("<p>Click Below Link to Login Again...</p><br>");
			writer.println("<a href='login.html'>Login</a>");

		}
	}

}
