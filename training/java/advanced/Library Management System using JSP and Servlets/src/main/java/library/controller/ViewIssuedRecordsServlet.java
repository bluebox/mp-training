package library.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.model.Book;
import library.model.IssueRecord;
import library.model.Member;
import library.service.BookServiceImpl;
import library.service.IssueServiceImpl;
import library.service.MemberServiceImpl;
import library.service.interfaces.BookService;
import library.service.interfaces.IssueService;
import library.service.interfaces.MemberService;

@WebServlet("/viewIssuedRecords")
public class ViewIssuedRecordsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IssueService issueService;
    private BookService bookService;
    private MemberService memberService;

    public ViewIssuedRecordsServlet() {
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
        try {
            List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
            request.setAttribute("issuedRecords", issuedRecords);

            List<Book> allBooks = bookService.findBooks(new Book());
            request.setAttribute("allBooks", allBooks);

            List<Member> allMembers = memberService.getAllMembers();
            request.setAttribute("allMembers", allMembers);

            request.getRequestDispatcher("/viewIssuedRecords.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Error loading issued records: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}