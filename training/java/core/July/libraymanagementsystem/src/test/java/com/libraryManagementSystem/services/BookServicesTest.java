package com.libraryManagementSystem.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.DuplicateBookException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;

public class BookServicesTest {

	private BookServices bookServices;

	@Before
	public void setUp() {
		bookServices = new BookServicesImpl();
	}

	@Test
	public void testAddValidBook()
			throws InvalidBookDataException, DuplicateBookException, DatabaseOperationException, InvalidException {
		Book book = new Book(0, "Test Driven Java", "Kent Beck", BookCategory.HORROR, BookStatus.ACTIVE,
				BookAvailability.AVAILABLE);
		bookServices.addBook(book);
		assertTrue(true); // Passed if no exception
	}

	@Test(expected = InvalidException.class)
	public void testAddBookWithInvalidTitle()
			throws InvalidException, InvalidBookDataException, DuplicateBookException, DatabaseOperationException {
		Book book = new Book(0, "", "Author", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
		bookServices.addBook(book);
	}

	@Test
	public void testGetAllBooks() throws DatabaseOperationException {
		List<Book> books = bookServices.getBooks();
		assertNotNull(books);
	}

	@Test
	public void testUpdateBookWithValidData() throws DatabaseOperationException, BookNotFoundException,
			InvalidBookDataException, DuplicateBookException, InvalidException {
		List<Book> books = bookServices.getBooks();
		if (!books.isEmpty()) {
			Book oldBook = books.get(0);
			Book updatedBook = new Book(oldBook.getBookId(), "Updated Title", oldBook.getAuthor(),
					oldBook.getCategory(), oldBook.getStatus(), oldBook.getAvailability());

			bookServices.updateBook(updatedBook, oldBook);
			assertTrue(true);
		}
	}

	@Test(expected = InvalidException.class)
	public void testUpdateBookWithInvalidAuthor() throws InvalidException, BookNotFoundException,
			InvalidBookDataException, DatabaseOperationException, DuplicateBookException {
		Book book = new Book(1, "Valid Title", "", BookCategory.HISTORY, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
		bookServices.updateBook(book, book);
	}

	@Test
	public void testUpdateBookAvailability()
			throws DatabaseOperationException, BookNotFoundException, InvalidException {
		List<Book> books = bookServices.getBooks();
		if (!books.isEmpty()) {
			Book book = books.get(0);
			bookServices.updateBookAvailability(book, BookAvailability.ISSUED);
			assertTrue(true);
		}
	}

	@Test
	public void testGetBookCountByCategory() throws DatabaseOperationException, InvalidException {
		assertNotNull(bookServices.getBooksCountByCategory());
	}

	@Test
	public void testGetActiveIssuedBooks() throws DatabaseOperationException, InvalidException {
		assertNotNull(bookServices.getActiveIssuedBooks());
	}

	@Test
	public void testGetOverDueBooks() throws DatabaseOperationException {
		assertNotNull(bookServices.getOverDueBooks());
	}
}
