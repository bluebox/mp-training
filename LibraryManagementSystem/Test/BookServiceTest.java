package com.library.test;

import com.library.model.Book;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.interfaces.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookServiceImplementation();
    }

    @Test
    public void testAddBookAndFetch() throws Exception {
        Book book = new Book(0, "JUnit Basics", "Tester", "Testing", 'A', 'A');
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks();
        boolean found = books.stream()
                .anyMatch(b -> b.getTitle().equals("JUnit Basics") && b.getAuthor().equals("Tester"));
        assertTrue("Book should be added and retrievable", found);
    }

    @Test
    public void testUpdateBookDetails() throws Exception {
        Book book = new Book(0, "Update Test", "Author1", "Category1", 'A', 'A');
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks();
        Book lastAdded = books.get(books.size() - 1);

        lastAdded.setTitle("Updated Title");
        lastAdded.setAuthor("Updated Author");
        lastAdded.setCategory("Updated Category");

        bookService.updateBookDetails(lastAdded);

        List<Book> updatedBooks = bookService.getAllBooks();
        Book updated = updatedBooks.stream()
                .filter(b -> b.getBookId() == lastAdded.getBookId())
                .findFirst()
                .orElse(null);

        assertNotNull("Book should exist after update", updated);
        assertEquals("Updated Title", updated.getTitle());
        assertEquals("Updated Author", updated.getAuthor());
        assertEquals("Updated Category", updated.getCategory());
    }

    @Test
    public void testUpdateAvailability() throws Exception {
        Book book = new Book(0, "Available Book", "Author2", "Category2", 'A', 'A');
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks();
        Book lastAdded = books.get(books.size() - 1);

        bookService.updateAvailability(lastAdded.getBookId(), 'I');

        List<Book> updatedBooks = bookService.getAllBooks();
        Book updated = updatedBooks.stream()
                .filter(b -> b.getBookId() == lastAdded.getBookId())
                .findFirst()
                .orElse(null);

        assertNotNull("Book should exist after availability update", updated);
        assertEquals('I', updated.getAvailability());
    }

   
}
