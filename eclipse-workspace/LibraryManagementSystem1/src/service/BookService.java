package service;

import dao.BookDAO;
import model.Book;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) throws Exception {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author is required");
        }
        if (book.getStatus() != 'A' && book.getStatus() != 'I') {
            throw new IllegalArgumentException("Status must be 'A' or 'I'");
        }
        if (book.getAvailability() != 'A' && book.getAvailability() != 'I') {
            throw new IllegalArgumentException("Availability must be 'A' or 'I'");
        }

        bookDAO.addBook(book);
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDAO.getAllBooks();
    }

    public void updateBook(Book book) throws Exception {
        if (book.getBookId() <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author is required");
        }
        if (book.getStatus() != 'A' && book.getStatus() != 'I') {
            throw new IllegalArgumentException("Status must be 'A' or 'I'");
        }
        if (book.getAvailability() != 'A' && book.getAvailability() != 'I') {
            throw new IllegalArgumentException("Availability must be 'A' or 'I'");
        }
        bookDAO.updateBook(book);
    }
    
    public void updateBookAvailability(int bookId,Character availability) throws Exception {
        if (bookId <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (availability != 'A' && availability != 'I') {
            throw new IllegalArgumentException("Availability must be 'A' or 'I'");
        }
        bookDAO.updateBookAvailability(bookId,availability);
    }
}
