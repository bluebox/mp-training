package service;

import dao.BookDAO;
import model.Book;
import exception.DatabaseException;
import exception.InvalidInputException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookService {

    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    // Add a new book
    public void addBook(Book book) throws InvalidInputException, DatabaseException {
        validateBook(book);
        bookDAO.addBook(book);
    }

    // Update book details (excluding availability)
    public void updateBook(Book book) throws InvalidInputException, DatabaseException {
        if (book.getBookId() <= 0) {
            throw new InvalidInputException("Book ID is required for update.");
        }
        validateBook(book);
        bookDAO.updateBookDetails(book);
    }

    // Update availability only
    public void updateAvailability(int bookId, char availability) throws DatabaseException {
        if (availability != 'A' && availability != 'I') {
            throw new DatabaseException("Invalid availability status.");
        }
        bookDAO.updateAvailability(bookId, availability);
    }

    // Get all books
    public List<Book> getAllBooks() throws DatabaseException {
        return bookDAO.getAllBooks();
    }

    // Get books by category using Java 8 Streams
    public long countBooksByCategory(String category) throws DatabaseException {
        return getAllBooks().stream()
                .filter(book -> Objects.equals(book.getCategory(), category))
                .count();
    }

    // Get list of books with 'I' (Issued) availability
    public List<Book> getIssuedBooks() throws DatabaseException {
        return getAllBooks().stream()
                .filter(book -> book.getAvailability() == 'I')
                .collect(Collectors.toList());
    }

    // Helper method for validation
    private void validateBook(Book book) throws InvalidInputException {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null.");
        }
        if (isEmpty(book.getTitle()) || isEmpty(book.getAuthor()) || isEmpty(book.getCategory())) {
            throw new InvalidInputException("Title, Author, and Category are required.");
        }
        if (book.getStatus() != 'A' && book.getStatus() != 'I') {
            throw new InvalidInputException("Status must be A or I.");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    public Book getBookById(int bookId) throws DatabaseException {
    	if (bookId <= 0) {
    	throw new DatabaseException("Invalid book ID.");
    	}
    	return bookDAO.getBookById(bookId);
    	}
}
