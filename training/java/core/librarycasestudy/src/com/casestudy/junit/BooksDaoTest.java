package com.casestudy.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casestudy.dao.BooksDao;
import com.casestudy.domain.Availability;
import com.casestudy.domain.Book;
import com.casestudy.domain.Status;
import com.casestudy.util.DBUtil;


public class BooksDaoTest {

    private static Connection conn;
    private static BooksDao dao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Setup connection
        conn = DBUtil.getConnection();  // You may replace this with an H2 in-memory DB for safe testing
        dao = new BooksDao();

        // Clean up table and insert a test book
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM Books");
        stmt.execute("INSERT INTO Books (bookId, title, author, category, status, availability) " +
                "VALUES (100, 'JUnit Book', 'Test Author', 'Testing', 'A', 'A')");
        stmt.close();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Cleanup
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM Books WHERE bookId = 100");
        stmt.close();
        conn.close();
    }

    @Test
    public void testCreateBook() throws SQLException {
        Book book = new Book("New Book", "New Author", "Science", Status.ACTIVE, Availability.AVAILABLE);
        dao.createBook(book);
        // If no exception, assume pass (for now)
        // Ideally we should fetch and assert the book exists
    }

    @Test
    public void testUpdateBookAvailability() {
        dao.updateBookAvailability(100); // toggles A->I
        List<Book> books = dao.viewAllBooks();
        Book updatedBook = books.stream().filter(b -> b.getBookId() == 100).findFirst().orElse(null);
        assertNotNull(updatedBook);
        assertEquals(Availability.ISSUED, updatedBook.getAvailable());

        // revert to A
        dao.updateBookAvailability(100);
    }

    @Test
    public void testUpdateBook() {
        Book updated = new Book(100, "Updated Title", "Updated Author", "Updated", Status.INACTIVE, Availability.AVAILABLE);
        dao.updateBook(updated);
        List<Book> books = dao.viewAllBooks();
        Book book = books.stream().filter(b -> b.getBookId() == 100).findFirst().orElse(null);
        assertNotNull(book);
        assertEquals("Updated Title", book.getTitle());
        assertEquals("Updated Author", book.getAuthor());
        assertEquals(Status.INACTIVE, book.getStatus());
    }

    @Test
    public void testViewAllBooks() {
        List<Book> books = dao.viewAllBooks();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testCanBeIssued() {
        boolean canIssue = dao.CanBeIssued(100);
        assertTrue(canIssue);  // should be true since status = A and availability = A
    }
}

