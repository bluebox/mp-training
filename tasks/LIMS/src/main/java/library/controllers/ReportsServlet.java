package library.controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
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
import library.validation.BookValidator;

@WebServlet("/reports") 
public class ReportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() throws ServletException {
        super.init();
        this.issueService = new IssueServiceImpl();
        this.bookService = new BookServiceImpl();
        this.memberService = new MemberServiceImpl();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleOverdueBooksReport(request);
        request.getRequestDispatcher("/ReportsScreen.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");

        String action = request.getParameter("action"); 

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No report action specified.");
            request.setAttribute("messageType", "error");
        } else {
            try {
                switch (action) {
                    case "overdueBooks":
                        handleOverdueBooksReport(request);
                        break;
                    case "booksByCategory":
                        handleBooksByCategoryReport(request);
                        break;
                    case "membersWithActiveBooks":
                        handleMembersWithActiveBooksReport(request);
                        break;
                    default:
                        request.setAttribute("message", "Unknown report action: " + action);
                        request.setAttribute("messageType", "error");
                        break;
                }
            } catch (LibraryException e) {
                request.setAttribute("message", "Database error generating report: " + e.getMessage());
                request.setAttribute("messageType", "error");
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("message", "An unexpected error occurred generating report: " + e.getMessage());
                request.setAttribute("messageType", "error");
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/ReportsScreen.jsp").forward(request, response);
    }

    private void handleOverdueBooksReport(HttpServletRequest request) throws LibraryException {
        try {
            List<IssueRecord> overdueRecords = issueService.getOverdueBooks(14); 

            StringBuilder sb = new StringBuilder();
            if (overdueRecords.isEmpty()) {
                sb.append("No overdue books found.");
            } else {
                sb.append("--- Overdue Books (Issued for >14 days) ---\n\n");
                for (IssueRecord record : overdueRecords) {
                    String bookTitle = "N/A";
                    String memberName = "N/A";

                    try {
                        BookValidator.validateNumericId(record.getBookId(), "Book ID");
                        Map<String, Object> criteria = new HashMap<>();
                        criteria.put("bookId", record.getBookId());
                        List<Book> books = bookService.findBooks(criteria);
                        Book book = books.isEmpty() ? null : books.get(0);
                        if (book != null) {
                            bookTitle = book.getTitle();
                        }
                    } catch (LibraryException | IllegalArgumentException e) {
                        System.err.println("Error fetching book for overdue report: " + e.getMessage());
                        bookTitle = "Error";
                    } catch (Exception e) {
                        System.err.println("Unexpected error fetching book for overdue report: " + e.getMessage());
                        bookTitle = "Error";
                    }

                    try {
                        Member member = memberService.getMemberById(record.getMemberId());
                        if (member != null) {
                            memberName = member.getName();
                        }
                    } catch (LibraryException | IllegalArgumentException e) {
                        System.err.println("Error fetching member for overdue report: " + e.getMessage());
                        memberName = "Error";
                    } catch (Exception e) {
                        System.err.println("Unexpected error fetching member for overdue report: " + e.getMessage());
                        memberName = "Error";
                    }

                    sb.append("Book ID: ").append(record.getBookId()).append("\n");
                    sb.append("  Title: ").append(bookTitle).append("\n");
                    sb.append("  Issued to: ").append(memberName).append(" (Member ID: ").append(record.getMemberId())
                            .append(")\n");
                    sb.append("  Issue Date: ").append(record.getIssueDate().format(FORMATTER)).append("\n");
                    sb.append("-------------------------------------------\n");
                }
            }
            request.setAttribute("reportContent", sb.toString());
            request.setAttribute("message", ""); 
        } catch (LibraryException e) {
            request.setAttribute("reportContent", "");
            throw e; 
        } catch (Exception e) {
            request.setAttribute("reportContent", "");
            throw e;
        }
    }

    private void handleBooksByCategoryReport(HttpServletRequest request) throws LibraryException {
        try {
            List<Book> allBooks = bookService.findBooks(Collections.emptyMap());
            
            Map<String, Long> booksByCategory = allBooks.stream()
                    .collect(Collectors.groupingBy(book -> book.getCategory().getDisplayName(), Collectors.counting()));

            StringBuilder sb = new StringBuilder();
            if (booksByCategory.isEmpty()) {
                sb.append("No books found to categorize.");
            } else {
                sb.append("--- Books Per Category ---\n\n");
                booksByCategory.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey()) 
                        .forEach(entry -> sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" books\n"));
            }
            request.setAttribute("reportContent", sb.toString());
            request.setAttribute("message", "");
        } catch (LibraryException e) {
            request.setAttribute("reportContent", "");
            throw e;
        } catch (Exception e) {
            request.setAttribute("reportContent", "");
            throw e;
        }
    }

    private void handleMembersWithActiveBooksReport(HttpServletRequest request) throws LibraryException {
        try {
            List<Member> membersWithActiveBooks = issueService.getMembersWithActiveBooks();

            StringBuilder sb = new StringBuilder();
            if (membersWithActiveBooks.isEmpty()) {
                sb.append("No members currently have active issued books.");
            } else {
                sb.append("--- Members with Active Issued Books ---\n\n");
                membersWithActiveBooks.stream()
                        .sorted(Comparator.comparingInt(Member::getMemberID)) 
                        .forEach(member -> sb.append("Member ID: ").append(member.getMemberID()).append(" - Name: ")
                                .append(member.getName()).append("\n"));
            }
            request.setAttribute("reportContent", sb.toString());
            request.setAttribute("message", "");
        } catch (LibraryException e) {
            request.setAttribute("reportContent", "");
            throw e;
        } catch (Exception e) {
            request.setAttribute("reportContent", "");
            throw e;
        }
    }
}