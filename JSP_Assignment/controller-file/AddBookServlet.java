package com.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.exception.BookAlreadyExistsException;
import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;


@WebServlet("/addBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BookServiceImplementation bookServ=new BookServiceImplementation();
    public AddBookServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher reqdis=request.getRequestDispatcher("/AddBook.jsp");
		reqdis.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String category=request.getParameter("category");
		
		 try {
		        bookServ.addBook(new Book(0,title,author,category,'A','A'));
		        RequestDispatcher reqdis=request.getRequestDispatcher("/WEB-INF/results/AddBookResult.jsp");
				reqdis.forward(request, response);

		    } catch (BookAlreadyExistsException e) {
		    	request.setAttribute("error", e.getMessage());
		        request.getRequestDispatcher("/WEB-INF/results/AddBookResult.jsp").forward(request, response);
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "An error occurred while adding the book.");
		        request.getRequestDispatcher("/WEB-INF/results/AddBookResult.jsp").forward(request, response);
		    }
		    
		
		
	}

}
