package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import domain.checking_enum.Gender;
import domain.Member;
import Service.BookService;
import domain.Book;
import domain.checking_enum.Availability;
import domain.checking_enum.Status;

/**
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/UpdateMemberController")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookService service=new BookService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookService();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            String bookIdStr = request.getParameter("bookid");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String category = request.getParameter("category");
            String statusStr = request.getParameter("status");
            String availabilityStr = request.getParameter("availability");

            if (bookIdStr == null || title == null || author == null || category == null || statusStr == null || availabilityStr == null) {
                out.println("<h1>Error: Missing input parameters</h1>");
                return;
            }

            int bookId = Integer.parseInt(bookIdStr.trim());
            Status statusEnum = Status.getstatus(statusStr.trim().toUpperCase().substring(0, 1));
            Availability availabilityEnum = Availability.getstatus(availabilityStr.trim().toUpperCase().substring(0, 1));

            if (statusEnum == null || availabilityEnum == null) {
                out.println("<h1>Error: Invalid status or availability value</h1>");
                return;
            }

            Book updatedBook = new Book(title, author, category, statusEnum, availabilityEnum);
            int result = bookService.updatebookdetails(bookId, updatedBook);

            if (result > 0) {
                out.println("<h1>Successfully updated book</h1>");
            } else {
                out.println("<h1>Book update failed</h1>");
            }

        } catch (NumberFormatException e) {
            out.println("<h1>Error: Invalid Book ID format</h1>");
        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h1>Error occurred: " + e.getMessage() + "</h1>");
        }
    }

  
}

}
