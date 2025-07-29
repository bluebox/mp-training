package test.DAO;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Issue_records;
import domain.checking_enum.Status_issue;
//import com.library_management.utilities.DBConnection;
import DAO.Issue_RecordDAO;

public class IssueRecordDaoImplementationTest {
	
	private Issue_RecordDAO dao=new Issue_RecordDAO();
	
	//@Before
//    public void setUp() {
//		 DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms");
//        dao = new IssueRecordDaoImplementation();
//    }
//	
	@Test
	public void testIssueBook() throws SQLException {
		boolean print_message = dao.isBookIssued(1,1);
		//System.out.print(print_message);
		
		assertEquals(true,print_message);
	}
	
	@Test
	public void testIssueBookFailCase() throws SQLException {
		//fail("Not yet implemented");
		boolean print_message = dao.issueBook(1,1);
		assertEquals(false,print_message);
	}

	@Test
	public void testReturnBook() throws SQLException {
		//fail("Not yet implemented");
		boolean print_message = dao.returnBook(1, 1);
		assertEquals(true,print_message);
	}

	@Test
	public void testReturnBookFailCase() throws SQLException {
		//fail("Not yet implemented");
		boolean print_message = dao.returnBook(1, 2);
		assertEquals(false,print_message);
	}
	
	@Test
	public void testGetAllIssues() throws SQLException {
		//fail("Not yet implemented");
		List<Issue_records> all = dao.printAllIssueRecords();
		assertNotNull(all);
	}
//	public static void main(String[] args) throws SQLException {
//		IssueRecordDaoImplementationTest  i=new IssueRecordDaoImplementationTest();
//		System.out.println(i.testIssueBook());
//	}

}
