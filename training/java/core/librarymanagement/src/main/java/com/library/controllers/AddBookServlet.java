package com.library.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.BookService;
import com.library.model.Book;
import com.library.util.DBConnection;
import com.library.service.BookServiceImpl;
import com.library.dao.BookDAOImpl;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBConnection.getConnection();
            bookService = new BookServiceImpl(new BookDAOImpl(conn));
        } catch (Exception e) {
            throw new ServletException("DB connection failed: " + e.getMessage());
        }
    }

    // ✅ Your code goes here:
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // START: Validation and book creation
        String title = req.getParameter("title").trim();
        String author = req.getParameter("author").trim();
        String category = req.getParameter("category").trim();
        String status = req.getParameter("status");

        if (title.isEmpty() || author.isEmpty() || category.isEmpty() || status == null) {
            req.setAttribute("message", "All fields are required.");
            req.getRequestDispatcher("addBook.jsp").forward(req, res);
            return;
        }

        try {
            char availability;

            if ("inactive".equals(status)) {
                availability = 'Y'; // Unavailable if status is inactive
            } else {
                availability = 'N'; // Default available
            }

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setStatus(status.charAt(0));
            book.setAvailability(availability);

            bookService.addBook(book);

            req.setAttribute("message", " Book added successfully.");
        } catch (Exception e) {
            req.setAttribute("message", " Error: " + e.getMessage());
        }

        req.getRequestDispatcher("addBook.jsp").forward(req, res);
        
    }
}
