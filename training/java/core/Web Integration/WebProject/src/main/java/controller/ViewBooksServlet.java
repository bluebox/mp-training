package controller;

import model.Book;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewBooks")
public class ViewBooksServlet extends HttpServlet {

    private final BookService bs = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        List<Book> list;
        try {
            list = bs.getAllBooks();
        } catch (Exception e) {
            resp.getWriter().println("<p style='color:red;'>Failed to load books: " + e.getMessage() + "</p>");
            return;
        }

        var out = resp.getWriter();

        out.println("<html><head><title>View Books</title>");
        out.println("<style>");
        out.println("table { border-collapse: collapse; width: 90%; margin: 20px 0; }");
        out.println("th, td { border: 1px solid #666; padding: 8px 12px; text-align: left; }");
        out.println("th { background-color: #ddd; }");
        out.println("</style></head><body>");
        out.println("<h2>Books List</h2>");

        if (list.isEmpty()) {
            out.println("<p>No books found.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Status</th><th>Availability</th></tr>");

            for (Book b : list) {
                String s = b.getStatus().toString().substring(0, 1);
                String a = b.getAvailability().toString().substring(0, 1);

                out.println("<tr>");
                out.println("<td>" + b.getBookId() + "</td>");
                out.println("<td>" + b.getTitle() + "</td>");
                out.println("<td>" + b.getAuthor() + "</td>");
                out.println("<td>" + b.getCategory() + "</td>");
                out.println("<td>" + s + "</td>");
                out.println("<td>" + a + "</td>");
                out.println("</tr>");
            }
            req.
            out.println("</table>");
        }

        out.println("</body></html>");
    }
}
