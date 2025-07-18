package controller.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviceimpl.IssueServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/returnbook")
public class ReturnBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IssueServiceImpl issueService = new IssueServiceImpl();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int issueId = Integer.parseInt(req.getParameter("issueId"));
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            LocalDate returnDate = LocalDate.parse(req.getParameter("returnDate"));
            issueService.returnBook(issueId, bookId, returnDate);
            req.setAttribute("success", "Book returned successfully.");
        } catch (Exception e) {
            req.setAttribute("error", "Unable to return book: " + e.getMessage());
        }
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/issues/returnBook.jsp").forward(req, resp);
    }
}
