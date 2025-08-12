package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.dao.ReportsDao;
import com.webLibraryManagementSystem.dao.impl.ReportsDaoImpl;
import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reports/*")
public class Reports extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final ReportsDao reportsService = new ReportsDaoImpl();

	public Reports() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		switch (pathInfo) {
		case "/activeMembers":
			activeMembersGet(request, response);
			break;
		case "/booksByCategory":
			booksByCategoryGet(request, response);
			break;
		case "/overdueBooks":
			overdueBooksGet(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void activeMembersGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<CustomActiveIssuedBooks> books = reportsService.getActiveIssuedBooks();
			request.setAttribute("activeIssueBooks", books);

		} catch (DatabaseOperationException e) {
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/activeMembers.jsp");
		dispatcher.forward(request, response);
	}

	protected void booksByCategoryGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Map<String, Integer> books = reportsService.getBookCountByCategory();
			request.setAttribute("books", books);

		} catch (DatabaseOperationException e) {
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/booksByCategory.jsp");
		dispatcher.forward(request, response);
	}

	protected void overdueBooksGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<CustomActiveIssuedBooks> books = reportsService.getOverDueBooks();
			request.setAttribute("overDueBooks", books);

		} catch (DatabaseOperationException e) {
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/overdueBooks.jsp");
		dispatcher.forward(request, response);
	}

}
