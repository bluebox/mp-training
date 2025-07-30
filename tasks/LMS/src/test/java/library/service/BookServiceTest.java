package library.service;

import library.dao.interfaceimpl.BookDAOImpl;
import library.dao.interfaces.BookDAO;
import library.exception.LibraryDataAccessException;
import library.exception.LibraryValidationException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.util.DBConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import static org.junit.Assert.*;

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
    public void testAddBook_Success() throws LibraryValidationException, LibraryDataAccessException {
        Book book = new Book("The Great Novel", "Jane Doe", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("The Great Novel", books.get(0).getTitle());
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_NullBook() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(null, TEST_USER);
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_EmptyTitle() throws LibraryValidationException, LibraryDataAccessException {
        Book book = new Book("", "Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_NumericTitle() throws LibraryValidationException, LibraryDataAccessException {
        Book book = new Book("12345", "Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_InvalidStatus() throws LibraryValidationException, LibraryDataAccessException {
        Book book = new Book("Title", "Author", BookCategory.FICTION, null, BookAvailability.AVAILABLE);
        bookService.addBook(book, TEST_USER);
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_InvalidAvailability() throws LibraryValidationException, LibraryDataAccessException {
        Book book = new Book("Title", "Author", BookCategory.FICTION, BookStatus.ACTIVE, null);
        bookService.addBook(book, TEST_USER);
    }

    @Test
    public void testGetAllBooks_NoBooks() throws LibraryDataAccessException {
        List<Book> books = bookService.getAllBooks();
        assertTrue(books.isEmpty());
    }

    @Test
    public void testGetAllBooks_MultipleBooks() throws LibraryDataAccessException {
        bookService.addBook(new Book("Book 1", "Author 1", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Book 2", "Author 2", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        assertEquals(2, bookService.getAllBooks().size());
    }

    @Test
    public void testGetBookById_Success() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(new Book("Find Me", "Finder", BookCategory.MYSTERY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookService.getAllBooks();
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        Book foundBook = bookService.getBookById(bookId);
        assertNotNull(foundBook);
        assertEquals("Find Me", foundBook.getTitle());
    }

    @Test
    public void testGetBookById_NotFound() throws LibraryValidationException, LibraryDataAccessException {
        Book foundBook = bookService.getBookById(99999);
        assertNull(foundBook);
    }

    @Test(expected = LibraryValidationException.class)
    public void testGetBookById_InvalidId() throws LibraryValidationException, LibraryDataAccessException {
        bookService.getBookById(0);
    }

    @Test(expected = LibraryValidationException.class)
    public void testAddBook_DuplicateTitleCategory() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(new Book("Duplicate Title", "Author A", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Duplicate Title", "Author B", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
    }

    @Test
    public void testUpdateBook_Success() throws LibraryValidationException, LibraryDataAccessException {
        Book originalBook = new Book("Old Title", "Old Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookService.addBook(originalBook, TEST_USER);

        List<Book> found = bookService.getBooksByTitle("Old Title");
        assertFalse(found.isEmpty());
        Book bookToUpdate = found.get(0);

        bookToUpdate.setTitle("New Title");
        bookToUpdate.setAuthor("New Author");
        bookToUpdate.setStatus(BookStatus.INACTIVE);

        boolean updated = bookService.updateBook(bookToUpdate, "Updater");
        assertTrue(updated);

        Book updatedBook = bookService.getBookById(bookToUpdate.getBookId());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Updater", updatedBook.getUpdatedBy());
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals(BookStatus.INACTIVE, updatedBook.getStatus());
    }

    @Test(expected = LibraryValidationException.class)
    public void testUpdateBook_DuplicateTitleCategory() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(new Book("Book A", "Author A", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookService.addBook(new Book("Book B", "Author B", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);

        List<Book> books = bookService.getBooksByTitle("Book B");
        assertFalse(books.isEmpty());
        Book bookToUpdate = books.get(0);

        bookToUpdate.setTitle("Book A");
        bookToUpdate.setCategory(BookCategory.FICTION);

        bookService.updateBook(bookToUpdate, "Updater");
    }


    @Test
    public void testDeleteBook_Success() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(new Book("Book to Delete", "Author", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookService.getAllBooks();
        assertFalse(allBooks.isEmpty());
        int bookIdToDelete = allBooks.get(0).getBookId();

        boolean deleted = bookService.deleteBook(bookIdToDelete);
        assertTrue(deleted);

        Book foundAfterDelete = bookService.getBookById(bookIdToDelete);
        assertNull(foundAfterDelete);
    }

    @Test
    public void testUpdateBookAvailability_Success() throws LibraryValidationException, LibraryDataAccessException {
        bookService.addBook(new Book("Avail Book", "Avail Author", BookCategory.TECHNOLOGY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookService.getBooksByTitle("Avail Book");
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        boolean updated = bookService.updateBookAvailability(bookId, BookAvailability.ISSUED.getCode(), "Issuer");
        assertTrue(updated);

        Book updatedBook = bookService.getBookById(bookId);
        assertEquals(BookAvailability.ISSUED, updatedBook.getAvailability());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Issuer", updatedBook.getUpdatedBy());
    }
}