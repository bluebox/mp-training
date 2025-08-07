package com.dynamic_library_management.controllers;

import java.io.IOException;

import com.dynamic_library_management.domain.BookCategory;
import com.dynamic_library_management.exceptions.DatabaseException;
import com.dynamic_library_management.exceptions.InvalidDetailsException;
import com.dynamic_library_management.services.implementation.BookServiceImplementation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet("/addBookController")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		BookCategory category = BookCategory.getEnumConstant(request.getParameter("category"));
		try {
			new BookServiceImplementation().validateAddBook(title, author, category);
			setSuccess(true);
			setMessage("Book Added Succesfully...");
//			ResponseHandler.showResponse(message, "Book Added Succesfully...", Color.GREEN);
		} catch (InvalidDetailsException | DatabaseException e) {
			setSuccess(false);
			setMessage(e.getMessage());
//			ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
		} finally {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/addBook.jsp");
			request.setAttribute("message", getMessage());
			request.setAttribute("success", isSuccess());
			if (isSuccess()) {
				requestDispatcher.forward(request, response);
			} else {
				requestDispatcher.include(request, response);
			}

		}
	}

}
