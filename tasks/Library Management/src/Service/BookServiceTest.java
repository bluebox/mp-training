package Service;

import casestudy.Book;
import casestudy.LibraryException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookService();
    }

    @Test(expected = LibraryException.class)
    public void testAddBookWithEmptyTitle() throws LibraryException {
        Book book = new Book("", "Author", "Fiction", 'A', 'A');
        bookService.addBook(book);
    }

    @Test(expected = LibraryException.class)
    public void testAddBookWithEmptyAuthor() throws LibraryException {
        Book book = new Book("Title", "", "Fiction", 'A', 'A');
        bookService.addBook(book);
    }

    @Test(expected = LibraryException.class)
    public void testUpdateBookWithInvalidId() throws LibraryException {
        // Cannot pass ID directly; test focuses on service logic which expects valid ID internally.
        Book book = new Book("Title", "Author", "Fiction", 'A', 'A');
        bookService.updateBook(book); // Will fail unless book ID is set within DAO or extended for testing
    }

    @Test(expected = LibraryException.class)
    public void testUpdateBookStatusInvalidId() throws LibraryException {
        bookService.updateBookStatus(0, 'A');
    }

    @Test(expected = LibraryException.class)
    public void testUpdateBookStatusInvalidStatus() throws LibraryException {
        bookService.updateBookStatus(1, 'X');
    }

    @Test(expected = LibraryException.class)
    public void testIsBookActiveInvalidId() throws LibraryException {
        bookService.isBookActive(-10);
    }

    @Test
    public void testGetAllBooks() throws LibraryException {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
    }

    @Test
    public void testGetBooksCountByCategory() throws LibraryException {
        Map<String, Long> countMap = bookService.getBooksCountByCategory();
        assertNotNull(countMap);
    }
}
