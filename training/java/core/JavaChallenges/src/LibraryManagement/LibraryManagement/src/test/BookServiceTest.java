package test;

import model.Book;
import service.BookService;
import exception.InvalidInputException;
import exception.DatabaseException;
import org.junit.Before;
import org.junit.Test;

import enums.Availability;
import enums.Status;

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
        Book book = new Book();
        book.setTitle("Java Programming");
        book.setAuthor("James Gosling");
        book.setCategory("Programming");
        book.setStatus(Status.Active);
        book.setAvailability(Availability.Available);

        bookService.addBook(book); 
        List<Book> books = bookService.getAllBooks(); 
        assertTrue(
            books.stream().anyMatch(b -> b.getTitle().equals("Java Programming"))
        );
    }

    @Test(expected = InvalidInputException.class)
    public void testAddBookWithEmptyTitle() throws Exception {
        Book book = new Book();
        book.setTitle("");
        book.setAuthor("Author");
        book.setCategory("Category");
        book.setStatus(Status.Active);
        book.setAvailability(Availability.Available);

        bookService.addBook(book);
    }
}
