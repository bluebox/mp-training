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
 * Servlet implementation class SavaAvailabilityController
 */
@WebServlet("/SaveAvailabilityController")
public class SaveAvailabilityController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	BookServiceImplementation bsi=new BookServiceImplementation();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        char availability = request.getParameter("availability").charAt(0);

        Book book = new Book();
        book.setBookId(bookId);
        book.setAvailability(availability);

        bsi.updateAvailability(book);
        List<Book> books = bsi.getAllBooks();
        request.setAttribute("bookList", books);
	request.getRequestDispatcher("ViewAllBooks.jsp").forward(request, response);

        
       // response.sendRedirect("ViewAllBooks.jsp");
    }
}

