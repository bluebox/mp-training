package controller;

import Service.ServiceLayer;
import Domain.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/issuebook")
public class IssuebookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceLayer service = new ServiceLayer();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/issuebookform.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookid = request.getParameter("bookid");
        String memberid = request.getParameter("memberid");
        String msg;

        if (bookid == null || memberid == null) {
            msg = "Book ID and Member ID are required.";
        } else {
            try {
                int bookId = Integer.parseInt(bookid);
                int memberId = Integer.parseInt(memberid);
                boolean success = service.issueBook(bookId, memberId);

                if (success) {
                    msg = "Book issued successfully!";
                } else {
                    msg = "Member or book are not available to issue.";
                }
            } catch (NumberFormatException e) {
                msg = "Invalid Member ID or Book ID.";
            } catch (SQLException e) {
                msg = "Database error while issuing book.";
                e.printStackTrace();
            }
        }

        request.setAttribute("message", msg);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/issueBookresult.jsp");
        dispatcher.forward(request, response);
    }
}

