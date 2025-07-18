package controller.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.IssueRecord;
import serviceimpl.IssueServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/issuedrecords")
public class IssueRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IssueServiceImpl issueService = new IssueServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
            req.setAttribute("issuedRecords", issuedRecords);
        } catch (Exception e) {
            req.setAttribute("error", "Unable to fetch issued records: " + e.getMessage());
        }
        req.getRequestDispatcher("/view/issues/issuedRecords.jsp").forward(req, resp);
    }
}
