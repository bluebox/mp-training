package com.library.test;

import com.library.model.IssueRecord;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.interfaces.IssueRecordService;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class IssueRecordServiceTest {
	
    private final IssueRecordService service = new IssueRecordServiceImplementation();
    @Test
    public void testIssueBookValid() throws Exception {
        IssueRecord record = new IssueRecord();
        record.setBookId(2);      
        record.setMemberId(3);     

        record.setIssueDate(LocalDate.now());
        boolean result = service.issueBook(record);
        assertTrue("Book should be issued successfully", result);
    }

    @Test
    public void testIssueBookInvalidData() {
        try {
            IssueRecord record = new IssueRecord();
            record.setBookId(-1);
            record.setMemberId(0);
            service.issueBook(record);
            fail("Exception expected for invalid data");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid book or member ID"));
        }
    }

    @Test
    public void testReturnBookValid() throws Exception {
        // First issue a book
        IssueRecord record = new IssueRecord();
        record.setBookId(2);    
        record.setMemberId(1);    

        record.setIssueDate(LocalDate.now());  // Correct type
        service.issueBook(record);
        // Fetch latest issue
        IssueRecord active = service.getActiveIssueByBookId(2);
        assertNotNull(active);

        boolean result = service.returnBook(active.getIssueId());
        assertTrue("Book should be returned successfully", result);
    }

    @Test
    public void testReturnBookInvalidId() {
        try {
            service.returnBook(-100);
            fail("Exception expected for invalid issue ID");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid issue ID"));
        }
    }

    @Test
    public void testGetActiveIssueByBookId() throws Exception {
        IssueRecord record = service.getActiveIssueByBookId(1);
        if (record != null) {
            assertEquals('I', record.getStatus());
        }
    }

    @Test
    public void testGetAllIssues() throws Exception {
        List<IssueRecord> list = service.getAllIssues();
        assertNotNull(list);
    }
}
