package test.com.dao;

import com.library.domain.Book;
import com.library.services.BookService;

import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

	private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookService();
    }

    @Test
    public void testGetAllBooks_NotNull() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull("Book list should not be null", books);
    }

    @Test
    public void testAddBookAndRetrieve() {
        Book book = new Book(0, "JUnit 101", "John Tester", "Education", 'A', 'A');

        boolean added = bookService.addBook(book);
        assertTrue("Book should be added successfully", added);

        List<Book> books = bookService.getAllBooks();
        assertTrue("Book list should contain at least one book", books.size() > 0);

        boolean found = books.stream().anyMatch(b -> "JUnit 101".equals(b.getTitle()));
        assertTrue("Added book should exist in the list", found);
    }
}

