package library.controller;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;

    public ReportsServlet() {
        super();
        this.issueService = new IssueServiceImpl();
        this.bookService = new BookServiceImpl();
        this.memberService = new MemberServiceImpl();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType = request.getParameter("type");
        String reportContent = "";

        try {
            if ("booksByCategory".equals(reportType)) {
                reportContent = generateBooksByCategoryReport();
            } else if ("membersWithActiveBooks".equals(reportType)) {
                reportContent = generateMembersWithActiveBooksReport();
            } else { // Default to overdue books report
                reportContent = generateOverdueBooksReport();
            }
            request.setAttribute("reportContent", reportContent);
        } catch (LibraryException e) {
            request.setAttribute("errorMessage", "Database error generating report: " + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("/reportsScreen.jsp").forward(request, response);
    }
    
    private String generateOverdueBooksReport() throws LibraryException {
        List<IssueRecord> overdueRecords = issueService.getOverdueBooks(14);
        if (overdueRecords.isEmpty()) {
            return "No overdue books found.";
        } else {
            StringBuilder sb = new StringBuilder("--- Overdue Books (Issued for >14 days) ---\n\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            for (IssueRecord record : overdueRecords) {
                String bookTitle = "N/A";
                String memberName = "N/A";

                try {
                    Book criteria = new Book();
                    criteria.setBookId(record.getBookId());
                    List<Book> books = bookService.findBooks(criteria);
                    if (!books.isEmpty()) {
                        bookTitle = books.get(0).getTitle();
                    }
                } catch (Exception e) {
                    System.err.println("Error fetching book for report: " + e.getMessage());
                }
                
                try {
                    Member member = memberService.getMemberById(record.getMemberId());
                    if (member != null) {
                        memberName = member.getName();
                    }
                } catch (Exception e) {
                    System.err.println("Error fetching member for report: " + e.getMessage());
                }

                sb.append("Book ID: ").append(record.getBookId()).append("\n");
                sb.append("  Title: ").append(bookTitle).append("\n");
                sb.append("  Issued to: ").append(memberName).append(" (Member ID: ").append(record.getMemberId()).append(")\n");
                sb.append("  Issue Date: ").append(record.getIssueDate().format(formatter)).append("\n");
                sb.append("-------------------------------------------\n");
            }
            return sb.toString();
        }
    }
    
    private String generateBooksByCategoryReport() throws LibraryException {
        List<Book> allBooks = bookService.findBooks(new Book());
        if (allBooks.isEmpty()) {
            return "No books found to categorize.";
        } else {
            Map<String, Long> booksByCategory = allBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getCategory().getDisplayName(), Collectors.counting()));
            
            StringBuilder sb = new StringBuilder("--- Books Per Category ---\n\n");
            booksByCategory.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" books\n"));
            
            return sb.toString();
        }
    }

    private String generateMembersWithActiveBooksReport() throws LibraryException {
        List<Member> membersWithActiveBooks = issueService.getMembersWithActiveBooks();
        if (membersWithActiveBooks.isEmpty()) {
            return "No members currently have active issued books.";
        } else {
            StringBuilder sb = new StringBuilder("--- Members with Active Issued Books ---\n\n");
            membersWithActiveBooks.stream().sorted((m1, m2) -> Integer.compare(m1.getMemberID(), m2.getMemberID()))
                .forEach(member -> sb.append("Member ID: ").append(member.getMemberID()).append(" - Name: ")
                        .append(member.getName()).append("\n"));
            return sb.toString();
        }
    }
}