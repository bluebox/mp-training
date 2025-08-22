package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.Map;

import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/booksByCategory")
public class BooksByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();

	public BooksByCategoryServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Map<String, Integer> books = bookService.getBookCountByCategory();
			request.setAttribute("books", books);

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("booksByCategory.jsp");
		dispatcher.forward(request, response);
	}

}
