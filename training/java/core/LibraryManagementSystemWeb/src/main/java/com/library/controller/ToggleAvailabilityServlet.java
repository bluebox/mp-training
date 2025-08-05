package com.library.controller;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ToggleAvailabilityServlet")
public class ToggleAvailabilityServlet extends HttpServlet {
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            char currentAvailability = request.getParameter("currentAvailability").charAt(0);
            char newAvailability = (currentAvailability == 'A') ? 'I' : 'A';

            bookService.updateAvailability(bookId, newAvailability);

            request.setAttribute("message", "Availability updated successfully!");
            request.setAttribute("redirectURL", "ViewBooksServlet");

            request.getRequestDispatcher("availability-success.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating availability: " + e.getMessage());
            request.getRequestDispatcher("ViewBooksServlet").forward(request, response);
        }
    }
}

