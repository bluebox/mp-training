package controller.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.IssueRecord;
import serviceimpl.IssueServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/issuebook")
public class IssueBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IssueServiceImpl issueService = new IssueServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int memberId = Integer.parseInt(req.getParameter("memberId"));
            LocalDate issueDate = LocalDate.parse(req.getParameter("issueDate"));
            IssueRecord issue = new IssueRecord(bookId, memberId, 'I', issueDate);
            issueService.issueBook(issue);
            req.setAttribute("success", "Book issued successfully.");
        } catch (Exception e) {
            req.setAttribute("error", "Unable to issue book: " + e.getMessage());
        }
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/issues/issueBook.jsp").forward(req, resp);
    }
}
