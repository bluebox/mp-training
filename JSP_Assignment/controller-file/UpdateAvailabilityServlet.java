package com.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;


@WebServlet("/updateAvailabilityServlet")
public class UpdateAvailabilityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookServiceImplementation bookService = new BookServiceImplementation();
	private int id;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookIdParam = request.getParameter("bookId");

        if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
            request.setAttribute("error", "No bookId provided");
            request.getRequestDispatcher("/viewBooksServlet").forward(request, response);
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdParam);
            Book book = bookService.getBookById(bookId);

            if (book == null) {
                request.setAttribute("error", "Book not found for ID: " + bookId);
                request.getRequestDispatcher("/viewBooksServlet").forward(request, response);
                return;
            }

            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UpdateAvailability.jsp");
            dispatcher.forward(request, response);
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching book details: " + e.getMessage());
            request.getRequestDispatcher("/viewBooksServlet").forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("bookId"));
		char availability=request.getParameter("availability").charAt(0);
		
		 try {
		        bookService.updateAvailability(id,availability);
		        RequestDispatcher reqdis=request.getRequestDispatcher("/WEB-INF/results/UpdateAvailabilityResult.jsp");
				reqdis.forward(request, response);

		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "An error occurred while updating the Availability.");
		        request.getRequestDispatcher("/WEB-INF/results/UpdateAvailabilityResult.jsp").forward(request, response);
		    }
		    
		
		
	}
}
