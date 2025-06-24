package controller;

import service.IssueService;
import exception.BookAlreadyIssuedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet {

    private final IssueService issueService = new IssueService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String bookIdStr = request.getParameter("bookId");
        String memberIdStr = request.getParameter("memberId");

        if (bookIdStr == null || bookIdStr.isEmpty() ||
            memberIdStr == null || memberIdStr.isEmpty()) {

            response.getWriter().println("<p style='color:red;'>Both Book ID and Member ID are required.</p>");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr.trim());
            int memberId = Integer.parseInt(memberIdStr.trim());

            issueService.issueBook(bookId, memberId);

            response.getWriter().println("<p style='color:green;'>Book issued successfully.</p>");

        } catch (NumberFormatException e) {
            response.getWriter().println("<p style='color:red;'>Invalid input. Enter valid numeric IDs.</p>");
        } catch (BookAlreadyIssuedException e) {
            response.getWriter().println("<p style='color:orange;'>This book is already issued.</p>");
        } catch (Exception e) {
            response.getWriter().println("<p style='color:red;'>Error issuing book: " + e.getMessage() + "</p>");
        }
    }
}
