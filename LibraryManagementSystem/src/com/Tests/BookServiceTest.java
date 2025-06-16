package com.Tests;
import com.Exception.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Models.Book;
import com.Service.BookService;

class BookServiceTest {
	BookService bookService;
	@Test
	void test() {
		fail("Not yet implemented");
	}
	@org.junit.Test(expected = InvalidInputException.class)
    public void testAddBook_InvalidTitle() throws Exception {
        Book book = new Book(0, "", "Author", "Category", "A", "A");
        bookService.addBook(book);
    }

    @Test
    public void testAddBook_ValidBook() throws Exception {
        Book book = new Book(0, "Java Basics", "John Doe", "Programming",  "A", "A");
        bookService.addBook(book);
        assertTrue(true); // if no exception, test passed
    }
}
