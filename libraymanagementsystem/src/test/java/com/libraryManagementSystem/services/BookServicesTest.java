package com.libraryManagementSystem.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libraryManagementSystem.domain.Book;
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
	public void testAddValidBook() {
		Book book = new Book(0, "Test Driven Java", "Kent Beck", BookCategory.HORROR, BookStatus.ACTIVE,
				BookAvailability.AVAILABLE);
		try {
			bookServices.addBook(book);
			assertTrue(true); // Passed if no exception
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test(expected = InvalidException.class)
	public void testAddBookWithInvalidTitle() throws InvalidException {
		Book book = new Book(0, "", "Author", BookCategory.SCIENCE, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
		bookServices.addBook(book);
	}

	@Test
	public void testGetAllBooks() {
		try {
			List<Book> books = bookServices.getBooks();
			assertNotNull(books);
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testUpdateBookWithValidData() {
		try {
			List<Book> books = bookServices.getBooks();
			if (!books.isEmpty()) {
				Book oldBook = books.get(0);
				Book updatedBook = new Book(oldBook.getBookId(), "Updated Title", oldBook.getAuthor(),
						oldBook.getCategory(), oldBook.getStatus(), oldBook.getAvailability());

				bookServices.updateBook(updatedBook, oldBook);
				assertTrue(true);
			}
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test(expected = InvalidException.class)
	public void testUpdateBookWithInvalidAuthor() throws InvalidException {
		Book book = new Book(1, "Valid Title", "", BookCategory.HISTORY, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
		bookServices.updateBook(book, book);
	}

	@Test
	public void testUpdateBookAvailability() {
		try {
			List<Book> books = bookServices.getBooks();
			if (!books.isEmpty()) {
				Book book = books.get(0);
				bookServices.updateBookAvailability(book, BookAvailability.ISSUED);
				assertTrue(true);
			}
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testGetBookCountByCategory() {
		try {
			assertNotNull(bookServices.getBookCountByCategory());
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testGetActiveIssuedBooks() {
		try {
			assertNotNull(bookServices.getActiveIssuedBooks());
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	public void testGetOverDueBooks() {
		try {
			assertNotNull(bookServices.getOverDueBooks());
		} catch (InvalidException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}
}
