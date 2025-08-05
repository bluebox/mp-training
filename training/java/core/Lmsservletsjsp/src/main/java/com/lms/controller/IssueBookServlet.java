package com.lms.controller;

import com.lms.model.*;
import com.lms.serviceimpl.IssueBookServiceImpl;
import com.lms.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IssueBookServiceImpl issueBookService = new IssueBookServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String mobile = request.getParameter("mobile");
        String category = request.getParameter("category");

        Member currentMember = null;

        try {
            currentMember = Validator.validateAndFetchMemberByMobile(mobile, issueBookService);
            request.setAttribute("member", currentMember);

            Set<BookCategory> categories = issueBookService.getAllAvailableBooks(null).stream()
                    .filter(book -> book.getStatus() == 'A' && book.getAvailability() == 'A')
                    .map(Book::getBookCategory)
                    .collect(Collectors.toSet());

            List<String> categoryList = categories.stream().map(Enum::toString).toList();
            request.setAttribute("categories", categoryList);

            if (category != null && !category.isBlank()) {
                List<Book> books = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(category.toUpperCase()));
                List<String> bookTitles = books.stream()
                        .filter(b -> b.getAvailability() == 'A' && b.getStatus() == 'A')
                        .map(Book::getBookTitle)
                        .toList();
                request.setAttribute("books", bookTitles);
            }

            if ("Issue Book".equals(action)) {
                String bookTitle = request.getParameter("bookTitle");
                String dueDateStr = request.getParameter("dueDate");

                if (bookTitle == null || dueDateStr == null || category == null) {
                    throw new Exception("All fields are required.");
                }

                LocalDate dueDate = LocalDate.parse(dueDateStr);
                if (dueDate.isBefore(LocalDate.now())) {
                    throw new Exception("Due date cannot be in the past.");
                }

                List<Book> books = issueBookService.getAvailableBooksByCategory(BookCategory.valueOf(category.toUpperCase()));
                Book selectedBook = books.stream()
                        .filter(book -> book.getBookTitle().equals(bookTitle))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Selected book not found."));

                IssueBook issue = new IssueBook();
                issue.setMemberId(currentMember.getMemberId());
                issue.setBookId(selectedBook.getBookId());
                issue.setIssueDate(LocalDate.now());
                issue.setReturnDate(dueDate);

                if (issueBookService.issueBook(issue)) {
                    selectedBook.setAvailability('U');
                    issueBookService.updateBookAvailability(selectedBook.getBookId(), 'U');
                    request.setAttribute("message", "Book issued successfully.");
                } else {
                    request.setAttribute("error", "Book issue failed.");
                }
            }

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("currentDate", LocalDate.now());
        request.setAttribute("page", "IssueBook.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
}
