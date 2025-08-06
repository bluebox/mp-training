package com.LibraryManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.service.Implementation.BookServiceImplementation;

@WebServlet("/bookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static BookServiceImplementation bookService = new BookServiceImplementation();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("view".equals(action)) {
			handleView(request, response);
		} else if ("update".equals(action)) {
			handleUpdateForm(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			if ("add".equals(action)) {
				handleAdd(request, response);
			} else if ("view".equals(action)) {
				handleView(request, response);
			} else if ("update".equals(action)) {
				handleLoadUpdateForm(request, response);
			} else if ("saveUpdate".equals(action)) {
				handleSubmitUpdate(request, response);
			} else if ("changeAvailability".equals(action)) {
				handleChangeAvailability(request, response);
			} else if ("updateAvail".equals(action)) {
				handleSubmitAvailabilityUpdate(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/views/Books/books.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
		}
	}

	private void handleAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		boolean isValid = true;

		if (title == null || title.trim().isEmpty()) {
			request.setAttribute("titleError", "Invalid title");
			isValid = false;
		}
		if (author == null || author.trim().isEmpty()) {
			request.setAttribute("authorError", "Invalid author");
			isValid = false;
		}
		if (category == null || category.equals("Category")) {
			request.setAttribute("categoryError", "Please select a category");
			isValid = false;
		}

		if (!isValid) {
			request.setAttribute("title", title);
			request.setAttribute("author", author);
			request.setAttribute("category", category);
			request.setAttribute("messageColor", "red");
			request.getRequestDispatcher("/views/Books/addBook.jsp").forward(request, response);
		} else {
			Book book = new Book(title, author, category);
			int id = bookService.addBook(book);
			request.setAttribute("message", "Successfully added the book. Book ID is: " + id);
			request.setAttribute("messageColor", "green");
			request.setAttribute("title", "");
			request.setAttribute("author", "");
			request.setAttribute("category", "Category");
			request.getRequestDispatcher("/views/Books/addBook.jsp").forward(request, response);
		}
	}

	private void handleView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> books = bookService.getAllBooks();
		request.setAttribute("bookList", books);
		request.getRequestDispatcher("/views/Books/viewBooks.jsp").forward(request, response);
	}

	private void handleUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = bookService.getBookById(bookId);
			if (book != null) {
				request.setAttribute("book", book);
				request.getRequestDispatcher("/views/Books/updateBook.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Book not found.");
				request.setAttribute("messageColor", "red");
				handleView(request, response);
			}
		} catch (Exception e) {
			throw new ServletException("Failed to load update form", e);
		}
	}

	private void handleLoadUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookIdStr = request.getParameter("bookId");
		try {
			int bookId = Integer.parseInt(bookIdStr);
			Book book = bookService.getBookById(bookId);
			if (book != null) {
				request.setAttribute("book", book);
				request.getRequestDispatcher("/views/Books/updateBook.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Book not found");
				request.setAttribute("messageColor", "red");
				handleView(request, response);
			}
		} catch (NumberFormatException e) {
			request.setAttribute("message", "Invalid Book ID");
			request.setAttribute("messageColor", "red");
			handleView(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void handleSubmitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookIdStr = request.getParameter("bookId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String statusStr = request.getParameter("status");

		boolean isValid = true;

		if (title == null || title.trim().isEmpty()) {
			request.setAttribute("titleError", "Invalid title");
			isValid = false;
		}
		if (author == null || author.trim().isEmpty()) {
			request.setAttribute("authorError", "Invalid author");
			isValid = false;
		}
		if (category == null || category.equals("Category")) {
			request.setAttribute("categoryError", "Please select a category");
			isValid = false;
		}
		if (statusStr == null || statusStr.length() != 1) {
			request.setAttribute("statusError", "Invalid status");
			isValid = false;
		}

		int bookId = -1;
		try {
			bookId = Integer.parseInt(bookIdStr);
		} catch (NumberFormatException e) {
			request.setAttribute("message", "Invalid Book ID");
			isValid = false;
		}

		if (!isValid) {
			try {
				Book book = bookService.getBookById(bookId);
				if (book != null) {
					request.setAttribute("book", book);
				}
			} catch (Exception e) {
			}
			request.setAttribute("messageColor", "red");
			request.getRequestDispatcher("/views/Books/updateBook.jsp").forward(request, response);
			return;
		}

		try {
			Book existingBook = bookService.getBookById(bookId);
			if (existingBook == null) {
				request.setAttribute("message", "Book not found for update.");
				request.setAttribute("messageColor", "red");
				handleView(request, response);
				return;
			}

			boolean noChanges = title.trim().equals(existingBook.getTitle().trim())
					&& author.trim().equals(existingBook.getAuthor().trim())
					&& category.equals(existingBook.getCategory()) && statusStr.charAt(0) == existingBook.getStatus();

			if (noChanges) {
				request.setAttribute("book", existingBook);
				request.setAttribute("message", "No changes in the details");
				request.setAttribute("messageColor", "red");
				request.getRequestDispatcher("/views/Books/updateBook.jsp").forward(request, response);
				return;
			}
			if(!noChanges) {
			existingBook.setTitle(title);
			existingBook.setAuthor(author);
			existingBook.setCategory(category);
			existingBook.setStatus(statusStr.charAt(0));

			bookService.updateBook(existingBook);

			request.setAttribute("message", "details updated");
			request.setAttribute("messageColor", "green");
			request.getRequestDispatcher("/views/Books/updateBook.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath() + "/bookController?action=view");
			}
		} catch (Exception e) {
			throw new ServletException("Failed to update book", e);
		}
	}

	private void handleChangeAvailability(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        try {
            int bookId = Integer.parseInt(bookIdStr);
            Book book = bookService.getBookById(bookId);
            if (book != null) {
                request.setAttribute("book", book);
                request.getRequestDispatcher("/views/Books/UpdateAvailability.jsp").forward(request, response);
            }
            else {
                request.setAttribute("message", "Book not found");
                request.setAttribute("messageColor", "red");
                handleView(request, response);}
        } catch (Exception e) {
        	throw new ServletException(e);
        }}

	private void handleSubmitAvailabilityUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookIdStr = request.getParameter("bookId");
		String availabilityStr = request.getParameter("availability");

		try {
			int bookId = Integer.parseInt(bookIdStr);
			char newAvailability = availabilityStr.charAt(0);
			Book book = bookService.getBookById(bookId);
			if (book != null) {
				boolean noChanges = ( newAvailability == book.getStatus());
				if(noChanges) {
					request.setAttribute("message", "No changes in availability.");
					request.setAttribute("messageColor", "red");
	                request.getRequestDispatcher("/views/Books/UpdateAvailability.jsp").forward(request, response);

				}
				else {
				book.setAvailability(newAvailability);
				bookService.updateAvailability(book);
				request.setAttribute("message", "Made changes in availability.");
				request.setAttribute("messageColor", "green");
                request.getRequestDispatcher("/views/Books/UpdateAvailability.jsp").forward(request, response);
				}
				
			}
			//response.sendRedirect(request.getContextPath() + "/bookController?action=view");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Error updating availability.");
			request.setAttribute("messageColor", "red");
			handleView(request, response);
		}
	}
}