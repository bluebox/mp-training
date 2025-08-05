package com.lms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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


@WebServlet("/ViewBooksServlet")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private List<Book> books;

    public void init() {
        bookService = new BookServiceImpl();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("Update".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String status = request.getParameter("status");
            String availability = request.getParameter("availability");
            Book book = new Book(bookId, title, author, category, status.charAt(0), availability.charAt(0));
            
            String[] categories = {"Fiction",
                    "Non-Fiction",
                    "Science",
                    "Technology",
                    "History",
                    "Biography"};
            
            request.setAttribute("book", book);
            request.setAttribute("categories", categories);
            request.setAttribute("fileToRender", "UpdateBook.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        
        else if ("UpdateBook".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("BookId"));
            Book book = bookService.getBookById(bookId);
            book.setBook_Title(request.getParameter("title").trim());
            book.setBook_Author(request.getParameter("author").trim());
            book.setBook_Category(request.getParameter("category").trim());
            book.setBook_Status(request.getParameter("status").trim().charAt(0));
            
            try {
				bookService.updateBook(book);
				books = bookService.getAllBooks();
	            request.setAttribute("booksList", books);
	            request.setAttribute("fileToRender", "ViewBooks.jsp");
	            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	            rd.forward(request, response);
			} catch (DBConstrainsException e) {
				request.setAttribute("fileToRender", "ViewBooks.jsp");
	            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	            rd.forward(request, response);
			}
            
        }
        
        else if ("Cancel".equals(action)) {
            books = bookService.getAllBooks();
            request.setAttribute("booksList", books);
            request.setAttribute("fileToRender", "ViewBooks.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        
        else {
            books = bookService.getAllBooks();
            request.setAttribute("booksList", books);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
