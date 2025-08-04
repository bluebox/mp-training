package library.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookService bookService;
    private IssueService issueService;
    private MemberService memberService;
    private final String CURRENT_USER = "ADMIN";

    public IssueBookServlet() {
        super();
        this.bookService = new BookServiceImpl();
        this.issueService = new IssueServiceImpl();
        this.memberService = new MemberServiceImpl();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> books = bookService.findBooks(new Book());
            List<Member> members = memberService.getAllMembers();
            
            request.setAttribute("booksList", books);
            request.setAttribute("membersList", members);
            
            String statusMessage = (String) request.getSession().getAttribute("statusMessage");
            String statusType = (String) request.getSession().getAttribute("statusType");
            if (statusMessage != null) {
                request.setAttribute("statusMessage", statusMessage);
                request.setAttribute("statusType", statusType);
                request.getSession().removeAttribute("statusMessage");
                request.getSession().removeAttribute("statusType");
            }

            request.getRequestDispatcher("/issueBookForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error loading data for book issue: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdText = request.getParameter("bookId");
        String memberIdText = request.getParameter("memberId");
        
        try {
            if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                throw new LibraryException("Please enter both Book ID and Member ID.");
            }

            int bookId = Integer.parseInt(bookIdText);
            int memberId = Integer.parseInt(memberIdText);

            Book criteria = new Book();
            criteria.setBookId(bookId);
            List<Book> books = bookService.findBooks(criteria);
            Book bookToIssue = books.isEmpty() ? null : books.get(0);
            
            if (bookToIssue == null) {
                throw new LibraryException("Error: Book with ID " + bookId + " not found.");
            }
            if (bookToIssue.getAvailability() == BookAvailability.ISSUED) {
                throw new LibraryException("Error: Book '" + bookToIssue.getTitle() + "' is already issued.");
            }
            if (bookToIssue.getStatus() == BookStatus.INACTIVE) {
                throw new LibraryException("Error: Book '" + bookToIssue.getTitle() + "' is inactive and cannot be issued.");
            }

            Member member = memberService.getMemberById(memberId);
            if (member == null) {
                throw new LibraryException("Error: Member with ID " + memberId + " not found.");
            }

            issueService.issueBook(bookId, memberId, LocalDateTime.now(), CURRENT_USER);

            request.getSession().setAttribute("statusMessage", "Book '" + bookToIssue.getTitle() + "' issued to " + member.getName() + " successfully!");
            request.getSession().setAttribute("statusType", "success");
            
            response.sendRedirect(request.getContextPath() + "/issueBook");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("statusMessage", "Invalid ID format. Please enter numeric IDs.");
            request.getSession().setAttribute("statusType", "error");
            response.sendRedirect(request.getContextPath() + "/issueBook");
        } catch (LibraryException e) {
            request.getSession().setAttribute("statusMessage", e.getMessage());
            request.getSession().setAttribute("statusType", "error");
            response.sendRedirect(request.getContextPath() + "/issueBook");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}