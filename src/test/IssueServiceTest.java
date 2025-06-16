package test;

import service.IssueService;
import exception.BookAlreadyIssuedException;
import exception.DatabaseException;
import model.IssueRecord;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IssueServiceTest {

    private IssueService issueService;

    @Before
    public void setup() {
        issueService = new IssueService();
    }

    @Test
    public void testSuccessfulBookIssue() throws Exception {
        int bookId = 2; // Ensure this book exists and is available in the DB
        int memberId = 2; // Ensure this member exists

        // First, make sure the book is available before issuing
        issueService.issueBook(bookId, memberId);

        List<IssueRecord> records = issueService.getAllIssueRecords();
        boolean issued = records.stream()
                .anyMatch(r -> r.getBookId() == bookId && r.getMemberId() == memberId && r.getStatus() == 'I');

        assertTrue("Book should be issued", issued);
    }

    @Test
    public void testIssueAlreadyIssuedBook() throws Exception {
        int bookId = 3; // Make sure this book exists and is 'A' initially
        int memberId = 2;

        // First issue - should pass
        issueService.issueBook(bookId, memberId);

        // Second issue - should throw BookAlreadyIssuedException
        try {
            issueService.issueBook(bookId, memberId);
            fail("Expected BookAlreadyIssuedException to be thrown");
        } catch (BookAlreadyIssuedException e) {
            assertEquals("Book is already issued.", e.getMessage());
        }
    }
}
