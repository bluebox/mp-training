package com.lms.test;
import org.junit.*;

import com.lms.Utils.DatabaseUtil;
import com.lms.dao.DataBookDao;
import com.lms.dao.IssueRecordDao;
import com.lms.dao.IssueRecordDaoImpl;
import com.lms.dao.MemberDaoImpl;
import com.lms.model.Book;
import com.lms.model.Issue_Records;
import com.lms.model.OverDueList;
import static org.junit.Assert.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
public class TestIssueRecordsImpl {

    private static final String url = DatabaseUtil.getUrl();
    private static final String user = DatabaseUtil.getUser();
    private static final String password = DatabaseUtil.getPassword();

	 private IssueRecordDao issueRecordDao;

	    @Before
	    public void setUp() {
	    	issueRecordDao = new IssueRecordDaoImpl();
	    }
	   
	    @Test
	    public void testAddIssueRecord() {
	        int bookId = 150; 
	        int memberId = 100;
			try{
				if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
					Book book=new DataBookDao().getBookById(bookId);
					char status=book.getBook_Status();
					char available=book.getBook_Availability();
					if((!String.valueOf(status).equals("I")) && (!String.valueOf(available).equals("I"))) {
				    	Issue_Records newRecord = new Issue_Records(bookId, memberId);
				        issueRecordDao.addIssueRecord(newRecord);

				        String query = "SELECT * FROM issue_records WHERE IssueId=?";
				        try (Connection conn = DriverManager.getConnection(url, user, password);
				             PreparedStatement stmt = conn.prepareStatement(query)) {

				            stmt.setInt(1, newRecord.getIssueId());
				            ResultSet rs = stmt.executeQuery();
				            assertTrue("Inserted issue record should exist in the DB", rs.next());
				            assertEquals(bookId, rs.getInt("BookId"));
				            assertEquals(memberId, rs.getInt("MemberId"));
				            assertEquals('I', rs.getString("Status").charAt(0));
				            assertEquals(Date.valueOf(LocalDate.now()), rs.getDate("IssueDate"));
				            assertEquals(null, rs.getDate("ReturnDate"));

				        }
				    }
				}
			}
	         catch (SQLException e) {
	            fail("SQLException during test: " + e.getMessage());
	        }
	    }
	    
	    @Test
	    public void testGetAllIssuedRecords() {
	    	int issueId;
	        int bookId = 150;
	        int memberId = 100;
	        char status = 'I'; 
	        Date issueDate = Date.valueOf("2025-07-25");
	        Date returnDate = Date.valueOf("2025-07-26");
	        if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
	        	String insertQuery = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
		        try (Connection conn = DriverManager.getConnection(url, user, password);
		             PreparedStatement insertStmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

		            insertStmt.setInt(1, bookId);
		            insertStmt.setInt(2, memberId);
		            insertStmt.setString(3, String.valueOf(status));
		            insertStmt.setDate(4, issueDate);
		            insertStmt.setDate(5, returnDate);
		            int affectedRows = insertStmt.executeUpdate();

		            assertTrue("Insert should affect at least 1 row", affectedRows > 0);
		            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
		            int insertedId = 0;
		            if (generatedKeys.next()) {
		            	insertedId = generatedKeys.getInt(1);
		            }
		            issueId=insertedId;
		            assertTrue("Inserted IssueId should be valid", issueId > 0);
		            List<Issue_Records> allRecords = issueRecordDao.getAllIssuedRecords();
			        boolean found = allRecords.stream().anyMatch(record ->record.getIssueId()==issueId);

			        assertTrue("Inserted record should be found in getAllIssuedRecords()", found);
		        } 
		        catch (SQLException e) {
		            fail("SQLException during test setup: " + e.getMessage());
		        }
	        } 
	    }
	    
	    @Test
	    public void testUpdateIssueRecord() {
	        int bookId = 601;     
	        int memberId = 100; 
			try{
				if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
					Book book=new DataBookDao().getBookById(bookId);
					char status=book.getBook_Status();
					char available=book.getBook_Availability();
					if((!String.valueOf(status).equals("I")) && (!String.valueOf(available).equals("I"))) {
				    	Issue_Records newRecord = new Issue_Records(bookId, memberId);
				    	issueRecordDao.addIssueRecord(newRecord);
				    	int issueId=newRecord.getIssueId();
				    	issueRecordDao.updateIssueRecord(issueId, true);
				    	String selectQuery = "SELECT Status, ReturnDate FROM issue_records WHERE IssueId = ?";
				    	try (Connection conn = DriverManager.getConnection(url, user, password);
				    			PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
				    		selectStmt.setInt(1, issueId);
				    		try (ResultSet rs = selectStmt.executeQuery()) {
				    			assertTrue("Updated record should exist", rs.next());
				    			String updatedStatus = rs.getString("Status");
				    			Date updatedReturnDate = rs.getDate("ReturnDate");
				    			assertEquals("Status should be 'R' after return", "R", updatedStatus);
				    			assertEquals("ReturnDate should be today's date", Date.valueOf(LocalDate.now()), updatedReturnDate);
				    		}

				    	} 
				    }
				}
			}
			catch (SQLException e) {
	    		fail("Verification failed: " + e.getMessage());
	    	}
	    }
	    
	    @Test
	    public void testAddIssue_Records_Log() {
	        int bookId = 150;
	        int memberId = 100;
	        if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
	        	Issue_Records newRecord = new Issue_Records(bookId, memberId);
		        issueRecordDao.addIssue_Records_Log(newRecord);
		        int issueId = newRecord.getIssueId();
		        String query = "SELECT * FROM issue_records_log WHERE IssueId = ?";
		        try (Connection conn = DriverManager.getConnection(url, user, password);
		             PreparedStatement stmt = conn.prepareStatement(query)) {
		            stmt.setInt(1, issueId);
		            try (ResultSet rs = stmt.executeQuery()) {
		                assertTrue("Log record should exist", rs.next());
		                assertEquals("Status should match", "I", rs.getString("Status"));
		                assertEquals("IssueDate should match", Date.valueOf(LocalDate.now()), rs.getDate("IssueDate"));
		            }

		        } catch (SQLException e) {
		            fail("SQLException during verification: " + e.getMessage());
		        }
	        } 
	    }
	    
	    @Test
	    public void testGetOverdueRecords() {
	        int bookId = 602;
	        int memberId = 30;
	        if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
	        	Issue_Records newRecord = new Issue_Records(bookId, memberId);
	 	        int isAffected=issueRecordDao.addIssueRecord(newRecord);
	 	        if(isAffected!=0) {
	 	        	int issueId = newRecord.getIssueId();
		 	        String query="UPDATE issue_records SET IssueDate = '2020-07-25' WHERE IssueId=?;";
		 	        try (Connection conn = DriverManager.getConnection(url, user, password);
		 		             PreparedStatement stmt = conn.prepareStatement(query)) {
		 	        	stmt.setInt(1, issueId);
		 		        int rowsAffected=stmt.executeUpdate();
		 		        if (rowsAffected == 0) {
		 		        	throw new SQLException("SQL ERROR: Failed to insert issueRecord");
		 		        } 
		 		        List<OverDueList> overdueRecords = issueRecordDao.getOverdueRecords();
		 		        boolean found = overdueRecords.stream().anyMatch(record ->record.getIssueId() == issueId );
		 		        assertTrue("Overdue record should be returned by getOverdueRecords()", found);
		 	        }
		 	        catch (SQLException e) {
		 	        	e.printStackTrace();
		 	            fail("SQLException during verification: " + e.getMessage());
		 	        }
	 	        }
	 	        else {
					assertEquals(0, isAffected);
				}
	        }
	    }

	    @Test
	    public void testGetRecordById() {
	        int bookId = 603; 
	        int memberId = 35;
	        if((new DataBookDao().getBookById(bookId)!=null) && (new MemberDaoImpl().getMemberById(memberId)!=null)) {
	        	Issue_Records newRecord = new Issue_Records(bookId, memberId);
		        issueRecordDao.addIssueRecord(newRecord);
		        int issueId = newRecord.getIssueId();
		        Issue_Records record = issueRecordDao.getRecordById(issueId);
		        assertNotNull("Record should not be null", record);
		        assertEquals(issueId, record.getIssueId());
	        }
	    }
}