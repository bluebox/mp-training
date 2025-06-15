package com.library.test.service;

import com.library.domain.Book;
import com.library.service.BookService;
import com.library.dao.BookDAO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    class TestableBookService extends BookService {
        private final BookDAO fakeDAO;

        public TestableBookService(BookDAO fakeDAO) {
            this.fakeDAO = fakeDAO;
        }

        @Override
		public BookDAO getBookDAO() {
            return fakeDAO;
        }

        
        @Override
        public boolean canIssueBook(Book book) {
            return "A".equals(book.getStatus()) && "A".equals(book.getAvailability());
        }
    }

    class FakeBookDAO extends BookDAO {
        private boolean insertResult;
        private List<Book> bookList;

        public FakeBookDAO(boolean insertResult, List<Book> bookList) {
            this.insertResult = insertResult;
            this.bookList = bookList;
        }

        @Override
        public List<Book> getAllBooks() {
            return bookList;
        }

        @Override
        public boolean insertBook(Book book) {
            return insertResult;
        }
    }

    @Test
    public void testFetchAllBooks_returnsExpectedList() {
        List<Book> mockBooks = Arrays.asList(
            new Book(1, "Java", "Author A", "Programming", "A", "A"),
            new Book(2, "Python", "Author B", "Tech", "A", "A")
        );

        BookService bookService = new TestableBookService(new FakeBookDAO(true, mockBooks));
        List<Book> books = bookService.fetchAllBooks();

        assertEquals(2, books.size());
        assertEquals("Java", books.get(0).getTitle());
        assertEquals("Python", books.get(1).getTitle());
    }

    @Test
    public void testAddBook_returnsTrue_whenInsertSucceeds() {
        Book book = new Book("C++", "Author C", "Programming", "A", "A");
        BookService bookService = new TestableBookService(new FakeBookDAO(true, null));

        assertTrue(bookService.addBook(book));
    }

    @Test
    public void testAddBook_returnsFalse_whenInsertFails() {
        Book book = new Book("Rust", "Author D", "System", "Avbl", "Aich");
        BookService bookService = new TestableBookService(new FakeBookDAO(false, null));

        assertFalse(bookService.addBook(book));
    }

}
