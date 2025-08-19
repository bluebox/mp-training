package com.lms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.exceptions.DBConstrainsException;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.service.impl.BookServiceImpl;


@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService;
	
	public void init() {
		bookService = new BookServiceImpl();
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println(request.getParameter("action"));
		if("AddBook".equals(request.getParameter("action"))){
			
			Book book = new Book();
			
			book.setBook_Title(request.getParameter("bookTitle"));
			book.setBook_Author(request.getParameter("author"));
			book.setBook_Category(request.getParameter("category"));
			
			try {
				bookService.addNewBook(book);
				request.setAttribute("alertMessage", "Book Added Successfully.");
			} catch (DBConstrainsException e) {
				request.setAttribute("alertMessage", "Problem adding book : " + e.getMessage());
				System.out.println("couldn't add book " + e.getMessage());
			}
			request.setAttribute("fileToRender", "AddBook.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
