package library.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.model.Book;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/viewBooks")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;

    public ViewBooksServlet() {
        super();
        this.bookService = new BookServiceImpl();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> books = bookService.findBooks(new Book());
            request.setAttribute("booksList", books);

            String statusMessage = (String) request.getSession().getAttribute("statusMessage");
            String statusType = (String) request.getSession().getAttribute("statusType");
            if (statusMessage != null) {
                request.setAttribute("statusMessage", statusMessage);
                request.setAttribute("statusType", statusType);
                request.getSession().removeAttribute("statusMessage");
                request.getSession().removeAttribute("statusType");
            }
            
            request.getRequestDispatcher("/viewBooks.jsp").forward(request, response);
        } catch (LibraryException e) {
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}