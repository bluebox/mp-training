package controller.issue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberIssueDTO;
import serviceimpl.MemberServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/memberswithbooks")
public class MembersWithBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberServiceImpl memberService = new MemberServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<MemberIssueDTO> membersWithBooks = memberService.getMembersWithActiveIssues();
            req.setAttribute("membersWithBooks", membersWithBooks);
        } catch (Exception e) {
            req.setAttribute("error", "Unable to fetch members with books: " + e.getMessage());
        }
        req.getRequestDispatcher("/view/issues/membersWithBooks.jsp").forward(req, resp);
    }
}
