package com.library.controller;

import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Member;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;
import com.library.dto.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<IssueRecord> allIssues = issueService.getAllIssues();
            List<Book> allBooks = bookService.getAllBooks();

            List<IssueRecordRow> overdueList = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(10)))
                    .map(issue -> {
                        Book book = null;
						try {
							book = bookService.getBookById(issue.getBookId());
						} catch (Exception e) {
							e.printStackTrace();
						}
                        return new IssueRecordRow(issue.getBookId(), book.getTitle(), issue.getMemberId(), issue.getIssueDate());
                    })
                    .collect(Collectors.toList());

            Map<String, Long> countByCategory = allBooks.stream()
                    .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

            List<CategoryCountRow> categoryRows = countByCategory.entrySet().stream()
                    .map(e -> new CategoryCountRow(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());

            Set<Integer> activeMemberIds = allIssues.stream()
                    .filter(issue -> issue.getReturnDate() == null)
                    .map(IssueRecord::getMemberId)
                    .collect(Collectors.toSet());

            List<MemberRow> memberRows = new ArrayList<>();
            for (int id : activeMemberIds) {
                Member m = memberService.fetchMemberById(id);
                memberRows.add(new MemberRow(m.getMemberId(), m.getName(), m.getEmail()));
            }

            request.setAttribute("overdueBooks", overdueList);
            request.setAttribute("categoryCounts", categoryRows);
            request.setAttribute("activeMembers", memberRows);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/Reports.jsp").forward(request, response);
    }
}