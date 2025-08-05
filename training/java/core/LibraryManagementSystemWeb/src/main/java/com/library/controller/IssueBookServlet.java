package com.library.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {
    private MemberServiceImplementation memberService = new MemberServiceImplementation();
    private BookServiceImplementation bookService = new BookServiceImplementation();
    private IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> members = null;
		try {
			members = memberService.fetchAllMembers();
		} catch (Exception e) {
			e.printStackTrace();
		}
        List<Book> books = null;
		try {
			books = bookService.getAvailableBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}

        req.setAttribute("members", members);
        req.setAttribute("books", books);

        req.getRequestDispatcher("issueBook.jsp").forward(req, resp);
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

                IssueRecord record = new IssueRecord(bookId, memberId, 'I', LocalDate.now());
                boolean success = issueService.issueBook(record);

                if (success) {
                    statusMessage = "Book issued successfully!";
                    statusColor = "green";
                } else {
                    statusMessage = "Issuing failed.";
                }
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
