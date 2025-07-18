package controller.book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Book;
import serviceimpl.BookServiceImpl;

import java.io.IOException;

@WebServlet("/updatebook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            char availability = req.getParameter("availability").charAt(0);
            bookService.updateBookAvailability(bookId, availability);
            req.setAttribute("success", "Book availability updated successfully.");
        } catch (Exception e) {
            req.setAttribute("error", "Unable to update book: " + e.getMessage());
        }
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/books/updateBook.jsp").forward(req, resp);
    }
}
