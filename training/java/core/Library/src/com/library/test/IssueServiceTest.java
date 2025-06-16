package com.library.test;

import com.library.service.IssueService;
import com.library.dao.Issue;
import com.library.enums.Status;
import com.library.enums.StatusRecords;
import com.library.exception.UserDefinedException;

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
        int bookId = 2;
        int memberId = 2;
        issueService.issueBook(bookId, memberId);
        List<Issue> records = issueService.getAllIssueRecords();
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
        } catch (UserDefinedException e) {
            assertEquals("Book is already issued.", e.getMessage());
        }
    }
}
