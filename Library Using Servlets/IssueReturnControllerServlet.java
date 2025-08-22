package com.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.library.model.IssueRecords;
import com.library.service.IssueRecordServiceImplementation;

@WebServlet("/IssueReturnController")
public class IssueReturnControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("view".equalsIgnoreCase(action)) {
                viewAllIssues(request, response);
            } else if ("issueForm".equalsIgnoreCase(action)) {
                loadIssueForm(request, response);
            } else if ("returnForm".equalsIgnoreCase(action)) {
                loadReturnForm(request, response);
            } else {
                response.sendRedirect("Main.html");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("issue".equalsIgnoreCase(action)) {
                issueBook(request, response);
            } else if ("return".equalsIgnoreCase(action)) {
                returnBook(request, response);
            } else {
                response.sendRedirect("Main.html");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void loadIssueForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Integer> bookIds = issueService.getAvailableBookIds();
        List<Integer> memberIds = issueService.getValidMemberIds();

        request.setAttribute("bookIds", bookIds);
        request.setAttribute("memberIds", memberIds);

        request.getRequestDispatcher("IssueBook.jsp").forward(request, response);
    }

    private void loadReturnForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("ReturnBook.jsp").forward(request, response);
    }

    private void viewAllIssues(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<IssueRecords> issues = issueService.getAllIssues();
        request.setAttribute("issues", issues);

        request.getRequestDispatcher("ViewAllIssues.jsp").forward(request, response);
    }

    private void issueBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        LocalDate issueDate = LocalDate.parse(request.getParameter("issueDate"));

        IssueRecords record = new IssueRecords(bookId, memberId, "I", issueDate);

        boolean success = issueService.issueBook(record);
        System.out.println("Received in Servlet:");
        System.out.println("bookId = " + request.getParameter("bookId"));
        System.out.println("memberId = " + request.getParameter("memberId"));
        System.out.println("issueDate = " + request.getParameter("issueDate"));


        if (success) {
            request.setAttribute("message", "Book issued successfully!");
        } else {
            request.setAttribute("message", "Book could not be issued. It may already be issued.");
        }

        loadIssueForm(request, response);
    }

    private void returnBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        IssueRecords activeIssue = issueService.getActiveIssueByBookId(bookId);

        if (activeIssue == null) {
            request.setAttribute("message", "Book is not currently issued.");
        } else {
            boolean success = issueService.returnBook(activeIssue.getIssueId());
            if (success) {
                request.setAttribute("message", "Book returned successfully!");
            } else {
                request.setAttribute("message", "Failed to return the book.");
            }
        }

        loadReturnForm(request, response);
    }
}
