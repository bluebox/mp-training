package com.LibraryManagement.controller;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.Implementation.IssueRecordsServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/IssueReturnController")
public class IssueReturnController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IssueRecordsServiceImplementation issueService = new IssueRecordsServiceImplementation();

    private void loadLists(HttpServletRequest request) throws Exception {
        List<Integer> bookList = issueService.getAvailableBookIds();
        List<Book> books=issueService.getAvailabeBooks();
        List<Integer> memberList = issueService.getValidMemberIds();
        request.setAttribute("books", books);
        request.setAttribute("bookList", bookList);
        request.setAttribute("memberList", memberList);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("returnForm".equalsIgnoreCase(action)) {
                request.getRequestDispatcher("/views/IssueReturn/returnBook.jsp").forward(request, response);
            } else if ("viewAllIssues".equalsIgnoreCase(action)) {
                handleViewAllIssues(request, response);
            } else {
                loadLists(request);
                request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error loading page: " + e.getMessage());
            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("issueBook".equalsIgnoreCase(action)) {
                handleIssueBook(request, response);
            } else if ("return".equalsIgnoreCase(action)) {
                handleReturnBook(request, response);
            } else if ("viewAllIssues".equalsIgnoreCase(action)) {
                handleViewAllIssuesLoad(request, response);
            } else {
                loadLists(request);
                request.setAttribute("message", "Invalid action.");
                request.setAttribute("messageColor", "red");
                request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Internal error: " + e.getMessage());
            request.setAttribute("messageColor", "red");
            try {
				loadLists(request);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
        }
    }

    private void handleViewAllIssuesLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
            List<IssueRecords> allIssues = issueService.getAllIssues();
            request.setAttribute("issueList", allIssues);
            request.getRequestDispatcher("/views/IssueReturn/viewAllIssues.jsp").forward(request, response);

			} catch (Exception e) {
				request.setAttribute("message", "Error retrieving issue records.");
	            request.setAttribute("messageColor", "red");
	            request.getRequestDispatcher("/views/IssueReturn/viewAllIssues.jsp").forward(request, response);
	            e.printStackTrace();
			}
	}

	private void handleIssueBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            loadLists(request);
            String bookIdStr = request.getParameter("bookId");
            String memberIdStr = request.getParameter("memberId");
            String issueDateStr = request.getParameter("issueDate");

            boolean hasError = false;

            if (bookIdStr == null || bookIdStr.trim().isEmpty()) {
                request.setAttribute("bookIdError", "Book ID is required");
                hasError = true;
            }
            if (memberIdStr == null || memberIdStr.trim().isEmpty()) {
                request.setAttribute("memberIdError", "Member ID is required");
                hasError = true;
            }
            if (issueDateStr == null || issueDateStr.trim().isEmpty()) {
                request.setAttribute("issueDateError", "Issue date is required");
                hasError = true;
            }

            int bookId = 0;
            int memberId = 0;
            LocalDate issueDate = null;

            if (!hasError) {
                try {
                    bookId = Integer.parseInt(bookIdStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("bookIdError", "Invalid Book ID");
                    hasError = true;
                }

                try {
                    memberId = Integer.parseInt(memberIdStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("memberIdError", "Invalid Member ID");
                    hasError = true;
                }

                try {
                    issueDate = LocalDate.parse(issueDateStr);
                } catch (DateTimeParseException e) {
                    request.setAttribute("issueDateError", "Invalid Date format");
                    hasError = true;
                }
            }

            if (hasError) {
                request.setAttribute("message", "Please correct the errors below");
                request.setAttribute("messageColor", "red");
                request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
                return;
            }

            IssueRecords record = new IssueRecords(0, bookId, memberId, 'I', issueDate);
            boolean success = issueService.issueBook(record);

            if (success) {
                request.setAttribute("message", "Book issued successfully!");
                request.setAttribute("messageColor", "green");
                request.setAttribute("bookId", "");
                request.setAttribute("memberId", "");
                request.setAttribute("issueDate", "");
            } else {
                request.setAttribute("message", "Failed to issue book. It may already be issued.");
                request.setAttribute("messageColor", "red");
            }

            request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Internal error occurred. Please try again later.");
            request.setAttribute("messageColor", "red");
            try {
				loadLists(request);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            request.getRequestDispatcher("/views/IssueReturn/issueBook.jsp").forward(request, response);
        }
    }

    private void handleReturnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        String errorMessage = null;
        String successMessage = null;

        if (bookIdStr == null || bookIdStr.trim().isEmpty()) {
            errorMessage = "Book ID is required.";
        } else {
            try {
                int bookId = Integer.parseInt(bookIdStr.trim());
                IssueRecords activeIssue = issueService.getActiveIssueByBookId(bookId);

                if (activeIssue == null) {
                    errorMessage = "This book is not currently issued.";
                } else {
                    boolean success = issueService.returnBook(activeIssue.getIssueId());
                    if (success) {
                        successMessage = "Book returned successfully.";
                    } else {
                        errorMessage = "Return failed. Try again.";
                    }
                }
            } catch (NumberFormatException e) {
                errorMessage = "Book ID must be a valid number.";
            } catch (Exception e) {
                e.printStackTrace();
                errorMessage = "Error: " + e.getMessage();
            }
        }

        if (errorMessage != null) {
            request.setAttribute("message", errorMessage);
            request.setAttribute("messageColor", "red");
        }
        if (successMessage != null) {
            request.setAttribute("message", successMessage);
            request.setAttribute("messageColor", "green");
        }

        request.getRequestDispatcher("/views/IssueReturn/returnBook.jsp").forward(request, response);
    }

    private void handleViewAllIssues(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<IssueRecords> allIssues = issueService.getAllIssues();
            request.setAttribute("issueList", allIssues);
            request.getRequestDispatcher("/views/IssueReturn/viewAllIssues.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error retrieving issue records.");
            request.setAttribute("messageColor", "red");
            request.getRequestDispatcher("/views/IssueReturn/viewAllIssues.jsp").forward(request, response);
        }
    }
}
