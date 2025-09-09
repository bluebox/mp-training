package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceimpl.BookServiceImpl;
import com.lms.util.Validator;
import com.lms.exceptions.InvalidInputException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {

    private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String bookId = request.getParameter("bookId");

        if ("fetch".equals(action)) {
            if (Validator.isEmptyBookId(bookId)) {
                request.setAttribute("error", "Please enter a Book ID.");
            } else {
                try {
                    Book book = bookService.getBookById(bookId.trim());
                    request.setAttribute("book", book);
                } catch (InvalidInputException e) {
                    request.setAttribute("error", e.getMessage());
                }
            }
        } else if ("update".equals(action)) {
            try {
                Book book = new Book();
                book.setBookId(bookId.trim());
                book.setBookTitle(request.getParameter("title").trim());
                book.setBookAuthor(request.getParameter("author").trim());
                book.setBookCategory(BookCategory.valueOf(request.getParameter("category")));
                book.setStatus(request.getParameter("status").charAt(0));
                book.setAvailability(request.getParameter("availability").charAt(0));

                bookService.updateBook(book);
                request.setAttribute("message", "Book updated successfully.");
            } catch (Exception e) {
                request.setAttribute("error", "Update failed: " + e.getMessage());
            }
        }

        request.setAttribute("page", "UpdateBook.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("home.jsp?page=UpdateBook.jsp").forward(req, resp);
    }
}
