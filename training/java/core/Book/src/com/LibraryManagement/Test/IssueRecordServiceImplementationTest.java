//package com.LibraryManagement.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.LibraryManagement.models.IssueRecords;
//import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;
//
//public class IssueRecordServiceImplementationTest {
//
//	private IssueRecordServiceImplementation service;
//
//	@Before
//	public void setUp() {
//		service = new IssueRecordServiceImplementation();
//	}
//
//	@Test(expected = Exception.class)
//	public void testIssueBook_NullRecord_ThrowsException() throws Exception {
//		service.issueBook(null);
//	}
//
//	@Test(expected = Exception.class)
//	public void testIssueBook_InvalidIds_ThrowsException() throws Exception {
//		IssueRecords record = new IssueRecords(0, -5, "issued", LocalDate.now());
//		service.issueBook(record);
//	}
//
//	@Test(expected = Exception.class)
//	public void testReturnBook_InvalidIssueId_ThrowsException() throws Exception {
//		service.returnBook(-1);
//	}
//
//	@Test(expected = Exception.class)
//	public void testGetActiveIssueByBookId_InvalidId_ThrowsException() throws Exception {
//		service.getActiveIssueByBookId(0);
//	}
//
//	@Test
//	public void testGetAllIssues_ReturnsList() throws Exception {
//		List<IssueRecords> issues = service.getAllIssues();
//		assertNotNull(issues);
//		assertTrue(issues.size() >= 0);
//	}
//
//	@Test
//	public void testGetValidMemberIds_ReturnsList() throws Exception {
//		List<Integer> validMemberIds = service.getValidMemberIds();
//		assertNotNull(validMemberIds);
//	}
//
//}