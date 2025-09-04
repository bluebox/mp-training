package com.lms.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.lms.exceptions.IdNotExistException;
import com.lms.model.Issue_Records;
import com.lms.service.IssueLogService;
import com.lms.service.impl.IssueLogServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class IssueLogServiceTest {
    
    private IssueLogService issueLogService;
    
    @Before
    public void setUp() {
        issueLogService = new IssueLogServiceImpl();
    }
    
    @Test
    public void testAddIssueRecord_ValidRecord() {
        
        Issue_Records newRecord = new Issue_Records();
        newRecord.setBookId(10); 
        newRecord.setMemberId(131); 
        newRecord.setStatus('I');
        newRecord.setIssueDate(Date.valueOf(LocalDate.now()));
        newRecord.setReturnDate(null);
        
        try {
			issueLogService.addIssueRecord(newRecord);
		} catch (IdNotExistException e) {
			e.printStackTrace();
		}
        
        List<Issue_Records> records = issueLogService.getAllIssuedRecords();
        System.out.println(records == null);
        for(Issue_Records record : records) {
        	System.out.println(record.getIssueId());
        }
        assertTrue(records.stream()
                .anyMatch(r -> r.getBookId() == newRecord.getBookId() 
                        && r.getMemberId() == newRecord.getMemberId()));
    }
    

    @Test
    public void testReturnIssuedBook_ValidReturn() {
        
        int issueId = 1; 
        
        issueLogService.returnIssuedBook(issueId, true);
        
        List<Issue_Records> records = issueLogService.getAllIssuedRecords();
        assertTrue(records.stream()
                .filter(r -> r.getIssueId() == issueId)
                .anyMatch(r -> r.getStatus() == 'R'));
    }
    

    
    @Test
    public void testGetAllIssuedRecords() {
        List<Issue_Records> records = issueLogService.getAllIssuedRecords();
        assertNotNull(records);
    }
}
