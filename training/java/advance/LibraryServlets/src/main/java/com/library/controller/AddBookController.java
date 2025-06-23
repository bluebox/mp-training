package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.domain.Book;
import com.library.service.LibraryService;
import com.library.service.impl.LibraryServiceImpl;

@WebServlet("/addBook")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		String status=request.getParameter("status");
		String availability=request.getParameter("availability");
		
		Book book =new Book(title, author,category,status, availability);
		LibraryService service =new LibraryServiceImpl();
		if(service.addBook(book))
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.print("<h1> Book added Sucessfully</h1>");
		}
		
		
		
	}

}
