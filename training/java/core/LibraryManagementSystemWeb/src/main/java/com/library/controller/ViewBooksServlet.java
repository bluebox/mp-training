package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewBooksServlet")
public class ViewBooksServlet extends HttpServlet {

    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Book> books = bookService.getAllBooks();
            request.setAttribute("books", books);

            String message = request.getParameter("message");
            if (message != null) {
                request.setAttribute("message", message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load books: " + e.getMessage());
        }

        request.getRequestDispatcher("ViewBooks.jsp").forward(request, response);
    }
}
