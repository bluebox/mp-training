package Library.src.test.java.com.LibraryManagement.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Library.src.main.java.com.LibraryManagement.dao.BookDAO;
import Library.src.main.java.com.LibraryManagement.model.Book;
import Library.src.main.java.com.LibraryManagement.service.BookServiceImpl;

public class BookServiceImplTest {

    private BookDAO bookDAO;
    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        bookDAO = mock(BookDAO.class);
        bookService = new BookServiceImpl(bookDAO);
    }

    @Test
    public void testAddBook_Valid() throws Exception {
        Book book = new Book(1, "Java Basics", "Author A" ,"education" , 'A','A');
        bookService.addBook(book);
        verify(bookDAO).addBook(book);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddBook_Invalid_Null() throws Exception {
        bookService.addBook(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddBook_InvalidFields() throws Exception {
        bookService.addBook(new Book(0, null, null,null, 'A','I'));
    }

    @Test
    public void testUpdateBook_Valid() throws Exception {
    	 Book book = new Book(1, "Java Basics", "Author A" ,"education" , 'A','A');
        bookService.updateBook(book);
        verify(bookDAO).updateBook(book);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBook_Invalid() throws Exception {
        bookService.updateBook(new Book(0, "x", "y", "z",'A','A'));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(
            new Book(1, "Java", "Author1","academics",'A','A'),
            new Book(2, "Python", "Author2","academics", 'I','I')
        );
        when(bookDAO.getAllBooks()).thenReturn(books);
        assertEquals(2, bookService.getAllBooks().size());
    }

//    @Test
//    public void testGetBookById_Valid() throws Exception {
//    	 Book book = new Book(1, "Java Basics", "Author A" ,"education" , 'A','A');
//        when(bookDAO.getBookById(1)).thenReturn(book);
//        assertEquals("Java", bookService.getBookById(1).getTitle());
//    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBookById_InvalidId() throws Exception {
        bookService.getBookById(0);
    }

    @Test
    public void testUpdateBookAvailability_Valid() throws Exception {
        bookService.updateBookAvailability(1, 'I');
        verify(bookDAO).updateBookAvailability(1, 'I');
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBookAvailability_Invalid() throws Exception {
        bookService.updateBookAvailability(0, 'X');
    }
}
