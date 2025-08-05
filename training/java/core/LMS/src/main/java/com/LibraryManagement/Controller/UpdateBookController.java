package com.LibraryManagement.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.LibraryManagement.Dao.Interface.BookDAO;
import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/UpdateBookController")
public class UpdateBookController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookServiceImplementation bsi=new BookServiceImplementation();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        char status = request.getParameter("status").charAt(0);
//        char availability = request.getParameter("availability").charAt(0);

        Book book = new Book(bookId, title, author, category, status, 'A');

       
        bsi.updateBook(book);
        List<Book> books = bsi.getAllBooks();
        request.setAttribute("bookList", books);
	request.getRequestDispatcher("ViewAllBooks.jsp").forward(request, response);

      
      //  response.sendRedirect("ViewAllBooks.jsp");
    }
}
