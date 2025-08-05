package com.LibraryManagement.Controller;

import com.LibraryManagement.Models.Book;
import com.LibraryManagement.Models.IssueRecords;
import com.LibraryManagement.Models.Member;
import com.LibraryManagement.Service.Implementation.BookServiceImplementation;
import com.LibraryManagement.Service.Implementation.IssueRecordServiceImplementation;
import com.LibraryManagement.Service.Implementation.MemberServiceImplementation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/ReportsController")
public class ReportsController extends HttpServlet {

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "overdue":
                    handleOverdue(request);
                    break;
                case "categoryCount":
                    handleBooksPerCategory(request);
//                    request.getRequestDispatcher("Reports.jsp").forward(request, response);
                    break;
                case "activeMembers":
                    handleActiveMembers(request);
                    break;
                default:
                   
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

       request.setAttribute("action", action);
    	

        request.getRequestDispatcher("Reports.jsp").forward(request, response);
    }

    private void handleOverdue(HttpServletRequest request) throws Exception {
        List<IssueRecords> allIssues = issueService.getAllIssues();
        List<IssueRecords> overdue = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .filter(issue -> issue.getIssueDate().isBefore(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());

        List<OverdueBook> overdueBooks = new ArrayList<>();
        for (IssueRecords issue : overdue) {
            Book book = bookService.getBookById(issue.getBookId());
            Member member = memberService.fetchMemberById(issue.getMemberId());
            overdueBooks.add(new OverdueBook(book.getTitle(), member.getName(), issue.getIssueDate()));
        }

        request.setAttribute("overdueBooks", overdueBooks);
        
    }

    private void handleBooksPerCategory(HttpServletRequest request) {
    	//System.out.println("gopi");
        List<Book> allBooks = bookService.getAllBooks();

        Map<String, Long> countByCategory = allBooks.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));

        List<CategoryCount> categoryCounts = countByCategory.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(entry -> new CategoryCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        
//        for(CategoryCount x:categoryCounts)
//        {
//        	System.out.println(x.category+" " +x.count);
//        	
//        }
//       
        request.setAttribute("categoryCounts", categoryCounts);
        
    }

    private void handleActiveMembers(HttpServletRequest request) throws Exception {
        List<IssueRecords> allIssues = issueService.getAllIssues();

        Map<Integer, Long> memberIssueCount = allIssues.stream()
                .filter(issue -> issue.getReturnDate() == null)
                .collect(Collectors.groupingBy(IssueRecords::getMemberId, Collectors.counting()));

        List<ActiveMember> activeMembers = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : memberIssueCount.entrySet()) {
            Member member = memberService.fetchMemberById(entry.getKey());
            activeMembers.add(new ActiveMember(member.getName(), entry.getValue().intValue()));
        }

        request.setAttribute("activeMembers", activeMembers);
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

        public String getTitle() { return title; }
        public String getMemberName() { return memberName; }
        public LocalDate getIssueDate() { return issueDate; }
    }

    public static class CategoryCount {
        private final String category;
        private final Long count;

        public CategoryCount(String category, Long count) {
            this.category = category;
            this.count = count;
        }

        public String getCategory() { return category; }
        public Long getCount() { return count; }
    }

    public static class ActiveMember {
        private final String name;
        private final int booksIssued;

        public ActiveMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }

        public String getName() { return name; }
        public int getBooksIssued() { return booksIssued; }
    }
}
