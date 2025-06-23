package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.LibraryService;
import com.library.service.impl.LibraryServiceImpl;

/**
 * Servlet implementation class ReturnBookController
 */
@WebServlet("/returnBook")
public class ReturnBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt( request.getParameter("bookId"));
		int memberId=Integer.parseInt( request.getParameter("memberId"));
		
		
		LibraryService service=new LibraryServiceImpl();
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		if(service.returnBook(bookId,memberId))
		{
			out.println("<h1>Book Sucessfully Returned</h1>");
			
		}
		else
		{
			out.println("<h2>Please enter valid details</h2>");
		}
	}

}
