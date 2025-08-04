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
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookService bookService;
    private final String CURRENT_USER = "ADMIN";

    public UpdateBookServlet() {
        super();
        this.bookService = new BookServiceImpl();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "Book ID not provided for update.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);
            
            // Use findBooks method to get a list, then retrieve the first element
            Book criteria = new Book();
            criteria.setBookId(bookId);
            List<Book> books = bookService.findBooks(criteria);
            Book bookToUpdate = books.isEmpty() ? null : books.get(0);

            if (bookToUpdate == null) {
                request.setAttribute("errorMessage", "Book with ID " + bookId + " not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("book", bookToUpdate);
            request.setAttribute("categories", BookCategory.values());
            request.setAttribute("statuses", BookStatus.values());
            request.getRequestDispatcher("/updateBookForm.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid book ID format.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryStr = request.getParameter("category");
        String statusStr = request.getParameter("status");
        
        try {
            int bookId = Integer.parseInt(bookIdStr);
            
            // Use findBooks method to get the book
            Book criteria = new Book();
            criteria.setBookId(bookId);
            List<Book> books = bookService.findBooks(criteria);
            Book bookToUpdate = books.isEmpty() ? null : books.get(0);
            
            if (bookToUpdate == null) {
                throw new LibraryException("Book with ID " + bookId + " not found.");
            }

            bookToUpdate.setTitle(title);
            bookToUpdate.setAuthor(author);
            bookToUpdate.setCategory(BookCategory.fromDisplayName(categoryStr));
            bookToUpdate.setStatus(BookStatus.valueOf(statusStr));

            boolean success = bookService.updateBook(bookToUpdate, CURRENT_USER);

            if (success) {
                request.getSession().setAttribute("statusMessage", "Book ID " + bookId + " updated successfully!");
                request.getSession().setAttribute("statusType", "success");
            } else {
                request.getSession().setAttribute("statusMessage", "Failed to update Book ID " + bookId + ". No changes were made.");
                request.getSession().setAttribute("statusType", "error");
            }
            
            response.sendRedirect(request.getContextPath() + "/viewBooks");

        } catch (LibraryException e) {
            int bookId = Integer.parseInt(bookIdStr);
            Book criteria = new Book();
            criteria.setBookId(bookId);
            List<Book> books = bookService.findBooks(criteria);
            Book bookForError = books.isEmpty() ? null : books.get(0);

            request.setAttribute("book", bookForError);
            request.setAttribute("categories", BookCategory.values());
            request.setAttribute("statuses", BookStatus.values());
            request.setAttribute("statusMessage", e.getMessage());
            request.setAttribute("statusType", "error");
            request.getRequestDispatcher("/updateBookForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}