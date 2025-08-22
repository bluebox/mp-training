package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

@WebServlet("/updateAvailability")
public class UpdateAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();
	Book selectedBook = null;

	public UpdateAvailabilityServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedTitle = request.getParameter("book");
		if (selectedTitle != null) {
			try {
				Book book = bookService.getBooks().stream().filter(b -> b.getTitle().equals(selectedTitle)).findFirst()
						.orElse(null);
				selectedBook = book;
				if (book != null) {

					setAtributesSelected(request, book);
				}
			} catch (InvalidException e) {
				e.printStackTrace();
			}
		}
		setAtributes(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher("updateAvailability.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookTitle = request.getParameter("book");
		BookAvailability bookAvailability = BookAvailability.valueOf(request.getParameter("availability"));

		Book newBook = new Book(selectedBook.getBookId(), bookTitle, selectedBook.getAuthor(),
				selectedBook.getCategory(), selectedBook.getStatus(), bookAvailability);

		try {
			if (selectedBook.equals(newBook)) {
				request.setAttribute("message", "at least one change required");
				request.setAttribute("formReset", false);
				setAtributesSelected(request, newBook);
				setAtributes(request);
			} else {
				bookService.updateBook(newBook, selectedBook);

				request.setAttribute("message", bookTitle + " Book availability updated Successfully");
				request.setAttribute("formReset", true);

				setAtributes(request);
			}

		} catch (InvalidException e) {
			request.setAttribute("message", e.getMessage());

			request.setAttribute("formReset", false);
			setAtributesSelected(request, newBook);

			setAtributes(request);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("updateAvailability.jsp");
		dispatcher.forward(request, response);

	}

	public void setAtributes(HttpServletRequest request) {
		try {
			List<Book> books = new ArrayList<>(bookService.getBooks());

			List<BookAvailability> availabilities = new ArrayList<>(Arrays.asList(BookAvailability.values()));

			request.setAttribute("books", books);
			request.setAttribute("availabilities", availabilities);

		} catch (InvalidException e) {
			e.printStackTrace();
		}
	}

	public void setAtributesSelected(HttpServletRequest request, Book book) {

		request.setAttribute("book", selectedBook.getTitle());
		request.setAttribute("availability", book.getAvailability());

	}
}
