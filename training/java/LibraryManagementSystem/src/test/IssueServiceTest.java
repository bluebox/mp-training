package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.IssueRecord;
import service.IssueService;

class IssueServiceTest {
    IssueService issueService;

    @BeforeEach
    void setUp() {
        issueService = new IssueService();
    }

    @Test
    void testNotYetImplemented() {
        fail("Not yet implemented");
    }

    @Test
    void testIssueBook_InvalidInput() {
        IssueRecord record = null;
        assertThrows(Exception.class, () -> issueService.issueBook(record));
    }

    @Test
    void testIssueBook_ValidInput() throws Exception {
        IssueRecord record = new IssueRecord(0, 1, 1, 'A',LocalDate.of(2024, 01, 01), null);
        issueService.issueBook(record);
        assertTrue(true);
    }
} 