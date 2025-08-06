package com.webLibraryManagementSystem.controllers;

import java.io.IOException;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();

	public DeleteBookServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		try {
			Book book = bookService.getBookById(bookId);
			if (book.getAvailability().equals(BookAvailability.ISSUED)) {
				request.setAttribute("message", "Book Availability is Issed Please Collect Book before delete");
			} else {
				bookService.deleteBook(book);
				request.setAttribute("message", book.getTitle() + " deleted Successfully");
			}

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("viewBooks");
		dispatcher.forward(request, response);

	}

}
