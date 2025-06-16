package Testing;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import Pojo.*;
import Dao.*;

public class IssueRecordDAOTest {
	private IssueRecordDAO issueDAO;

	public IssueRecordDAOTest() throws SQLException {
		issueDAO = new IssueRecordDAO();
	}
    @Test
	public void testIssueBook() throws Exception {
		issueDAO.issueBook(1, 1); 
	}

	@Test
	public void testReturnBook() throws Exception {
		issueDAO.returnBook(1);
	}

	@Test
	public void testGetAllIssuedRecords() throws SQLException {
		List<IssueRecord> records = issueDAO.getAllIssuedRecords();
		assertNotNull(records);
	}

}
