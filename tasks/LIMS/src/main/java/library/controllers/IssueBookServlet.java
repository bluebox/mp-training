package library.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
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
import library.model.Member;
import library.model.enums.BookAvailability;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;
import library.validation.BookValidator;

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {

    private BookService bookService;
    private IssueService issueService;
    private MemberService memberService;
    private final String CURRENT_USER = "ADMIN";

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookService = new BookServiceImpl();
        this.issueService = new IssueServiceImpl();
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
        request.setAttribute("displayListLabel", "Available Items List");
        request.getRequestDispatcher("/IssueBookForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.removeAttribute("message");
        request.removeAttribute("messageType");
        
        String bookIdText = request.getParameter("bookId");
        
        String memberIdText = request.getParameter("memberId");
        request.setAttribute("bookId", bookIdText);
        request.setAttribute("memberId", memberIdText);

        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "No action specified.");
            request.setAttribute("messageType", "error");
            prepareAndForward(request, response);
            return;
        }

        try {
            switch (action) {
                case "showBooks":
                    handleShowBooks(request);
                    break;
                case "showMembers":
                    handleShowMembers(request);
                    break;
                case "issueBook":
                    handleIssueBook(request);
                    break;
                default:
                    request.setAttribute("message", "Unknown action: " + action);
                    request.setAttribute("messageType", "error");
                    break;
            }
        } catch (LibraryException e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }

        prepareAndForward(request, response);
    }

    private void prepareAndForward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getAttribute("displayListLabel") == null) {
            request.setAttribute("displayListLabel", "Available Items List");
        }
        request.getRequestDispatcher("/IssueBookForm.jsp").forward(request, response);
    }

    private void handleShowBooks(HttpServletRequest request) {
        request.setAttribute("displayListLabel", "Available Books");
        try {
            List<Book> books = bookService.findBooks(null);
            StringBuilder sb = new StringBuilder();
            if (books.isEmpty()) {
                sb.append("No books in the library.");
            } else {
                sb.append("Books (ID - Title - Availability - Status):\n");
                for (Book book : books) {
                    sb.append(book.getBookId()).append(" - ").append(book.getTitle()).append(" (Avail: ")
                            .append(book.getAvailability().toString()).append(", Status: ")
                            .append(book.getStatus().toString()).append(")\n");
                }
            }
            request.setAttribute("displayContent", sb.toString());
        } catch (LibraryException e) {
            request.setAttribute("message", "Database error loading books: " + e.getMessage());
            request.setAttribute("messageType", "error");
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred loading books: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }

    private void handleShowMembers(HttpServletRequest request) {
        request.setAttribute("displayListLabel", "Available Members");
        try {
            List<Member> members = memberService.getAllMembers();
            StringBuilder sb = new StringBuilder();
            if (members.isEmpty()) {
                sb.append("No members registered.");
            } else {
                sb.append("Registered Members (ID - Name):\n");
                for (Member member : members) {
                    sb.append(member.getMemberID()).append(" - ").append(member.getName()).append("\n");
                }
            }
            request.setAttribute("displayContent", sb.toString());
        } catch (Exception e) {
            request.setAttribute("message", "An unexpected error occurred loading members: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
    }

    private void handleIssueBook(HttpServletRequest request) {
        String bookIdText = request.getParameter("bookId");
        String memberIdText = request.getParameter("memberId");

        if (bookIdText == null || bookIdText.isEmpty() || memberIdText == null || memberIdText.isEmpty()) {
            request.setAttribute("message", "Please enter both Book ID and Member ID.");
            request.setAttribute("messageType", "error");
            return;
        }

        int bookId;
        int memberId;

        try {
            bookId = Integer.parseInt(bookIdText);
            memberId = Integer.parseInt(memberIdText);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid ID format. Please enter numeric IDs.");
            request.setAttribute("messageType", "error");
            return;
        }

        try {
            BookValidator.validateNumericId(bookId, "Book ID");
            
            Map<String, Object> bookCriteria = new HashMap<>();
            bookCriteria.put("bookId", bookId);
            List<Book> books = bookService.findBooks(bookCriteria);
            Book bookToIssue = books.isEmpty() ? null : books.get(0);

            if (bookToIssue == null) {
                request.setAttribute("message", "Error: Book with ID " + bookId + " not found.");
                request.setAttribute("messageType", "error");
                return;
            }
            if (bookToIssue.getAvailability() == BookAvailability.ISSUED) {
                request.setAttribute("message", "Error: Book '" + bookToIssue.getTitle() + "' is already issued.");
                request.setAttribute("messageType", "error");
                return;
            }
            if (bookToIssue.getStatus() == BookStatus.INACTIVE) {
                request.setAttribute("message", "Error: Book '" + bookToIssue.getTitle() + "' is inactive and cannot be issued.");
                request.setAttribute("messageType", "error");
                return;
            }

            String memberName = "Unknown Member";
            Member member = memberService.getMemberById(memberId);
            if (member == null) {
                request.setAttribute("message", "Error: Member with ID " + memberId + " not found.");
                request.setAttribute("messageType", "error");
                return;
            }
            memberName = member.getName();

            issueService.issueBook(bookId, memberId, LocalDateTime.now(), CURRENT_USER);

            request.setAttribute("message", "Book '" + bookToIssue.getTitle() + "' issued to " + memberName + " successfully!");
            request.setAttribute("messageType", "success");
            
            request.removeAttribute("bookId");
            request.removeAttribute("memberId");

            handleShowBooks(request);

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