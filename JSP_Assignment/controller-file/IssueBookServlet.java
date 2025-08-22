package com.library.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MemberServiceImplementation memberService = new MemberServiceImplementation();
    private BookServiceImplementation bookService = new BookServiceImplementation();
    private IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Member> members = memberService.fetchAllMembers();
            List<Book> books = bookService.getAvailableBooks();

            req.setAttribute("members", members);
            
            req.setAttribute("books", books);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("statusMessage", "Error loading data: " + e.getMessage());
            req.setAttribute("statusColor", "red");
        }

        req.getRequestDispatcher("IssueBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
        String memberIdStr = req.getParameter("memberId");
        String bookIdStr = req.getParameter("bookId");

        String statusMessage;
        String statusColor = "red";

        if (memberIdStr == null || bookIdStr == null || memberIdStr.isEmpty() || bookIdStr.isEmpty()) {
            statusMessage = "Please select both member and book.";
        } else {
            try {
                int memberId = Integer.parseInt(memberIdStr);
                int bookId = Integer.parseInt(bookIdStr);

                Member member = memberService.fetchMemberById(memberId);
                if (member == null) {
                    statusMessage = "Invalid Member ID.";
                } else {
                    // Issue book
                    IssueRecord record = new IssueRecord(bookId, memberId, 'I', LocalDate.now());
                    boolean success = issueService.issueBook(record);

                    if (success) {
                        statusMessage = "Book issued successfully!";
                        statusColor = "green";
                    } else {
                        statusMessage = "Issuing failed.";
                    }
                }

            } catch (NumberFormatException e) {
                statusMessage = "Member ID and Book ID must be numbers.";
            } catch (Exception e) {
                e.printStackTrace();
                statusMessage = "Error: " + e.getMessage();
            }
        }

        req.setAttribute("statusMessage", statusMessage);
        req.setAttribute("statusColor", statusColor);

        doGet(req, resp);
    }
}
