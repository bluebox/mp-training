package controller;

import enums.Availability;
import enums.Status;
import model.Book;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String category = request.getParameter("category");

        PrintWriter out = response.getWriter();

        if (title == null || title.isEmpty() ||
            author == null || author.isEmpty() ||
            category == null || category.isEmpty()) {

            out.println("<h3>All fields are required.</h3>");
            return;
        }

        try {
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setStatus(Status.Active);
            book.setAvailability(Availability.Available);

            bookService.addBook(book);

            out.println("<h3>Book added successfully!</h3>");
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
