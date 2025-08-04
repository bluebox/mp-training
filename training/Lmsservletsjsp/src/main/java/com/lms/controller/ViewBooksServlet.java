package com.lms.controller;

import com.lms.model.Book;
import com.lms.serviceimpl.BookServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/viewBooks")
public class ViewBooksServlet extends HttpServlet {

    private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Book> bookList = bookService.getAllBooks();
            request.setAttribute("bookList", bookList);
        } catch (SQLException e) {
            request.setAttribute("error", "Server is down. Please try again later.");
        }

        request.setAttribute("page", "ViewBooks.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
