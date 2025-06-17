package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.IssueRecord;
import com.service.IssueService;

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
    void testIssueBookInvalidInput() {
        IssueRecord record = null; 
        assertThrows(Exception.class, () -> issueService.issueBook(record));
    }

    @Test
    void testIssueBookValidInput() throws Exception {
        IssueRecord record = new IssueRecord(0, 1, 1, "2024-01-01", null, false);
        issueService.issueBook(record);
        assertTrue(true);
    }
} 