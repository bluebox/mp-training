package com.lms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.lms.exceptions.InvalidInputException;
import com.lms.service.ReturnBookServiceInterface;
import com.lms.serviceimpl.ReturnBookServiceImpl;
import com.lms.util.Validator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {

    private final ReturnBookServiceInterface returnBookService = new ReturnBookServiceImpl();
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String mobile = request.getParameter("mobile");

        if ("Fetch Books".equals(action)) {
            try {
                String memberName = Validator.validateAndFetchMemberNameByMobile(mobile, returnBookService);
                List<String> books = returnBookService.getIssuedBooksByMobile(mobile);

                request.setAttribute("memberName", memberName);
                request.setAttribute("books", books);

            } catch (InvalidInputException | SQLException e) {
                request.setAttribute("error", e.getMessage());
            }

        } else if ("Return Book".equals(action)) {
            String bookName = request.getParameter("bookName");
            String status = request.getParameter("status");

            try {
                Validator.validateReturnBookInputs(mobile, bookName, status);
                boolean success = returnBookService.returnBook(mobile, bookName, status);

                if (success) {
                    request.setAttribute("message", "Book returned successfully.");
                } else {
                    request.setAttribute("error", "Book return failed. Please try again.");
                }

            } catch (InvalidInputException e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        
        request.setAttribute("page", "ReturnBook.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
    
}
 


