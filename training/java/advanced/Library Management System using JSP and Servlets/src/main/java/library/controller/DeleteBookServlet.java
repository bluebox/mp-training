package library.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.exception.LibraryException;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;

    public DeleteBookServlet() {
        super();
        this.bookService = new BookServiceImpl();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        
        try {
            if (bookIdStr == null || bookIdStr.isEmpty()) {
                throw new LibraryException("Book ID not provided for deletion.");
            }
            int bookId = Integer.parseInt(bookIdStr);
            
            boolean deleted = bookService.deleteBook(bookId);

            if (deleted) {
                request.getSession().setAttribute("statusMessage", "Book ID " + bookId + " deleted successfully.");
                request.getSession().setAttribute("statusType", "success");
            } else {
                request.getSession().setAttribute("statusMessage", "Failed to delete Book ID " + bookId + ". Book not found.");
                request.getSession().setAttribute("statusType", "error");
            }
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("statusMessage", "Invalid book ID format for deletion.");
            request.getSession().setAttribute("statusType", "error");
        } catch (LibraryException e) {
            request.getSession().setAttribute("statusMessage", e.getMessage());
            request.getSession().setAttribute("statusType", "error");
        } catch (Exception e) {
            request.getSession().setAttribute("statusMessage", "An unexpected error occurred: " + e.getMessage());
            request.getSession().setAttribute("statusType", "error");
        }
        
        response.sendRedirect(request.getContextPath() + "/viewBooks");
    }
}