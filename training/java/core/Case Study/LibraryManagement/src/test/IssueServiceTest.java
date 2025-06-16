package test;

import service.IssueService;
import exception.BookAlreadyIssuedException;
import exception.DatabaseException;
import model.IssueRecord;

import org.junit.Before;
import org.junit.Test;

import enums.StatusRecords;

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
        int bookId = 2; 
        int memberId = 2; 
        issueService.issueBook(bookId, memberId);

        List<IssueRecord> records = issueService.getAllIssueRecords();
        boolean issued = records.stream()
                .anyMatch(r -> r.getBookId() == bookId && r.getMemberId() == memberId && r.getStatus() == StatusRecords.Issued);

        assertTrue("Book should be issued", issued);
    }

    @Test
    public void testIssueAlreadyIssuedBook() throws Exception {
        int bookId = 3; 
        int memberId = 2;

        issueService.issueBook(bookId, memberId);

        try {
            issueService.issueBook(bookId, memberId);
            fail("Expected BookAlreadyIssuedException to be thrown");
        } catch (BookAlreadyIssuedException e) {
            assertEquals("Book is already issued.", e.getMessage());
        }
    }
}
