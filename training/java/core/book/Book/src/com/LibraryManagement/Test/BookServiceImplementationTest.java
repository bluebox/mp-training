//package com.LibraryManagement.Test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.LibraryManagement.exception.BookDAOException;
//import com.LibraryManagement.models.Book;
//import com.LibraryManagement.service.implementation.BookServiceImplementation;
//
//public class BookServiceImplementationTest {
//
//	private BookServiceImplementation bookService;
//
//	@Before
//	public void setUp() {
//		bookService = new BookServiceImplementation();
//	}
//
//	@Test(expected = BookDAOException.class)
//	public void testAddBook_NullBook_ThrowsException() {
//		bookService.addBook(null);
//	}
//
//	@Test(expected = BookDAOException.class)
//	public void testAddBook_MissingFields_ThrowsException() {
//		Book book = new Book(null, "Author", "Category");
//		bookService.addBook(book);
//	}
//
//	@Test
//	public void testAddBook_ValidBook_DoesNotThrow() {
//		Book book = new Book("Effective Java", "Joshua Bloch", "Programming");
//		try {
//			bookService.addBook(book);
//			// No exception expected
//		} catch (BookDAOException e) {
//			fail("Exception should not be thrown for valid book.");
//		}
//	}
//
//	@Test(expected = BookDAOException.class)
//	public void testUpdateBook_InvalidId_ThrowsException() {
//		Book book = new Book("Clean Code", "Robert C. Martin", "Programming");
//		book.setBookId(0); // Invalid ID
//		bookService.updateBook(book);
//	}
//
//	@Test(expected = BookDAOException.class)
//	public void testUpdateAvailability_InvalidId_ThrowsException() {
//		Book book = new Book("Test Driven Development", "Kent Beck", "Programming");
//		book.setBookId(-1);
//		bookService.updateAvailability(book);
//	}
//
//	@Test
//	public void testGetAllBooks_ReturnsList() {
//		List<Book> books = bookService.getAllBooks();
//		assertNotNull(books);
//	}
//
//}