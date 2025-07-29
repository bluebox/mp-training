package Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Before;
import org.junit.Test;
import DAO.BookImplementation;
import Domain.BookStatus;
import Domain.Book;
import Domain.BookAvailability;

public class BookTest {
	private BookImplementation dao=new BookImplementation();


//public void setup() throws Exception {
//	service = new BookImplementation();
//	BookImplementation dao = new BookImplementation();
//	D
//	
//}
	@Test
	public void successbookadd() throws SQLException {
		Book b=new Book(1,"title","niha","car",BookStatus.ACTIVE,BookAvailability.AVAILABLE);
		int printmessage=dao.AddBook(b);
		assertEquals(1,printmessage);

		
	}
	@Test
	public void failbookadd() throws SQLException {
		Book b=new Book(1,"title","niha","car",BookStatus.ACTIVE,BookAvailability.AVAILABLE);
		int printmessage=dao.AddBook(b);
		
		assertEquals(0,printmessage);
		
	}
	@Test

	public void successupdatebook() throws SQLException {
		Book b=new Book(1,"title","kavi","car",BookStatus.ACTIVE,BookAvailability.AVAILABLE);
		Book p=dao.updateBookDetails(b);
		assertNotNull(p);

		
	}
	@Test

	public void failupadtebook() throws SQLException {
		Book b=new Book(1,"title","kavi","car",BookStatus.ACTIVE,BookAvailability.AVAILABLE);
		Book p=dao.updateBookDetails(b);
		assertNull(p);

		

		
	}
	@Test
	public void testGetAllIssues() throws SQLException {
		//fail("Not yet implemented");
		List<Book> all = dao.getBooks();
		assertNotNull(all);
	}

	
	
}



