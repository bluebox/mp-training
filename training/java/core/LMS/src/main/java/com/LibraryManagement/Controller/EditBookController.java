package com.LibraryManagement.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;

/**
 * Servlet implementation class EditBookController
 */
@WebServlet("/EditBookController")
public class EditBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   BookServiceImplementation bsi=new BookServiceImplementation();
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int bookId = Integer.parseInt(request.getParameter("id"));

	       
	        Book book =new Book();
			try {
				book = bsi.getBookById(bookId);
				 request.setAttribute("book", book);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("EditBookForm.jsp");
			        dispatcher.forward(request, response);
			} catch (Exception e) {
			
				e.printStackTrace();
			} 

	    }
	


}
