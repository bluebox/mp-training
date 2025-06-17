package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Book;
import service.BookService;

class BookServiceTest {
    BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void testNotYetImplemented() {
        fail("Not yet implemented");
    }

    @Test
    void testAddBook_InvalidTitle() {
        Book book = new Book(0, "", "Author", "Category", 'A','A');
        assertThrows(Exception.class, () -> bookService.addBook(book));
    }

    @Test
    void testAddBook_ValidBook() throws Exception {
        Book book = new Book(0, "Java Basics", "John Doe", "Programming",  'A','A');
        bookService.addBook(book);
        assertTrue(true);
    }
} 