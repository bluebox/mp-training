package com.LibraryManagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.Implementation.BookServiceImplementation;
import com.LibraryManagement.service.Implementation.IssueRecordsServiceImplementation;
import com.LibraryManagement.service.Implementation.MemberServiceImplementation;

@WebServlet("/ReportsController")
public class ReportsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IssueRecordsServiceImplementation issueService = new IssueRecordsServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IssueRecords> allIssues=null;
		try {
			allIssues = issueService.getAllIssues();
		} catch (Exception e) {
			e.printStackTrace();
		}
        List<Book> allBooks = bookService.getAllBooks();

        List<OverdueBook> overdueList = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(10)))
                .map(issue -> {
                    Book book = null;
                    Member member = null;
					try {
						book = bookService.getBookById(issue.getBookId());
						member = memberService.fetchMemberById(issue.getMemberId());
					} catch (Exception e) {
						e.printStackTrace();
					}
                    return new OverdueBook(book.getTitle(), member.getName(), issue.getIssueDate());
                })
                .collect(Collectors.toList());

        Map<String, Long> countByCategory = allBooks.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

        List<CategoryCount> categoryCountList = countByCategory.entrySet().stream()
                .map(e -> new CategoryCount(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        Map<Integer, Long> memberIssueCount = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .collect(Collectors.groupingBy(IssueRecords::getMemberId, Collectors.counting()));

        List<ActiveMember> activeMembersList = memberIssueCount.entrySet().stream()
                .map(e -> {
                    Member member=null;
					try {
						member = memberService.fetchMemberById(e.getKey());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                    return new ActiveMember(member.getName(), e.getValue().intValue());
                })
                .collect(Collectors.toList());

        request.setAttribute("overdueList", overdueList);
        request.setAttribute("categoryCountList", categoryCountList);
        request.setAttribute("activeMembersList", activeMembersList);
        request.getRequestDispatcher("/views/Reports/reports.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public static class OverdueBook {
        private final String title;
        private final String memberName;
        private final LocalDate issueDate;

        public OverdueBook(String title, String memberName, LocalDate issueDate) {
            this.title = title;
            this.memberName = memberName;
            this.issueDate = issueDate;
        }

        public String getTitle() {
            return title;
        }

        public String getMemberName() {
            return memberName;
        }

        public LocalDate getIssueDate() {
            return issueDate;
        }
    }

    public static class CategoryCount {
        private final String category;
        private final Long count;

        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() {
            return category;
        }

        public Long getCount() {
            return count;
        }
    }

    public static class ActiveMember {
        private final String name;
        private final int booksIssued;

        public ActiveMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }

        public String getName() {
            return name;
        }

        public int getBooksIssued() {
            return booksIssued;
        }
    }
}
