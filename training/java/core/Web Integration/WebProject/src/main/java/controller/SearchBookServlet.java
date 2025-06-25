package controller;

import model.Book;
import service.BookService;
import enums.Availability;
import exception.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {

    private final BookService bs = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

        PrintWriter out = res.getWriter();
        String idStr = req.getParameter("bookId");

        out.println("<html><head><title>Search Book Result</title></head><body>");
        out.println("<h2>Search Book Result</h2>");

        if (idStr == null || idStr.trim().isEmpty()) {
            out.println("<p style='color:red;'>Please enter a Book ID.</p>");
            out.println("</body></html>");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            Book b = bs.getBookById(id);

            if (b != null) {
                out.println("<p><strong>Title:</strong> " + b.getTitle() + "</p>");
                out.println("<p><strong>Author:</strong> " + b.getAuthor() + "</p>");
                String status = (b.getAvailability() == "A") ? "Available" : "Issued";
                out.println("<p><strong>Status:</strong> " + status + "</p>");
            } else {
                out.println("<p style='color:red;'>Book not found.</p>");
            }

        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Invalid Book ID format. Please enter a number.</p>");
        } catch (DatabaseException e) {
            out.println("<p style='color:red;'>Database error: " + e.getMessage() + "</p>");
        } catch (Exception e) {
            out.println("<p style='color:red;'>Unexpected error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }
}
