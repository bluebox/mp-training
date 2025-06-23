package com.library.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.domain.IssueRecord;
import com.library.service.LibraryService;
import com.library.service.impl.LibraryServiceImpl;

@WebServlet("/issueBook")
public class IssueBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookId=Integer.parseInt( request.getParameter("bookId"));
		int memberId=Integer.parseInt( request.getParameter("memberId"));
		
		IssueRecord record=new IssueRecord(bookId, memberId);
		
		LibraryService service=new LibraryServiceImpl();
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		if(service.issueBook(record))
		{
			out.println("<h1>Book Sucessfully Issued</h1>");
			
		}
		else
		{
			out.println("<h2>Please enter valid details</h2>");
		}
		
	}

}
