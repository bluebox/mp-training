package Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import DAO.IssueRecordImplementation;
import Domain.IssueRecord;
import Domain.IssueStatus;



public class IssueRecordImplementationTest {
	
	private IssueRecordImplementation dao=new IssueRecordImplementation();
	
	//@Before
//    public void setUp() {
//		 DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms");
//        dao = new IssueRecordDaoImplementation();
//    }
//	
	@Test
	public void testIssueBook() throws SQLException {
		boolean print_message = dao.checkBookIssue(1,1);

		
		assertEquals(true,print_message);
	}
	
	@Test
	public void testIssueBookFailCase() throws SQLException {
		boolean print_message = dao.checkBookIssue(1,1);
		assertEquals(false,print_message);
	}

	@Test
	public void testReturnBook() throws SQLException {
		int print_message = dao.returnBook(1, 1);
		assertEquals(1,print_message);
	}

	@Test
	public void testReturnBookFailCase() throws SQLException {
		int print_message = dao.returnBook(1, 2);
		assertEquals(0,print_message);
	}
	
	@Test
	public void testGetAllIssues() throws SQLException {
		List<IssueRecord> all = dao.getAllIssueRecords();
		assertNotNull(all);
	}
//	public static void main(String[] args) throws SQLException {
//		IssueRecordDaoImplementationTest  i=new IssueRecordDaoImplementationTest();
//		System.out.println(i.testIssueBook());
//	}

}
