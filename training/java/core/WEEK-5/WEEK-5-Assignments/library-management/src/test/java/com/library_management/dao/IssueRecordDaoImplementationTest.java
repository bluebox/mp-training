package com.library_management.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.library_management.domain.IssueRecord;
import com.library_management.utilities.DBConnection;
import com.library_management.dao.implementation.IssueRecordDaoImplementation;

@RunWith(JUnit4.class)
public class IssueRecordDaoImplementationTest {
	
	private IssueRecordDaoImplementation dao;
	
	@Before
    public void setUp() {
		 DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms");
        dao = new IssueRecordDaoImplementation();
    }
	
	@Test
	public void testIssueBook() {
//		System.out.println(dao);
		//fail("Not yet implemented");
		IssueRecord issue = new IssueRecord(2, 1, LocalDate.now());
		String print_message = dao.issueBook(issue);
		assertEquals("Book issued successfully",print_message);
	}
	
	@Test
	public void testIssueBookFailCase() {
		//fail("Not yet implemented");
		IssueRecord issue = new IssueRecord(2, 1, LocalDate.now());
		String print_message = dao.issueBook(issue);
		assertEquals("Book is not available for issue",print_message);
	}

	@Test
	public void testReturnBook() {
		//fail("Not yet implemented");
		String print_message = dao.returnBook(1, 2);
		assertEquals("Book returned successfully",print_message);
	}

	@Test
	public void testReturnBookFailCase() {
		//fail("Not yet implemented");
		String print_message = dao.returnBook(1, 2);
		assertEquals("Issue record not found or already returned",print_message);
	}
	
	@Test
	public void testGetAllIssues() {
		//fail("Not yet implemented");
		List<IssueRecord> all = dao.getAllIssues();
		assertNotNull(all);
	}

}
