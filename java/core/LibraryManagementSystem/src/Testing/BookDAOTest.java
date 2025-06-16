package Testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import Pojo.*;
import Dao.*;

public class BookDAOTest {
	private BookDao bookDAO;
     public BookDAOTest() throws SQLException {
	 bookDAO = new BookDao();
     }
	@Test
	public void testAddBook() throws Exception {
		Book book = new Book();
		book.setTitle("Test Book");
		book.setAuthor("Test Author");
		book.setCategory("Test Category");
		book.setStatus('A');
		book.setAvailability('A');
		bookDAO.addBook(book);
	}

	@Test
	public void testUpdateBook() throws Exception {
		// Assuming BookId = 1 exists
		Book book = bookDAO.getBookById(1);
		book.setTitle("Updated Title");
		bookDAO.updateBookDetails(book);
	}

	@Test
	public void testViewAllBooks() throws SQLException {
		List<Book> books = bookDAO.getAllBooks();
		assertNotNull(books);
	}
}
