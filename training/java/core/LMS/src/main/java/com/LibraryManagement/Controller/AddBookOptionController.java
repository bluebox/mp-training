package com.LibraryManagement.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;




/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddBookOptionController")
public class AddBookOptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public AddBookOptionController() {
        super();
    }
   

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
////		response.sendRedirect("AddBook.jsp");
////		request.getRequestDispatcher("AddBook.jsp").forward(request, response);
//		
//	}
	private final BookServiceImplementation bookService = new BookServiceImplementation();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		String action=request.getParameter("action");
		if("addbook".equalsIgnoreCase(action)) {
		request.getRequestDispatcher("AddBook.jsp").forward(request, response);
		}
		 if("viewallbooks".equalsIgnoreCase(action))
		{
			 List<Book> books = bookService.getAllBooks();
	            request.setAttribute("bookList", books);
			request.getRequestDispatcher("ViewAllBooks.jsp").forward(request, response);
		}
//		 if("edit")
//		 {
//			 
//		 }
	}

}
