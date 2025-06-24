package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DatabaseException;
import model.Book;
import service.BookService;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {

    private BookService bookService = new BookService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("bookId");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Book book = null;

        if (idStr == null) {
            out.println("Book ID is missing");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            book = bookService.getBookById(id);

            if (book == null) {
                out.println("Book not found");
                return;
            }

            out.println("<h2>Update Book</h2>");
            out.println("<form method='POST' action='./updateBook'>");
            out.println("<input type='hidden' name='bookId' value='" + book.getBookId() + "'/>");

            out.println("Title: <input name='title' value='" + book.getTitle() + "' required/><br/>");
            out.println("Author: <input name='author' value='" + book.getAuthor() + "' required/><br/>");
            out.println("Category: <input name='category' value='" + book.getCategory() + "' required/><br/>");

            out.println("Status: <select name='status' required>");
            out.println("<option value='A'" + ("A".equals(book.getStatus()) ? " selected" : "") + ">A</option>");
            out.println("<option value='I'" + ("I".equals(book.getStatus()) ? " selected" : "") + ">I</option>");
            out.println("</select><br/>");

            out.println("Availability: <input value='" + book.getAvailability() + "' disabled/><br/>");

            out.println("<input type='submit' value='Update Book'/>");
            out.println("</form>");

        } catch (NumberFormatException e) {
            out.println("Invalid book ID");
        } catch (DatabaseException e) {
            e.printStackTrace();
            out.println("Database error");
        }
    }

    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String category = req.getParameter("category");
            String status = req.getParameter("status");

            Book existingBook = bookService.getBookById(bookId);
            if (existingBook == null) {
                out.println("Book not found");
                return;
            }

            existingBook.setTitle(title);
            existingBook.setAuthor(author);
            existingBook.setCategory(category);
            existingBook.setStatus(status);

            bookService.updateBook(existingBook);

            out.println("Book updated successfully!<br/>");
            out.println("<a href='book?id=" + bookId + "'>Back to Book</a>");

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

}
