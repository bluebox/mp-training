package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.List;

import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/activeMembers")
public class ActiveMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();

	public ActiveMembersServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			List<CustomActiveIssuedBooks> books = bookService.getActiveIssuedBooks();
			request.setAttribute("activeIssueBooks", books);

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("activeMembers.jsp");
		dispatcher.forward(request, response);
	}

}
