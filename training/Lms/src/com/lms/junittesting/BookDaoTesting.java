package com.lms.junittesting;

import com.lms.daoImpl.BookDao;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.util.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookDaoTest {

    private static final BookDao dao = BookDao.getInstance();
    private static String testBookId;

    @BeforeAll
    static void setup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM books WHERE title = 'JUnit Book'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void testAddBook() {
        Book added = dao.addBook("JUnit Book", "JUnit Author", BookCategory.BIOGRAPHY, 'A', 'A');
        assertNotNull(added);
        assertEquals("JUnit Book", added.getBookTitle());
        testBookId = added.getBookId();
    }

    @Test
    @Order(2)
    void testIsTitleExists() {
        assertTrue(dao.isTitleExists("JUnit Book"));
        assertFalse(dao.isTitleExists("Nonexistent Book"));
    }

    @Test
    @Order(3)
    void testUpdateBook() {
        boolean updated = dao.updateBook(testBookId, "Updated JUnit Book", "New Author", BookCategory.COMICS, 'I', 'I');
        assertTrue(updated);
        Book fetched = dao.getBookById(testBookId);
        assertEquals("Updated JUnit Book", fetched.getBookTitle());
    }

    @Test
    @Order(4)
    void testGetBookById() {
        Book book = dao.getBookById(testBookId);
        assertNotNull(book);
        assertEquals(testBookId, book.getBookId());
    }

    @Test
    @Order(5)
    void testGetBooks() {
        List<Book> books = dao.getBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    @Order(6)
    void testGetAvailableBooksByCategory() {
        List<Book> sciBooks = dao.getAvailableBooksByCategory(BookCategory.BIOGRAPHY);
        assertNotNull(sciBooks);
        List<Book> allAvailable = dao.getAvailableBooksByCategory(null);
        assertNotNull(allAvailable);
    }

    @Test
    @Order(7)
    void testUpdateBookAvailability() {
        dao.updateBookAvailability(testBookId, 'A');
        Book updated = dao.getBookById(testBookId);
        assertEquals('A', updated.getAvailability());
    }

    @AfterAll
    static void cleanup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM books WHERE book_id = '" + testBookId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}