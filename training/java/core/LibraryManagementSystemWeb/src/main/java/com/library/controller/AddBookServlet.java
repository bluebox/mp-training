package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

    private final BookServiceImplementation bsi = new BookServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        request.setAttribute("title", title);
        request.setAttribute("author", author);
        request.setAttribute("category", category);

        if (title == null || title.trim().isEmpty() ||
            author == null || author.trim().isEmpty() ||
            category == null || category.trim().isEmpty()) {

            request.setAttribute("message", "All fields are required.");
            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
            return;
        }

        try {
            Book book = new Book(0, title.trim(), author.trim(), category.trim(), 'A', 'A');

            if (bsi.doesBookExist(title.trim(), author.trim(), category.trim())) {
                request.setAttribute("message", "Book already exists.");
                request.setAttribute("messageColor", "red");
                request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
                return;
            }

            bsi.addBook(book);
            request.setAttribute("message", "Book added successfully.");
            request.setAttribute("messageColor", "green");

            request.setAttribute("title", "");
            request.setAttribute("author", "");
            request.setAttribute("category", "");

            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Failed to add book: " + e.getMessage());
            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/AddBookForm.jsp").forward(request, response);
        }
    }
}
