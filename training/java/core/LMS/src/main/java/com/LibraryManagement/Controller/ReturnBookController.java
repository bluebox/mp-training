package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.IssueRecords;
import com.LibraryManagement.Service.Implementation.IssueRecordServiceImplementation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ReturnBookController")
public class ReturnBookController extends HttpServlet {

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        request.getRequestDispatcher("ReturnBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String bookIdText = request.getParameter("bookId").trim();
        String statusMessage = "";
        String action = request.getParameter("action");

        if ("back".equals(action)) {
           
            response.sendRedirect("IssueAndReturn.jsp");
            return;
        }

        if (bookIdText.isEmpty()) {
            statusMessage = "Book ID is required.";
        } else {
            try {
                int bookId = Integer.parseInt(bookIdText);
                IssueRecords activeIssue = issueService.getActiveIssueByBookId(bookId);

                if (activeIssue == null) {
                    statusMessage = "This book is not currently issued.";
                } else {
                    boolean success = issueService.returnBook(activeIssue.getIssueId());

                    if (success) {
                        statusMessage = "Book returned successfully.";
                        request.setAttribute("statusStyle", "-fx-text-fill: green; -fx-font-weight: bold;");
                    } else {
                        statusMessage = "Return failed. Try again.";
                    }
                }
            } catch (NumberFormatException e) {
                statusMessage = "Book ID must be a number.";
            } catch (Exception e) {
                e.printStackTrace();
                statusMessage = "Error: " + e.getMessage();
            }
        }

        request.setAttribute("statusMessage", statusMessage);
        request.getRequestDispatcher("ReturnBookForm.jsp").forward(request, response);
        
        

    }
}
