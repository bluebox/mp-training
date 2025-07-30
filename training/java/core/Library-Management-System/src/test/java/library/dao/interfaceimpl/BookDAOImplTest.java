package library.dao.interfaceimpl;

import library.dao.interfaces.BookDAO;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.util.DBConnection;
import library.exception.LibraryDataAccessException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookDAOImplTest {

    private BookDAO bookDAO;
    private final String TEST_USER = "TestUser"; // Static user for createdBy/updatedBy

    @Before
    public void setUp() throws SQLException {
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
    public void testAddOneBook_Success() throws LibraryDataAccessException {
        Book book = new Book("Test Title", "Test Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookDAO.addBook(book, TEST_USER);

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", "Test Title");
        List<Book> foundBooks = bookDAO.findBooks(criteria);

        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        assertEquals("Test Title", foundBooks.get(0).getTitle());
        assertEquals("Test Author", foundBooks.get(0).getAuthor());
        assertEquals(BookCategory.FICTION, foundBooks.get(0).getCategory());
        assertEquals(BookStatus.ACTIVE, foundBooks.get(0).getStatus());
        assertEquals(BookAvailability.AVAILABLE, foundBooks.get(0).getAvailability());
        assertNotNull(foundBooks.get(0).getCreatedAt());
        assertEquals(TEST_USER, foundBooks.get(0).getCreatedBy());
        assertNull(foundBooks.get(0).getUpdatedAt());
        assertNull(foundBooks.get(0).getUpdatedBy());
    }

    @Test
    public void testGetAllMultipleBooks() throws LibraryDataAccessException {
        assertEquals(0, bookDAO.findBooks(Collections.emptyMap()).size());

        bookDAO.addBook(new Book("Book One", "Author One", BookCategory.FANTASY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookDAO.addBook(new Book("Book Two", "Author Two", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.ISSUED), TEST_USER);
        bookDAO.addBook(new Book("Book Three", "Author Three", BookCategory.HISTORY, BookStatus.INACTIVE, BookAvailability.AVAILABLE), TEST_USER);

        assertEquals(3, bookDAO.findBooks(Collections.emptyMap()).size());
    }

    @Test
    public void testFindBooks_ById() throws LibraryDataAccessException {
        Book book = new Book("Specific Book", "Specific Author", BookCategory.MYSTERY, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookDAO.addBook(book, TEST_USER);

        List<Book> allBooks = bookDAO.findBooks(Collections.emptyMap());
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        Map<String, Object> idCriteria = new HashMap<>();
        idCriteria.put("bookId", bookId);
        List<Book> found = bookDAO.findBooks(idCriteria);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals(bookId, found.get(0).getBookId());
        assertEquals("Specific Book", found.get(0).getTitle());
    }

    @Test
    public void testFindBooks_ByTitleAndCategory() throws LibraryDataAccessException {
        bookDAO.addBook(new Book("Search Title", "Author A", BookCategory.TECHNOLOGY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookDAO.addBook(new Book("Another Title", "Author B", BookCategory.HISTORY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("title", "Search Title");
        criteria.put("category", BookCategory.TECHNOLOGY.getDisplayName());
        List<Book> found = bookDAO.findBooks(criteria);

        assertNotNull(found);
        assertEquals(1, found.size());
        assertEquals("Search Title", found.get(0).getTitle());
        assertEquals(BookCategory.TECHNOLOGY, found.get(0).getCategory());
    }

    @Test(expected = LibraryDataAccessException.class)
    public void testAddBook_DuplicateTitleCategory() throws LibraryDataAccessException {
        bookDAO.addBook(new Book("Duplicate Title", "Author One", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        bookDAO.addBook(new Book("Duplicate Title", "Another Author", BookCategory.OTHER, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
    }

    @Test
    public void testUpdateBook_Success() throws LibraryDataAccessException {
        Book originalBook = new Book("Old Title", "Old Author", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
        bookDAO.addBook(originalBook, TEST_USER);

        List<Book> found = bookDAO.findBooks(Map.of("title", "Old Title"));
        assertFalse(found.isEmpty());
        Book bookToUpdate = found.get(0);

        bookToUpdate.setTitle("New Title");
        bookToUpdate.setAuthor("New Author");
        bookToUpdate.setStatus(BookStatus.INACTIVE);

        boolean updated = bookDAO.updateBook(bookToUpdate, "Updater");
        assertTrue(updated);

        Book updatedBook = bookDAO.findBooks(Map.of("bookId", bookToUpdate.getBookId())).get(0);
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals(BookStatus.INACTIVE, updatedBook.getStatus());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Updater", updatedBook.getUpdatedBy());
    }

    @Test
    public void testDeleteBook_Success() throws LibraryDataAccessException {
        bookDAO.addBook(new Book("Book to Delete", "Author", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookDAO.findBooks(Collections.emptyMap());
        assertFalse(allBooks.isEmpty());
        int bookIdToDelete = allBooks.get(0).getBookId();

        boolean deleted = bookDAO.deleteBook(bookIdToDelete);
        assertTrue(deleted);

        List<Book> foundAfterDelete = bookDAO.findBooks(Map.of("bookId", bookIdToDelete));
        assertTrue(foundAfterDelete.isEmpty());
    }

    @Test
    public void testUpdateBookAvailability_Success() throws LibraryDataAccessException {
        bookDAO.addBook(new Book("Avail Book", "Avail Author", BookCategory.TECHNOLOGY, BookStatus.ACTIVE, BookAvailability.AVAILABLE), TEST_USER);
        List<Book> allBooks = bookDAO.findBooks(Map.of("title", "Avail Book"));
        assertFalse(allBooks.isEmpty());
        int bookId = allBooks.get(0).getBookId();

        boolean updated = bookDAO.updateBookAvailability(bookId, BookAvailability.ISSUED.getCode(), "Issuer");
        assertTrue(updated);

        Book updatedBook = bookDAO.findBooks(Map.of("bookId", bookId)).get(0);
        assertEquals(BookAvailability.ISSUED, updatedBook.getAvailability());
        assertNotNull(updatedBook.getUpdatedAt());
        assertEquals("Issuer", updatedBook.getUpdatedBy());
    }
}