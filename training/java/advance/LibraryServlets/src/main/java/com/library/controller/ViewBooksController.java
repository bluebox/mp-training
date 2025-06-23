package com.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.LibraryService;
import com.library.service.impl.LibraryServiceImpl;

/**
 * Servlet implementation class ViewBooksController
 */
@WebServlet("/viewBooks")
public class ViewBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LibraryService service=new LibraryServiceImpl();
		request.setAttribute("booksList", service.fetchAllBooks());
		request.getRequestDispatcher("UI/ViewBooks.jsp").forward(request, response);
	}

}
