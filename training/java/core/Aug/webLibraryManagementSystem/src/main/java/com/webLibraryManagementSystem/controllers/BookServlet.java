package com.webLibraryManagementSystem.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.DuplicateBookException;
import com.webLibraryManagementSystem.exceptions.InvalidBookDataException;
import com.webLibraryManagementSystem.services.impl.BookServicesImpl;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookCategory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookServicesImpl bookService = new BookServicesImpl();
	Book selectedBookAvailability = null;
	Book selectedBookUpdate = null;

	public BookServlet() {
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
		case "/addBook":
			addBookGet(request, response);
			break;
		case "/updateAvailability":
			updateAvailabilityGet(request, response);
			break;
		case "/updateBook":
			updateBookGet(request, response);
			break;
		case "/viewBooks":
			viewBooksGet(request, response);
			break;
		case "/deleteBook":
			deleteBookGet(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		switch (pathInfo) {
		case "/addBook":
			addBookPost(request, response);
			break;
		case "/updateAvailability":
			updateAvailabilityPost(request, response);
			break;
		case "/updateBook":
			updateBookPost(request, response);
			break;
		case "/viewBooks":
			viewBooksGet(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void addBookGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<BookCategory> categories = new ArrayList<>(Arrays.asList(BookCategory.values()));

		request.setAttribute("categories", categories);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/addBook.jsp");
		dispatcher.forward(request, response);
	}

	private void addBookPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookTitle = request.getParameter("title");
		String bookAuthor = request.getParameter("author");
		BookCategory bookcategory = BookCategory.valueOf(request.getParameter("category"));

		Book newBook = new Book(bookTitle, bookAuthor, bookcategory);

		try {
			bookService.addBook(newBook);

			request.setAttribute("message", bookTitle + " Book added Successfully");
			request.setAttribute("formReset", true);

			request.setAttribute("title", "");
			request.setAttribute("author", "");
			request.setAttribute("category", "");
			request.setAttribute("status", "");
			request.setAttribute("availability", "");

		} catch (InvalidBookDataException | DuplicateBookException | DatabaseOperationException e) {
			request.setAttribute("message", e.getMessage());
			request.setAttribute("formReset", false);

			request.setAttribute("title", bookTitle);
			request.setAttribute("author", bookAuthor);
			request.setAttribute("category", bookcategory);

		}

		addBookGet(request, response);
	}

	private void updateAvailabilityGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedTitle = request.getParameter("book");
		if (selectedTitle != null) {
			try {
				Book book = bookService.getBooks().stream().filter(b -> b.getTitle().equals(selectedTitle)).findFirst()
						.orElse(null);
				selectedBookAvailability = book;
				if (book != null) {

					setAtributesAvailabilitySelected(request, book);
				}
			} catch (DatabaseOperationException e) {
				e.printStackTrace();
			}
		}
		setAtributesAvailability(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateAvailability.jsp");
		dispatcher.forward(request, response);
	}

	private void updateAvailabilityPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookTitle = request.getParameter("book");
		BookAvailability bookAvailability = BookAvailability.valueOf(request.getParameter("availability"));

		Book newBook = new Book(selectedBookAvailability.getBookId(), bookTitle, selectedBookAvailability.getAuthor(),
				selectedBookAvailability.getCategory(), selectedBookAvailability.getStatus(), bookAvailability);

		try {
			if (selectedBookAvailability.equals(newBook)) {
				request.setAttribute("message", "at least one change required");
				request.setAttribute("formReset", false);
				setAtributesAvailabilitySelected(request, newBook);
				setAtributesAvailability(request);
			} else {
				bookService.updateBook(newBook, selectedBookAvailability);

				request.setAttribute("message", bookTitle + " Book availability updated Successfully");
				request.setAttribute("formReset", true);

				setAtributesAvailability(request);
			}

		} catch (BookNotFoundException | InvalidBookDataException | DatabaseOperationException
				| DuplicateBookException e) {
			request.setAttribute("message", e.getMessage());

			request.setAttribute("formReset", false);
			setAtributesAvailabilitySelected(request, newBook);

			setAtributesAvailability(request);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateAvailability.jsp");
		dispatcher.forward(request, response);

	}

	private void updateBookGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selectedTitle = request.getParameter("book");

		if (selectedTitle != null) {
			try {
				Book book = bookService.getBooks().stream()
						.filter(b -> (b.getTitle() + " - " + b.getAuthor()).equals(selectedTitle))
						.filter(b -> b.getAvailability().equals(BookAvailability.AVAILABLE)).findFirst().orElse(null);
				selectedBookUpdate = book;
				if (book != null) {

					setAtributesUpdateSelected(request, book);
				}
			} catch (DatabaseOperationException e) {
				e.printStackTrace();
			}
		}

		setAtributesUpdate(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateBook.jsp");
		dispatcher.forward(request, response);

	}

	private void updateBookPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookTitle = request.getParameter("bookTitle");
		String bookAuthor = request.getParameter("author");
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		BookCategory bookcategory = BookCategory.valueOf(request.getParameter("category"));

		Book newBook = new Book(bookId, bookTitle, bookAuthor, bookcategory);

		try {
			if (selectedBookUpdate.equals(newBook)) {
				request.setAttribute("message", "at least one change required");
				request.setAttribute("formReset", false);
				setAtributesUpdateSelected(request, newBook);
				setAtributesUpdate(request);
			} else {
				bookService.updateBook(newBook, selectedBookUpdate);

				request.setAttribute("message", bookTitle + " Book added Successfully");
				request.setAttribute("formReset", true);

				setAtributesUpdate(request);
			}

		} catch (BookNotFoundException | InvalidBookDataException | DatabaseOperationException
				| DuplicateBookException e) {
			request.setAttribute("message", e.getMessage());

			request.setAttribute("formReset", false);
			setAtributesUpdateSelected(request, newBook);

			setAtributesUpdate(request);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateBook.jsp");
		dispatcher.forward(request, response);

	}

	private void viewBooksGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> books;
		try {
			books = new ArrayList<>(bookService.getBooks());
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/viewBooks.jsp");
			dispatcher.forward(request, response);
		} catch (DatabaseOperationException e) {
			e.printStackTrace();
		}

	}

	private void deleteBookGet(HttpServletRequest request, HttpServletResponse response)
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

		} catch (DatabaseOperationException | BookNotFoundException | InvalidBookDataException e) {
			request.setAttribute("message", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/books/viewBooks");
		dispatcher.forward(request, response);

	}

	public void setAtributesAvailability(HttpServletRequest request) {
		try {
			List<Book> books = new ArrayList<>(bookService.getBooks());

			List<BookAvailability> availabilities = new ArrayList<>(Arrays.asList(BookAvailability.values()));

			request.setAttribute("books", books);
			request.setAttribute("availabilities", availabilities);

		} catch (DatabaseOperationException e) {
			e.printStackTrace();
		}
	}

	public void setAtributesAvailabilitySelected(HttpServletRequest request, Book book) {

		request.setAttribute("book", selectedBookAvailability.getTitle());
		request.setAttribute("availability", book.getAvailability());

	}

	public void setAtributesUpdate(HttpServletRequest request) {
		try {
			List<Book> books = new ArrayList<>(bookService.getBooks().stream()
					.filter(b -> b.getAvailability().equals(BookAvailability.AVAILABLE)).toList());

			List<BookCategory> categories = new ArrayList<>(Arrays.asList(BookCategory.values()));

			request.setAttribute("books", books);
			request.setAttribute("categories", categories);

		} catch (DatabaseOperationException e) {
			e.printStackTrace();
		}
	}

	public void setAtributesUpdateSelected(HttpServletRequest request, Book book) {
		request.setAttribute("book", (selectedBookUpdate.getTitle() + " - " + selectedBookUpdate.getAuthor()));
		request.setAttribute("bookId", book.getBookId());
		request.setAttribute("bookTitle", book.getTitle());
		request.setAttribute("author", book.getAuthor());
		request.setAttribute("category", book.getCategory());

	}

}
