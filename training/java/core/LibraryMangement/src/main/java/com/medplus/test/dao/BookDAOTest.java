package com.medplus.test.dao;

import com.medplus.dao.impl.BookDAOImpl;
import com.medplus.model.Book;
import org.junit.*;
import java.util.*;

public class BookDAOTest {
    private BookDAOImpl dao;
    @Before
    public void setUp() { dao = new BookDAOImpl(); }
    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(0, "Title", "Author", "Category", 'A', 'A');
        Assert.assertTrue(dao.addBook(book));
    }
    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = dao.getAllBooks();
        Assert.assertNotNull(books);
    }
    @Test
    public void testUpdateBookDetails() throws Exception {
        Book book = dao.getBookById(1);
        book.setTitle("Updated Title");
        Assert.assertTrue(dao.updateBook(book));
    }
    @Test
    public void testUpdateAvailability() throws Exception {
        Assert.assertTrue(dao.updateAvailability(1, 'I'));
    }
    @Test
    public void testGetBookById() throws Exception {
        Book book = dao.getBookById(1);
        Assert.assertNotNull(book);
    }
}