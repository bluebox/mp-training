package library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService;

	public AddBookServlet() {
		super();
		this.bookService = new BookServiceImpl();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("categories", BookCategory.values());
		request.setAttribute("statuses", BookStatus.values());
		request.setAttribute("availabilities", BookAvailability.values());
		request.getRequestDispatcher("/addBookForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String categoryStr = request.getParameter("category");
		String statusStr = request.getParameter("status");
		String availabilityStr = request.getParameter("availability");
		try {
			String createdBy = "admin";
			Book newBook = new Book(title, author, BookCategory.valueOf(categoryStr), BookStatus.valueOf(statusStr),
					BookAvailability.valueOf(availabilityStr));
			bookService.addBook(newBook, createdBy);
			request.getSession().setAttribute("statusMessage", "Book '" + title + "' added successfully.");
			request.getSession().setAttribute("statusType", "success");
			response.sendRedirect(request.getContextPath() + "/viewBooks");
		} catch (LibraryException e) {
			request.setAttribute("statusMessage", e.getMessage());
			request.setAttribute("statusType", "error");
			request.setAttribute("categories", BookCategory.values());
			request.setAttribute("statuses", BookStatus.values());
			request.setAttribute("availabilities", BookAvailability.values());
			request.getRequestDispatcher("/addBookForm.jsp").forward(request, response);
		} catch (IllegalArgumentException e) {
			request.setAttribute("statusMessage", "Invalid category, status, or availability selected.");
			request.setAttribute("statusType", "error");
			request.setAttribute("categories", BookCategory.values());
			request.setAttribute("statuses", BookStatus.values());
			request.setAttribute("availabilities", BookAvailability.values());
			request.getRequestDispatcher("/addBookForm.jsp").forward(request, response);
		}
	}
}