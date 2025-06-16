package com.library.test;

import com.library.dao.*;
import com.library.enums.Availability;
import com.library.enums.Status;
import com.library.exception.UserDefinedException;
import com.library.service.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setup() {
        bookService = new BookService();
    }

    @Test
    public void testAddAndFetchBooks() throws Exception {
        Book book = new Book(12345432,"Java Programming","James Gosling","Programming",Status.Active,Availability.Available);
        bookService.addBook(book); // Insert book
        List<Book> books = bookService.getAllBooks(); // Fetch all

        assertTrue(
            books.stream().anyMatch(b -> b.getTitle().equals("Java Programming"))
        );
    }
    @Test(expected = UserDefinedException.class)
    public void testAddBookWithEmptyTitle() throws Exception {
        Book book = new Book(654323456,"","Author","Category");
        bookService.addBook(book);
    }
}
