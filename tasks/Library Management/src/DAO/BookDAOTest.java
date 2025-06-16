package DAO;

import casestudy.Book;
import casestudy.LibraryException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDAOTest {

    private BookDAO bookDAO;

    @Before
    public void setUp() {
        bookDAO = new BookDAO();
    }

    @Test
    public void testAddAndGetAllBooks() throws LibraryException {
        Book book = new Book("JUnit Book", "Tester", "TestCategory", 'A', 'A');
        bookDAO.addBook(book);

        List<Book> books = bookDAO.getAllBooks();
        assertTrue(books.stream().anyMatch(b -> b.getTitle().equals("JUnit Book")));
    }

    @Test
    public void testUpdateBook() throws LibraryException {
        Book book = new Book("Book1", "Author1", "Category", 'A', 'A');
        bookDAO.addBook(book);
        List<Book> allBooks = bookDAO.getAllBooks();
        Book latest = allBooks.get(allBooks.size() - 1);

        latest.setTitle("Book2");
        bookDAO.updateBook(latest);

        Book updated = bookDAO.getBookById(latest.getBookId());
        assertEquals("Book2", updated.getTitle());
    }

    @Test
    public void testUpdateBookStatus() throws LibraryException {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) return;
        Book book = books.get(0);

        bookDAO.updateBookStatus(book.getBookId(), 'I');
        Book updated = bookDAO.getBookById(book.getBookId());
        assertEquals('I', updated.getStatus());
    }

    @Test(expected = LibraryException.class)
    public void testGetBookByInvalidId() throws LibraryException {
        bookDAO.getBookById(-1);
    }
}
