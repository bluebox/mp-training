package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.IssueRecords;
import com.LibraryManagement.Service.Implementation.IssueRecordServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@WebServlet("/IssueBookController")
public class IssueBookController extends HttpServlet {

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Integer> bookIds = issueService.getAvailableBookIds();
            List<Integer> memberIds = issueService.getValidMemberIds();

            request.setAttribute("bookIds", bookIds);
            request.setAttribute("memberIds", memberIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/IssueBookForm.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String bookIdStr = request.getParameter("bookId");
        String memberIdStr = request.getParameter("memberId");
        String issueDateStr = request.getParameter("issueDate");
        String action = request.getParameter("action");

       
        if ("back".equals(action)) {
            response.sendRedirect("IssueAndReturn.jsp"); 
            return;
        }

        Integer bookId = null;
        Integer memberId = null;
        LocalDate issueDate = null;

        if (bookIdStr == null || bookIdStr.isEmpty()) {
            errors.put("bookIdError", "Book ID is required.");
        } else {
            try {
                bookId = Integer.parseInt(bookIdStr);
            } catch (NumberFormatException e) {
                errors.put("bookIdError", "Invalid Book ID.");
            }
        }

        if (memberIdStr == null || memberIdStr.isEmpty()) {
            errors.put("memberIdError", "Member ID is required.");
        } else {
            try {
                memberId = Integer.parseInt(memberIdStr);
            } catch (NumberFormatException e) {
                errors.put("memberIdError", "Invalid Member ID.");
            }
        }

        if (issueDateStr == null || issueDateStr.isEmpty()) {
            errors.put("issueDateError", "Issue date is required.");
        } else {
            try {
                issueDate = LocalDate.parse(issueDateStr);
            } catch (Exception e) {
                errors.put("issueDateError", "Invalid date format.");
            }
        }

        if (errors.isEmpty()) {
            IssueRecords record = new IssueRecords(bookId, memberId, 'I', issueDate);
            try {
                boolean success = issueService.issueBook(record);
                if (success) {
                    request.setAttribute("message", "Book issued successfully!");
                } else {
                    request.setAttribute("message", "Book could not be issued. It may already be issued.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Server error occurred while issuing the book.");
            }
        }

        try {
            request.setAttribute("bookList", issueService.getAvailableBookIds()); 
            request.setAttribute("memberList", issueService.getValidMemberIds()); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("bookIdError", errors.get("bookIdError"));
        request.setAttribute("memberIdError", errors.get("memberIdError"));
        request.setAttribute("issueDateError", errors.get("issueDateError"));

        request.getRequestDispatcher("/IssueBookForm.jsp").forward(request, response);
    }

}
