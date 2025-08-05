package com.LibraryManagement.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;


/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddBookController")
public class AddBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddBookController() {
        super();
    }
    BookServiceImplementation bsi=new BookServiceImplementation();

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
////		response.sendRedirect("AddBook.jsp");
////		request.getRequestDispatcher("AddBook.jsp").forward(request, response);
//		
//	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addtodb".equalsIgnoreCase(action)) {
            String title = request.getParameter("Title");
            String author = request.getParameter("Author");
            String category = request.getParameter("Category");

            if (title != null && !title.trim().isEmpty()
                    && author != null && !author.trim().isEmpty()
                    && category != null && !category.trim().isEmpty()) {

                Book book = new Book();
                book.setTitle(title.trim());
                book.setAuthor(author.trim());
                book.setCategory(category.trim());

                 bsi.addBook(book); 

           
                    request.setAttribute("message", "Book added successfully!");
                    request.setAttribute("color", "green");
                }

            
        else {
                request.setAttribute("message", "Invalid details. Please fill all fields.");
                request.setAttribute("color", "red");
            }

            request.getRequestDispatcher("AddBook.jsp").forward(request, response);
        }

        else if ("backpage".equalsIgnoreCase(action)) {
            request.getRequestDispatcher("MainPage.jsp").forward(request, response);
        }
    }


}
