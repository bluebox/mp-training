package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceimpl.BookServiceImpl;
import com.lms.util.Validator;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("categories", BookCategory.values());

        req.setAttribute("page", "AddBook.jsp");
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String categoryParam = req.getParameter("category");

        try {
            Book newBook = new Book();
            newBook.setBookTitle(title.trim());
            newBook.setBookAuthor(author.trim());
            newBook.setBookCategory(BookCategory.valueOf(categoryParam));
            newBook.setStatus('A');
            newBook.setAvailability('A');

            Validator.validateBook(newBook);
            bookService.addBook(newBook);

            req.setAttribute("message", "Book added successfully.");
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }

        req.setAttribute("categories", BookCategory.values());
        req.setAttribute("page", "AddBook.jsp");
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
