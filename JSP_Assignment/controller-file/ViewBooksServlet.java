package com.library.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

@WebServlet("/viewBooksServlet")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final BookServiceImplementation bookService = new BookServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Book> books = bookService.getAllBooks();
            request.setAttribute("books", books);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to load books: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewBooks.jsp");
        dispatcher.forward(request, response);
    }
}
