package test.DAO;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Before;
import org.junit.Test;
import DAO.Databasemanager;
import domain.checking_enum;
import domain.checking_enum.Availability;
import domain.checking_enum.Status;
import domain.Book;


public class BookTest {
	private Databasemanager dao=new Databasemanager();


//public void setup() throws Exception {
//	service = new BookImplementation();
//	BookImplementation dao = new BookImplementation();
//	D
//	
//}
	@Test
	public void successbookadd() throws SQLException {
		Book b=new Book(1,"title","niha","car",Status.ACTIVE,Availability.AVAILABLE);
		int printmessage=dao.addBooks(b);
		assertEquals(1,printmessage);

		
	}
	@Test
	public void failbookadd() throws SQLException {
		Book b=new Book(1,"title","niha","car",Status.ACTIVE,Availability.AVAILABLE);
		int printmessage=dao.addBooks(b);
		
		assertEquals(0,printmessage);
		
	}
	@Test

	public void successupdatebook() throws SQLException {
		Book b=new Book("title","kavi","car",Status.ACTIVE,Availability.AVAILABLE);
		boolean p=dao.updateBookDetails(1,b);
		assertEquals(true,p);

		
	}
	@Test

	public void failupadtebook() throws SQLException {
		Book b=new Book(1,"title","kavi","car",Status.ACTIVE,Availability.AVAILABLE);
		boolean p=dao.updateBookDetails(1,b);
		assertEquals(false,p);
		

		
	}
	@Test
	public void testGetAllIssues() throws SQLException {
		//fail("Not yet implemented");
		List<Book> all = dao.viewallbooks();
		assertNotNull(all);
	}

	
	
}



