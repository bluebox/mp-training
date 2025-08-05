package library.controllers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.model.enums.IssueStatus;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;
import library.validation.BookValidator;

@WebServlet("/returnBook") 
public class ReturnBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;
    private final String CURRENT_USER = "ADMIN"; 

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
        handleShowIssuedBooks(request); 
        request.getRequestDispatcher("/ReturnBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");
        
        String bookIdText = request.getParameter("bookId");
        request.setAttribute("bookId", bookIdText);

        String action = request.getParameter("action"); 

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No action specified.");
            request.setAttribute("messageType", "error");
            prepareAndForward(request, response);
            return;
        }

        try {
            switch (action) {
                case "showIssuedBooks":
                    handleShowIssuedBooks(request);
                    break;
                case "returnBook":
                    handleReturnBook(request);
                    break;
                default:
                    request.setAttribute("message", "Unknown action: " + action);
                    request.setAttribute("messageType", "error");
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid Book ID format. Please enter a numeric ID.");
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }

        request.getRequestDispatcher("/ReturnBookForm.jsp").forward(request, response);
    }

    private void handleShowIssuedBooks(HttpServletRequest request) {
        try {
            List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
            StringBuilder sb = new StringBuilder("Issued Books (Book ID - Title - Issued To - Issue Date/Time):\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            boolean foundIssuedBooks = false;
            for (IssueRecord record : issuedRecords) {
                if (record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null) { 
                    foundIssuedBooks = true;
                    String bookTitle = "Unknown Book";
                    String memberName = "Unknown Member";

                    try {
                        BookValidator.validateNumericId(record.getBookId(), "Book ID");
                        Map<String, Object> criteria = new HashMap<>();
                        criteria.put("bookId", record.getBookId());
                        List<Book> books = bookService.findBooks(criteria);
                        Book book = books.isEmpty() ? null : books.get(0);
                        if (book != null) {
                            bookTitle = book.getTitle();
                        }
                    } catch (LibraryException e) {
                        System.err.println("DB error getting book title for record " + record.getIssueId() + ": " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Unexpected error getting book title for record " + record.getIssueId() + ": " + e.getMessage());
                    }

                    try {
                        Member member = memberService.getMemberById(record.getMemberId()); 
                        if (member != null) {
                            memberName = member.getName();
                        }
                    } catch (Exception e) {
                        System.err.println("Unexpected error getting member name for record " + record.getIssueId() + ": " + e.getMessage());
                    }

                    sb.append(record.getBookId()).append(" - ").append(bookTitle).append(" (Issued to: ")
                            .append(memberName).append(" on ").append(record.getIssueDate().format(formatter))
                            .append(")\n");
                }
            }
            
            if (!foundIssuedBooks) {
                request.setAttribute("displayContent", "No books currently marked as 'Issued'.");
            } else {
                request.setAttribute("displayContent", sb.toString());
            }
        } catch (LibraryException e) {
            request.setAttribute("message", "Database error loading issued records: " + e.getMessage());
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred loading issued records: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }

    private void prepareAndForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ReturnBookForm.jsp").forward(request, response);
    }

    private void handleReturnBook(HttpServletRequest request) {
        String bookIdText = request.getParameter("bookId");

        if (bookIdText == null || bookIdText.isEmpty()) {
            request.setAttribute("message", "Please enter the Book ID to return.");
            request.setAttribute("messageType", "error");
            return;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdText);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid Book ID format. Please enter a numeric ID.");
            request.setAttribute("messageType", "error");
            return;
        }

        try {
            issueService.returnBook(bookId, CURRENT_USER); 
            request.setAttribute("message", "Book ID " + bookId + " returned successfully!");
            request.setAttribute("messageType", "success");
            
            request.removeAttribute("bookId");
            
            handleShowIssuedBooks(request);
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }
}