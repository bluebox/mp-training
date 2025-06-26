package com.library.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Impl.BooksDao;
import com.library.domain.Availability;
import com.library.domain.Book;
import com.library.domain.Status;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BooksDao booksDao = new BooksDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("Search Book By Name".equals(request.getParameter("type"))) {
			String bookName = request.getParameter("book");
			List<Book> books = null;

			try {
				books = booksDao.searchByName(bookName);
			} catch (SQLException e) {
				System.out.println("Error found in searching books in books dao");
			}
			System.out.println(books);
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BooksView.jsp");
			dispatcher.forward(request, response);
		} else if ("View All Books".equals(request.getParameter("type"))) {
			List<Book> books = null;

			books = booksDao.viewAllBooks();
			System.out.println(books);
			request.setAttribute("books", books);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BooksView.jsp");
			dispatcher.forward(request, response);

		} else if ("Add Book".equals(request.getParameter("type"))) {
			try {
				Book book = new Book(request.getParameter("name"), request.getParameter("Author"),
						request.getParameter("Category"),
						request.getParameter("Status").equals("Active") ? Status.ACTIVE : Status.INACTIVE,
						request.getParameter("Availability").equals("Available") ? Availability.AVAILABLE
								: Availability.ISSUED);
				booksDao.createBook(book);
				String message = "Book added successfully!";
				request.setAttribute("alertMessage", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Add_book.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				System.out.println("Error");
			}
		}
	}
}
