package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String status = request.getParameter("status");

        try {
            int bookId = Integer.parseInt(idStr);

            Book book = new Book();
            book.setBookId(bookId);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setStatus(status.charAt(0));

            bookService.updateBookDetails(book);

            Book updatedBook = bookService.getBookById(bookId); 
            request.setAttribute("book", updatedBook);
            request.setAttribute("message", "Book updated successfully");
            request.getRequestDispatcher("update-book.jsp").forward(request, response);

        } catch (Exception e) {
            Book book = new Book();
            book.setBookId(Integer.parseInt(idStr));
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setStatus(status.charAt(0));

            request.setAttribute("book", book);
            request.setAttribute("error", "Error updating book: " + e.getMessage());
            request.getRequestDispatcher("update-book.jsp").forward(request, response);
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = bookService.getBookById(bookId);
            request.setAttribute("book", book);
            request.getRequestDispatcher("update-book.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("ViewBooksServlet?error=Book+not+found");
        }
    }
}