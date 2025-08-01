package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletCount")
public class ServletCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String name = "UserCount";

	public ServletCount() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = request.getSession().getServletContext();

		try (PrintWriter writer = response.getWriter()) {
			writer.println("Number of active Users are : " + context.getAttribute(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
