package com.lms.junittesting;

import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    @BeforeEach
    void clearBooks() throws SQLException {
        String[] testTitles = {
            "JUnit Book", "Book One", "Book Two", "Unique Book", "Duplicate Book",
            "Old Title", "New Title", "First Title", "Second Title"
        };
        for (String title : testTitles) {
            Book book = BookDao.getBookByTitle(title);
            if (book != null) {
                BookDao.deleteBookById(book.getBookId());
            }
        }
    }

    @Test
    void testGetBookById_ValidId_ReturnsBook() throws Exception {
        Book book = new Book();
        book.setBookTitle("JUnit Book");
        book.setBookAuthor("JUnit Author");
        book.setBookCategory(BookCategory.FICTION);
        book=BookDao.addBook(book);

        BookServiceImpl service = new BookServiceImpl();
        Book result = service.getBookById(book.getBookId());

        assertNotNull(result);
        assertEquals(book.getBookId(), result.getBookId());
        assertEquals("JUnit Book", result.getBookTitle());
    }

    @Test
    void testGetBookById_BookNotFound_ThrowsException() {
        BookServiceImpl service = new BookServiceImpl();
        assertThrows(InvalidInputException.class, () -> service.getBookById("9999"));
    }

    @Test
    void testGetAllBooks_ReturnsList() throws SQLException {
        Book book1 = new Book();
        book1.setBookTitle("Book One");
        book1.setBookAuthor("Author One");
        book1.setBookCategory(BookCategory.FICTION);
        Book book2 = new Book();
        book2.setBookTitle("Book Two");
        book2.setBookAuthor("Author Two");
        book2.setBookCategory(BookCategory.FANTASY);
        BookDao.addBook(book1);
        BookDao.addBook(book2);

        BookServiceImpl service = new BookServiceImpl();
        List<Book> books = service.getAllBooks();
        assertTrue(books.size() >= 2);
    }

    @Test
    void testAddBook_Success() throws InvalidInputException, SQLException {
        Book book = new Book();
        book.setBookTitle("Unique Book");
        book.setBookAuthor("Test Author");
        book.setBookCategory(BookCategory.FICTION);
        book.setStatus('A');
        book.setAvailability('A');

        BookServiceImpl service = new BookServiceImpl();
        service.addBook(book);

        Book result = BookDao.getBookByTitle("Unique Book");
        assertNotNull(result);
        assertEquals("Unique Book", result.getBookTitle());
    }

    @Test
    void testAddBook_DuplicateTitle_ThrowsException() throws InvalidInputException {
        Book book = new Book();
        book.setBookTitle("Duplicate Book");
        book.setBookAuthor("Duplicate Author");
        book.setBookCategory(BookCategory.COMICS);
        book.setStatus('A');
        book.setAvailability('A');
        try {
			BookDao.addBook(book);
		} catch (SQLException e) {
			throw new InvalidInputException("Error connecting to server.");
		}

        BookServiceImpl service = new BookServiceImpl();
        Book duplicate = new Book();
        duplicate.setBookTitle("Duplicate Book");

        assertThrows(InvalidInputException.class, () -> service.addBook(duplicate));
    }

    @Test
    void testUpdateBook_Success() throws InvalidInputException {
        Book book = new Book();
        book.setBookTitle("Old Title");
        book.setBookAuthor("Old Author");
        book.setBookCategory(BookCategory.HISTORY);
        book.setStatus('A');
        book.setAvailability('A');
        try {
			book=BookDao.addBook(book);
		} catch (SQLException e) {
			throw new InvalidInputException("Error connecting to server.");
		}

        Book update = new Book();
        update.setBookId(book.getBookId());
        update.setBookTitle("New Title");
        update.setBookAuthor("New Author");
        update.setBookCategory(BookCategory.FICTION);
        update.setStatus('A');
        update.setAvailability('A');

        BookServiceImpl service = new BookServiceImpl();
        Boolean updated = service.updateBook(update);

        assertTrue(updated);
        Book result;
		try {
			result = BookDao.getBookById(book.getBookId());
		} catch (SQLException e) {
			throw new InvalidInputException("Error connecting to server.");
		}
        assertEquals("New Title", result.getBookTitle());
    }

    @Test
    void testUpdateBook_DuplicateTitle_ThrowsException() throws InvalidInputException, SQLException {
        Book book1 = new Book();
        book1.setBookTitle("First Title");
        book1.setBookAuthor("Author1");
        book1.setBookCategory(BookCategory.FICTION);
        book1.setStatus('A');
        book1.setAvailability('A');
        book1 = BookDao.addBook(book1);

        Book book2 = new Book();
        book2.setBookTitle("Second Title");
        book2.setBookAuthor("Author2");
        book2.setBookCategory(BookCategory.FANTASY);
        book2.setStatus('A');
        book2.setAvailability('A');
        book2 = BookDao.addBook(book2);

        Book update = new Book();
        update.setBookId(book1.getBookId());
        update.setBookTitle("Second Title");
        update.setBookAuthor("Author1");
        update.setBookCategory(BookCategory.FICTION);
        update.setStatus('A');
        update.setAvailability('A');

        BookServiceImpl service = new BookServiceImpl();
        assertThrows(InvalidInputException.class, () -> service.updateBook(update));
    }
}



