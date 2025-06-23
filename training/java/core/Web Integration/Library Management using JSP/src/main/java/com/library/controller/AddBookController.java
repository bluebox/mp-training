package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.Book;
import com.library.service.BookService;

@WebServlet("/AddBookController")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("bookId"));
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		String s=BookService.add(new Book(id,title,author,category));
		if(s=="Insertion is done"){
			response.sendRedirect("http://localhost:8080/Library_Management_using_JSP/ShowBooks");
		}
		else {
			PrintWriter out=response.getWriter();
			out.print("<h1>"+s+"</h1>");
		}
	}
}
