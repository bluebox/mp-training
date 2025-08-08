package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewBooks")
public class ViewBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();

	public ViewBooksServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> books;
		try {
			books = new ArrayList<>(bookService.getBooks());
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewBooks.jsp");
			dispatcher.forward(request, response);
		} catch (InvalidException e) {
			e.printStackTrace();
		}

	}

}
