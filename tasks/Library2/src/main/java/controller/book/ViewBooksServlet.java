package controller.book;

import model.Book;
import serviceimpl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewbooks")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private final BookServiceImpl bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Book> books = bookService.getAllBooks();
            req.setAttribute("books", books);
        } catch (Exception e) {
            req.setAttribute("error", "Unable to fetch books: " + e.getMessage());
        }
        req.getRequestDispatcher("/view/books/viewBooks.jsp").forward(req, resp);
    }
}