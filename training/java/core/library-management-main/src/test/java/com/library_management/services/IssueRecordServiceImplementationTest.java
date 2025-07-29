package com.library_management.services;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.library_management.domain.IssueRecord;
import com.library_management.services.implementation.IssueRecordServiceImplementation;
import com.library_management.utilities.DBConnection;

public class IssueRecordServiceImplementationTest {

	private IssueRecordServiceImplementation service;

	@Before
	public void setUp() {
		DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms");
		service = new IssueRecordServiceImplementation();
	}

	@Test
	public void testGetAllIssues() {
		// fail("Not yet implemented");
		List<IssueRecord> all = service.getAllIssues();
		assertNotNull(all);
	}

	@Test
	public void testGetOverdueBooks() {
		// fail("Not yet implemented");
		List<IssueRecord> overdue = service.getOverdueBooks();
		assertNotNull(overdue);
	}

	@Test
	public void testGetActiveIssuedBooks() {
		// fail("Not yet implemented");
		List<List<String>> activeIssues = service.getActiveIssuedBooks();
		assertNotNull(activeIssues);
	}

}
