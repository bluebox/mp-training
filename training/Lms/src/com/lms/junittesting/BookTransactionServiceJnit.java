package com.lms.junittesting;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.util.DBUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceImplJnit{

    private static BookServiceImpl bookService;

    @BeforeAll
    static void setup() {
        bookService = new BookServiceImpl();
    }

    @Test
    @Order(1)
    void testAddBook() {
        assertDoesNotThrow(() -> bookService.addBook(
                "JUnit in Action", "Craig Walls", BookCategory.SCIENCEFICTION, 'A', 'A'));
    }

    @Test
    @Order(2)
    void testGetAllBooks_AfterAdd() {
        List<Book> books = bookService.getAllBooks();
        assertFalse(books.isEmpty());
        Book lastBook = books.get(books.size() - 1);
        assertEquals("JUnit in Action", lastBook.getBookTitle());
        assertEquals("Craig Walls", lastBook.getBookAuthor());
    }

    @Test
    @Order(3)
    void testGetBookById() throws InvalidInputException {
        List<Book> books = bookService.getAllBooks();
        Book lastBook = books.get(books.size() - 1);
        String bookId = lastBook.getBookId();

        Book found = bookService.getBookById(bookId);
        assertNotNull(found);
        assertEquals("JUnit in Action", found.getBookTitle());
    }

    @Test
    @Order(4)
    void testUpdateBook() throws InvalidInputException {
        List<Book> books = bookService.getAllBooks();
        Book book = books.get(books.size() - 1); 
        String bookId = book.getBookId();

        boolean updated = bookService.updateBook(bookId,
                "JUnit Revised", "Updated Author", BookCategory.THRILLER, 'U', 'U');
        assertTrue(updated);

        Book updatedBook = bookService.getBookById(bookId);
        assertEquals("JUnit Revised", updatedBook.getBookTitle());
        assertEquals("Updated Author", updatedBook.getBookAuthor());
        assertEquals(BookCategory.THRILLER, updatedBook.getBookCategory());
    }

    @Test
    @Order(5)
    void testGetBookByInvalidId() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                bookService.getBookById("INVALID123"));
        assertTrue(ex.getMessage().toLowerCase().contains("not found"));
    }

    @Test
    @Order(6)
    void testAddBook_InvalidTitle() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                bookService.addBook("", "Author", BookCategory.FICTION, 'A', 'U'));
        assertTrue(ex.getMessage().toLowerCase().contains("book name"));
    }

    @Test
    @Order(7)
    void testUpdateBook_InvalidId() {
        Exception ex = assertThrows(InvalidInputException.class, () ->
                bookService.updateBook("", "Title", "Author", BookCategory.FICTION, 'A', 'A'));
        assertTrue(ex.getMessage().toLowerCase().contains("book id"));
    }
    @AfterAll
    static void cleanup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM books WHERE title = 'JUnit in Action' OR title = 'JUnit Revised'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}