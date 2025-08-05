package library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.dao.interfaceimpl.BookDAOImpl;
import library.dao.interfaces.BookDAO;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.util.DBConnection;
import library.validation.BookValidator;

public class BookServiceTest {

    private BookServiceImpl bookService;
    private BookDAO bookDAO;
    private final String TEST_USER = "TestUser";

    @Before
    public void setUp() throws SQLException {
        bookService = new BookServiceImpl();
        bookDAO = new BookDAOImpl();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM books");
            statement.executeUpdate("DELETE FROM books_log");
        }
    }

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM books");
            statement.executeUpdate("DELETE FROM books_log");
        }
    }

    @Test
    public void testAddBook_Success() throws LibraryException{
        Book book = new Book("The Great Novel", "Jane Doe", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
        List<Book> books = bookService.findBooks(Collections.emptyMap());
        assertEquals(1, books.size());
        assertEquals("The Great Novel", books.get(0).getTitle());
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_NullBook() throws LibraryException{
        bookService.addBook(null, TEST_USER);
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_EmptyTitle() throws LibraryException{
        Book book = new Book("", "Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_NumericTitle() throws LibraryException{
        Book book = new Book("12345", "Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_InvalidStatus() throws LibraryException{
        Book book = new Book("Title", "Author", BookCategory.FICTION, null, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_InvalidAvailability() throws LibraryException{
        Book book = new Book("Title", "Author", BookCategory.FICTION, BookStatus.ACTIVE, null);
        bookService.addBook(book, TEST_USER);
    }

    @Test
    public void testGetAllBooks_NoBooks() throws LibraryException {
        List<Book> books = bookService.findBooks(Collections.emptyMap());
        assertTrue(books.isEmpty());
    }

    @Test
    public void testGetAllBooks_MultipleBooks() throws LibraryException {
        bookService.addBook(new Book("Book 1", "Author 1", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Book 2", "Author 2", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        assertEquals(2, bookService.findBooks(Collections.emptyMap()).size());
    }

    @Test
    public void testGetBookById_Success() throws LibraryException{
        bookService.addBook(new Book("Find Me", "Finder", BookCategory.MYSTERY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookService.findBooks(Collections.emptyMap());
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        BookValidator.validateNumericId(bookId, "Book ID");
		
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("bookId", bookId);
        List<Book> books = bookService.findBooks(criteria);
        Book foundBook = books.isEmpty() ? null : books.get(0);
        
//        Book foundBook = bookService.getBookById(bookId);
        assertNotNull(foundBook);
        assertEquals("Find Me", foundBook.getTitle());
    }

    @Test
    public void testGetBookById_NotFound() throws LibraryException{
    	BookValidator.validateNumericId(99999, "Book ID");
		
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("bookId", 99999);
        List<Book> books = bookService.findBooks(criteria);
        Book foundBook = books.isEmpty() ? null : books.get(0);
//        Book foundBook = bookService.getBookById(99999);
        assertNull(foundBook);
    }

    @Test(expected = LibraryException.class)
    public void testGetBookById_InvalidId() throws LibraryException{
    	BookValidator.validateNumericId(0, "Book ID");
		
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("bookId", 0);
        bookService.findBooks(criteria);
//        Book foundAfterDelete = books.isEmpty() ? null : books.get(0);
//        bookService.getBookById(0);
    }

    @Test(expected = LibraryException.class)
    public void testAddBook_DuplicateTitleCategory() throws LibraryException{
        bookService.addBook(new Book("Duplicate Title", "Author A", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Duplicate Title", "Author B", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
    }

    @Test
    public void testUpdateBook_Success() throws LibraryException{
        Book originalBook = new Book("Old Title", "Old Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(originalBook, TEST_USER);

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", "Old Title");
        List<Book> found = bookService.findBooks(criteria);
        
        assertFalse(found.isEmpty());
        Book bookToUpdate = found.get(0);

        bookToUpdate.setTitle("New Title");
        bookToUpdate.setAuthor("New Author");
        bookToUpdate.setStatus(BookStatus.INACTIVE);

        boolean updated = bookService.updateBook(bookToUpdate, "Updater");
        assertTrue(updated);

        BookValidator.validateNumericId(bookToUpdate.getBookId(), "Book ID");
		
        Map<String, Object> Bidcriteria = new HashMap<>();
        criteria.put("bookId", bookToUpdate.getBookId());
        List<Book> books = bookService.findBooks(Bidcriteria);
        Book updatedBook = books.isEmpty() ? null : books.get(0);
        
//        Book updatedBook = bookService.getBookById(bookToUpdate.getBookId());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Updater", updatedBook.getUpdatedBy());
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals(BookStatus.INACTIVE, updatedBook.getStatus());
    }

    @Test(expected = LibraryException.class)
    public void testUpdateBook_DuplicateTitleCategory() throws LibraryException{
        bookService.addBook(new Book("Book A", "Author A", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Book B", "Author B", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", "Book B");
        List<Book> books = bookService.findBooks(criteria);
        
//        List<Book> books = bookService.getBooksByTitle("Book B");
        assertFalse(books.isEmpty());
        Book bookToUpdate = books.get(0);

        bookToUpdate.setTitle("Book A");
        bookToUpdate.setCategory(BookCategory.FICTION);

        bookService.updateBook(bookToUpdate, "Updater");
    }


    @Test
    public void testDeleteBook_Success() throws LibraryException{
        bookService.addBook(new Book("Book to Delete", "Author", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookService.findBooks(Collections.emptyMap());
        assertFalse(allBooks.isEmpty());
        int bookIdToDelete = allBooks.get(0).getBookId();

        boolean deleted = bookService.deleteBook(bookIdToDelete);
        assertTrue(deleted);

        BookValidator.validateNumericId(bookIdToDelete, "Book ID");
		
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("bookId", bookIdToDelete);
        List<Book> books = bookService.findBooks(criteria);
        Book foundAfterDelete = books.isEmpty() ? null : books.get(0);
        
//        Book foundAfterDelete = bookService.getBookById(bookIdToDelete);
        assertNull(foundAfterDelete);
    }

    @Test
    public void testUpdateBookAvailability_Success() throws LibraryException{
        bookService.addBook(new Book("Avail Book", "Avail Author", BookCategory.TECHNOLOGY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", "Avail Book");
        List<Book> allBooks = bookService.findBooks(criteria);
        
//        List<Book> allBooks = bookService.getBooksByTitle("Avail Book");
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        boolean updated = bookService.updateBookAvailability(bookId, BookAvailability.ISSUED.getCode(), "Issuer");
        assertTrue(updated);

        BookValidator.validateNumericId(bookId, "Book ID");
		
        Map<String, Object> Bidcriteria = new HashMap<>();
        criteria.put("bookId", bookId);
        List<Book> books = bookService.findBooks(Bidcriteria);
        Book updatedBook = books.isEmpty() ? null : books.get(0);
        
//        Book updatedBook = bookService.getBookById(bookId);
        assertEquals(BookAvailability.ISSUED, updatedBook.getAvailability());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Issuer", updatedBook.getUpdatedBy());
    }
}