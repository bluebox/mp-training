package com.casestudyjunit;

import com.casestudy.IssueRecord;
import com.casestudy.RecordStatus;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class IssueRecordTest {

    @Test
    public void testNoArgConstructor() {
        IssueRecord record = new IssueRecord();
        assertNotNull(record);
    }

    @Test
    public void testPartialConstructor() {
        int bookId = 101;
        int memberId = 202;
        IssueRecord record = new IssueRecord(bookId, memberId);

        assertEquals(bookId, record.getBookId());
        assertEquals(memberId, record.getMemberId());
        assertEquals(LocalDate.now(), record.getIssueDate()); // assumes test runs same day
    }

    @Test
    public void testFullConstructorAndGetters() {
        int issueId = 1;
        int bookId = 101;
        int memberId = 202;
        RecordStatus status = RecordStatus.ISSUED;
        LocalDate issueDate = LocalDate.of(2024, 1, 1);
        LocalDate returnDate = LocalDate.of(2024, 1, 10);

        IssueRecord record = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);

        assertEquals(issueId, record.getIssueId());
        assertEquals(bookId, record.getBookId());
        assertEquals(memberId, record.getMemberId());
        assertEquals(status, record.getStatus());
        assertEquals(issueDate, record.getIssueDate());
        assertEquals(returnDate, record.getReturnDate());
    }

    @Test
    public void testSetters() {
        IssueRecord record = new IssueRecord();

        record.setIssueId(10);
        record.setBookId(100);
        record.setMemberId(200);
        record.setStatus(RecordStatus.RETURNED);
        record.setIssueDate(LocalDate.of(2024, 5, 1));
        record.setReturnDate(LocalDate.of(2024, 5, 15));

        assertEquals(10, record.getIssueId());
        assertEquals(100, record.getBookId());
        assertEquals(200, record.getMemberId());
        assertEquals(RecordStatus.RETURNED, record.getStatus());
        assertEquals(LocalDate.of(2024, 5, 1), record.getIssueDate());
        assertEquals(LocalDate.of(2024, 5, 15), record.getReturnDate());
    }

    @Test
    public void testToStringNotNull() {
        IssueRecord record = new IssueRecord(1, 101, 202, RecordStatus.ISSUED, LocalDate.now(), null);
        assertNotNull(record.toString());
    }
}

