package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookCategory;
import com.webLibraryManagementSystem.utilities.BookStatus;
import com.webLibraryManagementSystemexceptions.InvalidException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();

	public AddBookServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookCategory> categories = new ArrayList<>(Arrays.asList(BookCategory.values()));

		List<BookStatus> statuses = new ArrayList<>(Arrays.asList(BookStatus.values()));
		List<BookAvailability> availabilities = new ArrayList<>(Arrays.asList(BookAvailability.values()));

		request.setAttribute("categories", categories);
		request.setAttribute("statuses", statuses);
		request.setAttribute("availabilities", availabilities);

		RequestDispatcher dispatcher = request.getRequestDispatcher("addBook.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookTitle = request.getParameter("title");
		String bookAuthor = request.getParameter("author");
		BookCategory bookcategory = BookCategory.valueOf(request.getParameter("category"));
		BookStatus bookstatus = BookStatus.valueOf(request.getParameter("status"));
		BookAvailability bookAvailability = BookAvailability.valueOf(request.getParameter("availability"));

		Book newBook = new Book(bookTitle, bookAuthor, bookcategory, bookstatus, bookAvailability);

		try {
			bookService.addBook(newBook);

			request.setAttribute("message", bookTitle + " Book added Successfully");
			request.setAttribute("formReset", true);

			request.setAttribute("title", "");
			request.setAttribute("author", "");
			request.setAttribute("category", "");
			request.setAttribute("status", "");
			request.setAttribute("availability", "");

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("formReset", false);

			request.setAttribute("title", bookTitle);
			request.setAttribute("author", bookAuthor);
			request.setAttribute("category", bookcategory);
			request.setAttribute("status", bookstatus);
			request.setAttribute("availability", bookAvailability);
		}

		doGet(request, response);
	}
}
