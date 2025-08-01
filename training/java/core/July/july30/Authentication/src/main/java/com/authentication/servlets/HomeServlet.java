package com.authentication.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter writer = response.getWriter()) {

			HttpSession session = request.getSession();
			response.setContentType("text/html");

			writer.print("<p>User LoggedIn Succesfully...</p><br>");
			writer.println("<p>User Gmail : " + session.getAttribute("gmail") + "</p><br>");
			writer.println("<a href='logoutResponse'>Logout</a>");

		}
	}

}
