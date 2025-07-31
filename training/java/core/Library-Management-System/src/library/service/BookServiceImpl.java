package library.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import library.dao.interfaceimpl.BookDAOImpl;
import library.dao.interfaces.BookDAO;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.service.interfaces.BookService;
import library.validation.BookValidator;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

   

    public BookServiceImpl() {
        this.bookDAO = new BookDAOImpl();
    }


    @Override
    public void addBook(Book book, String createdBy){
        BookValidator.validateBookTitle(book.getTitle());
        BookValidator.validateBookAuthor(book.getAuthor());
        BookValidator.validateBookCategory(book.getCategory().getDisplayName());
        BookValidator.validateBookStatus(book.getStatus());
        BookValidator.validateBookAvailability(book.getAvailability());
        BookValidator.validateUser(createdBy, "Created By User");

        try {
            bookDAO.addBook(book, createdBy);
            System.out.println("BookService: Book '" + book.getTitle() + "' successfully processed for addition.");
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                System.err.println("BookService: Attempted to add duplicate book (Title, Category): " + book.getTitle() + " - " + book.getCategory());
                throw new LibraryException("A book with the same title and category already exists.", e.getCause());
            }
            System.err.println("BookService: Database error during book addition: " + e.getMessage());
            throw new LibraryException("An error occurred during book data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred during book addition: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during book addition.", e);
        }
    }

    @Override
    public List<Book> findBooks(Map<String, Object> criteria) {
        try {
            List<Book> books = bookDAO.findBooks(criteria);
            System.out.println("BookService: Found " + books.size() + " books with criteria.");
            return books;
        } catch (LibraryException e) {
            System.err.println("BookService: Database error during book search: " + e.getMessage());
            throw new LibraryException("An error occurred during book search data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred while searching for books: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during book search.", e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return findBooks(Collections.emptyMap());
    }

    @Override
    public Book getBookById(int bookId) {
        BookValidator.validateNumericId(bookId, "Book ID");
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("bookId", bookId);
        List<Book> books = findBooks(criteria);
        return books.isEmpty() ? null : books.get(0);
    }

    public List<Book> getBooksByTitle(String title) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", title);
        return findBooks(criteria);
    }

    public List<Book> getBooksByAuthor(String author) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("author", author);
        return findBooks(criteria);
    }

    public List<Book> getBooksByCategory(String category) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("category", category);
        return findBooks(criteria);
    }

    @Override
    public Map<String, Long> getBooksCountByCategory(){
        List<Book> allBooks = getAllBooks();
        return allBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getCategory().getDisplayName(), Collectors.counting()));
    }

    @Override
    public boolean updateBook(Book book, String updatedBy) {
        if (book == null) { throw new LibraryException("Book object for update cannot be null."); }
        BookValidator.validateNumericId(book.getBookId(), "Book ID");
        BookValidator.validateBookTitle(book.getTitle());
        BookValidator.validateBookAuthor(book.getAuthor());
        BookValidator.validateBookCategory(book.getCategory().getDisplayName());
        BookValidator.validateBookStatus(book.getStatus());
        BookValidator.validateUser(updatedBy, "Updated By User");

        try {
            boolean success = bookDAO.updateBook(book, updatedBy);
            if (success) {
                System.out.println("BookService: Book with ID " + book.getBookId() + " successfully processed for update.");
            } else {
                System.out.println("BookService: Book with ID " + book.getBookId() + " not found for update.");
            }
            return success;
        } catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                System.err.println("BookService: Attempted to update book to duplicate (Title, Category): " + book.getTitle() + " - " + book.getCategory());
                throw new LibraryException("Cannot update: A book with the same title and category already exists.", e.getCause());
            }
            System.err.println("BookService: Database error during book update: " + e.getMessage());
            throw new LibraryException("An error occurred during book update data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred during book update: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during book update.", e);
        }
    }

    @Override
    public boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy) {
        BookValidator.validateNumericId(bookId, "Book ID");
        try {
            BookAvailability.fromCode(newAvailabilityCode);
        } catch (IllegalArgumentException e) {
            throw new LibraryException("Invalid Availability status code: " + newAvailabilityCode + ". Must be 'A' or 'I'.", e);
        }
        BookValidator.validateUser(updatedBy, "Updated By User");

        try {
            return bookDAO.updateBookAvailability(bookId, newAvailabilityCode, updatedBy);
        } catch (LibraryException e) {
            System.err.println("BookService: Database error during update book availability: " + e.getMessage());
            throw new LibraryException("An error occurred during book availability update data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred during update book availability: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during book availability update.", e);
        }
    }

    @Override
    public boolean deleteBook(int bookId){
        BookValidator.validateNumericId(bookId, "Book ID");
        try {
            boolean deleted = bookDAO.deleteBook(bookId);
            if (deleted) {
                System.out.println("BookService: Book with ID " + bookId + " deleted.");
            } else {
                System.out.println("BookService: No book with ID " + bookId + " found for deletion.");
            }
            return deleted;
        } catch (LibraryException e) {
            System.err.println("BookService: Database error during delete book: " + e.getMessage());
            throw new LibraryException("An error occurred during book deletion data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred during delete book: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during book deletion.", e);
        }
    }

    @Override
    public boolean[] deleteBooksBatch(List<Integer> bookIds) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new LibraryException("List of book IDs for batch delete cannot be null or empty.");
        }
        for (Integer bookId : bookIds) {
            BookValidator.validateNumericId(bookId, "Book ID in batch");
        }
        try {
            boolean[] results = bookDAO.deleteBooksBatch(bookIds);
            System.out.println("BookService: Batch delete operation completed.");
            return results;
        } catch (LibraryException e) {
            System.err.println("BookService: Database error during batch delete books: " + e.getMessage());
            throw new LibraryException("An error occurred during batch book deletion data access: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("BookService: An unexpected error occurred during batch delete books: " + e.getMessage());
            e.printStackTrace();
            throw new LibraryException("An unexpected error occurred during batch book deletion.", e);
        }
    }

    }