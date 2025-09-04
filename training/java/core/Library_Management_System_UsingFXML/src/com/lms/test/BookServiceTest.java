package com.lms.test;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.lms.exceptions.DBConstrainsException;
import com.lms.model.Book;
import com.lms.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Map;

public class BookServiceTest {

    private BookServiceImpl bookService;
    private Book testBook;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl();
        testBook = new Book(0, "Test Book", "Test Author", "Fiction", 'A', 'A');
        System.out.println("Book id when created :" +testBook.getBook_Id());
    }

    @Test
    public void testAddNewBook_Success() {
    	System.out.println("book id before adding it "+testBook.getBook_Id());
        try {
			bookService.addNewBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}
        assertNotEquals(0, testBook.getBook_Id());

        Book retrievedBook = bookService.getBookById(testBook.getBook_Id());
        assertNotNull(retrievedBook);
        assertEquals(testBook.getBook_Title(), retrievedBook.getBook_Title());
        assertEquals(testBook.getBook_Author(), retrievedBook.getBook_Author());
    }

    @Test
    public void testGetAllBooks_Success() {
        try {
			bookService.addNewBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}
        
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        
        boolean found = books.stream()
            .anyMatch(b -> b.getBook_Title().equals(testBook.getBook_Title()));
        assertTrue(found);
    }

    @Test
    public void testUpdateBook_Success() {

        try {
			bookService.addNewBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}
        int bookId = testBook.getBook_Id();

        testBook.setBook_Title("Updated Title");
        testBook.setBook_Author("Updated Author");
        try {
			bookService.updateBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}

        
        Book updatedBook = bookService.getBookById(bookId);
        assertEquals("Updated Title", updatedBook.getBook_Title());
        assertEquals("Updated Author", updatedBook.getBook_Author());
    }

    @Test
    public void testUpdateBookAvailability_Success() {
        try {
			bookService.addNewBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}
        int bookId = testBook.getBook_Id();

        bookService.updateBookAvailability(bookId, false);

        Book updatedBook = bookService.getBookById(bookId);
        assertEquals('I', updatedBook.getBook_Availability());

        bookService.updateBookAvailability(bookId, true);
        updatedBook = bookService.getBookById(bookId);
        assertEquals('A', updatedBook.getBook_Availability());
    }

    @Test
    public void testGetBooksByCategory_Success() {
        Book fictionBook1 = new Book(0, "Fiction Book 1", "Author 1", "Fiction", 'A', 'A');
        Book fictionBook2 = new Book(0, "Fiction Book 2", "Author 2", "Fiction", 'A', 'A');
        Book nonFictionBook = new Book(0, "Non Fiction Book", "Author 3", "Non-Fiction", 'A', 'A');

        
        try {
        	bookService.addNewBook(fictionBook1);
			bookService.addNewBook(fictionBook2);
			bookService.addNewBook(nonFictionBook);
        } catch (DBConstrainsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        Map<String, Long> categoryMap = bookService.getBooksByCategory();
        
        assertNotNull(categoryMap);
        assertTrue(categoryMap.containsKey("Fiction"));
        assertTrue(categoryMap.containsKey("Non-Fiction"));
        assertTrue(categoryMap.get("Fiction") >= 2);
        assertTrue(categoryMap.get("Non-Fiction") >= 1);
    }

    @Test
    public void testGetBookById_Success() {
        
        try {
			bookService.addNewBook(testBook);
		} catch (DBConstrainsException e) {
			e.printStackTrace();
		}
        int bookId = testBook.getBook_Id();
        Book retrievedBook = bookService.getBookById(bookId);
        
        assertNotNull(retrievedBook);
        assertEquals(bookId, retrievedBook.getBook_Id());
        assertEquals(testBook.getBook_Title(), retrievedBook.getBook_Title());
        assertEquals(testBook.getBook_Author(), retrievedBook.getBook_Author());
        assertEquals(testBook.getBook_Category(), retrievedBook.getBook_Category());
    }
}
