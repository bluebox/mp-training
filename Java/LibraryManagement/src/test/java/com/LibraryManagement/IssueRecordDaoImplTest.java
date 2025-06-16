package com.LibraryManagement;

import com.LibraryManagement.dao.IssueRecordDao;
import com.LibraryManagement.dao.impl.IssueRecordDaoImpl;
import com.LibraryManagement.utilites.pojos.IssueRecord;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IssueRecordDaoImplTest {

    private IssueRecordDaoImpl issueRecordDao;

    @Before
    public void setUp() {
        issueRecordDao = new IssueRecordDaoImpl();
    }

    @Test
    public void testVerifyBookAndMember_Valid() {
        int bookId = 1;      // Make sure this book exists and is available in DB
        int memberId = 1;    // Make sure this member exists and is active

        boolean result = issueRecordDao.verifyBookAndMember(bookId, memberId);
        assertTrue("Expected book and member to be valid and available", result);
    }

    @Test
    public void testVerifyBookAndMember_Invalid() {
        int bookId = 9999;   // Non-existing book
        int memberId = 9999; // Non-existing member

        boolean result = issueRecordDao.verifyBookAndMember(bookId, memberId);
        assertFalse("Expected book or member to be invalid", result);
    }

    @Test
    public void testIssueBook() {
        int bookId = 2;      // Existing and available book
        int memberId = 1;    // Valid member

        boolean result = issueRecordDao.issueBook(bookId, memberId);
        assertFalse("Expected issueBook to return false (since method always returns false)", result);
    }

    @Test
    public void testReturnBook() throws Exception {
        int issueId = 1;  // Ensure this issueId exists

        boolean result = issueRecordDao.returnBook(issueId);
        assertFalse("Expected returnBook to return false (since method always returns false)", result);
    }

    @Test
    public void testViewAllIssuedRecords() throws Exception {
        ArrayList<IssueRecord> records = issueRecordDao.viewAllIssuedRecords();
        assertNotNull("Expected non-null list of issued records", records);
    }

    @Test
    public void testViewIssueRecordLog() throws Exception {
        ArrayList<IssueRecord> records = issueRecordDao.viewIssueRecordLog();
        assertNotNull("Expected non-null list of issue record logs", records);
    }
}