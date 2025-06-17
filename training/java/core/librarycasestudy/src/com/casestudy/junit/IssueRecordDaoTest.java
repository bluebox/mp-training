package com.casestudy.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.casestudy.dao.IssueRecordDao;
import com.casestudy.domain.IssueRecord;
import com.casestudy.domain.RecordStatus;

public class IssueRecordDaoTest {

    private static IssueRecordDao dao;
    private static IssueRecord testRecord;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        dao = new IssueRecordDao();

        // Make sure bookId and memberId exist in your DB for the test
        testRecord = new IssueRecord(101, 202); // bookId=101, memberId=202
        testRecord.setStatus(RecordStatus.ISSUED);
        testRecord.setIssueDate(LocalDate.now());

        // Clean up if record already exists
        if (dao.alreadyIssued(testRecord)) {
            dao.returnBook(testRecord);
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Clean-up: Return the test book
        dao.returnBook(testRecord);
    }

    @Test
    public void testIssueBook() throws SQLException {
        dao.issueBook(testRecord);
        assertTrue(dao.alreadyIssued(testRecord));
    }

    @Test
    public void testReturnBook() throws SQLException {
        dao.issueBook(testRecord);
        dao.returnBook(testRecord);
        assertFalse(dao.alreadyIssued(testRecord));
    }

    @Test
    public void testGetIssuedBooks() {
        List<IssueRecord> list = dao.getIssuedBooks();
        assertNotNull(list);
    }

    @Test
    public void testGetAllIssuedRecords() {
        List<IssueRecord> list = dao.getAllIssuedRecords();
        assertNotNull(list);
        assertTrue(list.size() >= 0);
    }

    @Test
    public void testGetActiveIssuedBooks() throws SQLException {
        dao.issueBook(testRecord);
        List<IssueRecord> list = dao.getActiveIssuedBooks();
        assertNotNull(list);
        boolean found = list.stream().anyMatch(r ->
            r.getBookId() == testRecord.getBookId() &&
            r.getMemberId() == testRecord.getMemberId()
        );
        assertTrue(found);
    }

    @Test
    public void testAlreadyIssued() throws SQLException {
        dao.issueBook(testRecord);
        boolean result = dao.alreadyIssued(testRecord);
        assertTrue(result);
    }
}

