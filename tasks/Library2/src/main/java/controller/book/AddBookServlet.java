package controller.book;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;
import serviceimpl.BookServiceImpl;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	 private final BookServiceImpl bookService = new BookServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/view/books/addBook.jsp").forward(req, resp);
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String availability = req.getParameter("availability");

            
            if (title == null || author == null || category == null || status == null || availability == null) {
                req.setAttribute("message", "All fields are required.");
                doGet(req, resp);
                return;
            }

            Book book = new Book(0, title, author, category, status.charAt(0), availability.charAt(0));
            bookService.addBook(book);

            req.setAttribute("message", "Book added successfully.");
            doGet(req, resp);
        } catch (Exception e) {
            
            req.setAttribute("message", "Error: " + e.getMessage());
            doGet(req, resp);
        }
    }
}
