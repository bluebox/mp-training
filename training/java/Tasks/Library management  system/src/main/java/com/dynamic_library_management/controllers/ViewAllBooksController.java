package com.dynamic_library_management.controllers;

import java.io.IOException;
import java.util.List;

import com.dynamic_library_management.domain.Book;
import com.dynamic_library_management.exceptions.DatabaseException;
import com.dynamic_library_management.services.implementation.BookServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewAllBooksController")
public class ViewAllBooksController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Book> books = null;
		String message;
		String messageColor;

		try {
			books = new BookServiceImplementation().validateViewAllBooks();
			message = "Books Fetched Successfully...";
			messageColor = "green";
		} catch (DatabaseException e) {
			message = "Cannot Fetch Books Data...";
			messageColor = "red";
		}

		req.getSession().setAttribute("books", books);
		resp.sendRedirect("jsp/viewAllBooks.jsp");
	}
}
