package com.libraryManagementSystem.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.services.impl.IssueServiceImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;
import com.libraryManagementSystem.utilities.IssueStatus;

public class IssueServicesTest {

	private IssueService issueService;
	private BookServices bookServices;

	private Book testBook;
	private int testMemberId = 1; // ✅ change this if needed

	@Before
	public void setUp() throws Exception {
		issueService = new IssueServiceImpl();
		bookServices = new BookServicesImpl();

		// Create a fresh available book
		Book book = new Book(0, "JUnit for Java", "Tester", BookCategory.EDUCATION, BookStatus.ACTIVE,
				BookAvailability.AVAILABLE);
		bookServices.addBook(book);

		// Find the book we just added
		List<Book> books = bookServices.getBooks();
		for (Book b : books) {
			if (b.getTitle().equalsIgnoreCase("JUnit for Java")) {
				testBook = b;
				break;
			}
		}

		assertNotNull("Test book must not be null", testBook);
		assertEquals(BookAvailability.AVAILABLE, testBook.getAvailability());
	}

	@Test
	public void testAddIssueValid() {
		IssueRecord issue = new IssueRecord(0, 18, 4, IssueStatus.ISSUED, LocalDate.now(), null);

		try {
			issueService.addIssue(issue);
			assertTrue(true); // success
		} catch (InvalidException e) {
			fail("Issuing failed: " + e.getMessage());
		}
	}

	@Test(expected = InvalidException.class)
	public void testAddIssueFutureDate() throws InvalidException {
		IssueRecord issue = new IssueRecord(0, testBook.getBookId(), testMemberId, IssueStatus.ISSUED,
				LocalDate.now().plusDays(2), null);
		issueService.addIssue(issue);
	}

	@Test(expected = InvalidException.class)
	public void testAddIssueAlreadyIssuedBook() throws InvalidException {
		IssueRecord issue = new IssueRecord(0, testBook.getBookId(), testMemberId, IssueStatus.ISSUED, LocalDate.now(),
				null);
		issueService.addIssue(issue); // second time – should fail
	}

	@Test
	public void testReturnBookValid() {
		try {
			issueService.returnBook(testBook, testMemberId, LocalDate.now());
			assertTrue(true); // success
		} catch (InvalidException e) {
			fail("Returning book failed: " + e.getMessage());
		}
	}

	@Test(expected = InvalidException.class)
	public void testReturnBookWithFutureDate() throws InvalidException {
		issueService.returnBook(testBook, testMemberId, LocalDate.now().plusDays(3));
	}

	@Test(expected = InvalidException.class)
	public void testReturnBookNotIssued() throws InvalidException {
		// return again – should now be available
		issueService.returnBook(testBook, testMemberId, LocalDate.now());
	}
}
