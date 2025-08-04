package library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.IssueRecord;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;
    private final String CURRENT_USER = "ADMIN";

    public ReturnBookServlet() {
        super();
        this.issueService = new IssueServiceImpl();
        this.bookService = new BookServiceImpl();
        this.memberService = new MemberServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
            request.setAttribute("issuedRecords", issuedRecords);

            String statusMessage = (String) request.getSession().getAttribute("statusMessage");
            String statusType = (String) request.getSession().getAttribute("statusType");
            if (statusMessage != null) {
                request.setAttribute("statusMessage", statusMessage);
                request.setAttribute("statusType", statusType);
                request.getSession().removeAttribute("statusMessage");
                request.getSession().removeAttribute("statusType");
            }
            
            request.getRequestDispatcher("/returnBookForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error loading data for book return: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdText = request.getParameter("bookId");

        try {
            if (bookIdText.isEmpty()) {
                throw new LibraryException("Please enter the Book ID to return.");
            }
            int bookId = Integer.parseInt(bookIdText);
            
            issueService.returnBook(bookId, CURRENT_USER);
            
            request.getSession().setAttribute("statusMessage", "Book ID " + bookId + " returned successfully!");
            request.getSession().setAttribute("statusType", "success");
            
            response.sendRedirect(request.getContextPath() + "/returnBook");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("statusMessage", "Invalid Book ID format. Please enter a numeric ID.");
            request.getSession().setAttribute("statusType", "error");
            response.sendRedirect(request.getContextPath() + "/returnBook");
        } catch (LibraryException e) {
            request.getSession().setAttribute("statusMessage", e.getMessage());
            request.getSession().setAttribute("statusType", "error");
            response.sendRedirect(request.getContextPath() + "/returnBook");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}