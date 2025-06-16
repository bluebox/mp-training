package com.models;

import java.sql.Date;

public class IssueRecord {
    private int bookId;
    private int memberId;
    private char status;
    private Date issueDate;
    private Date returnDate;

    public IssueRecord(int bookId, int memberId, char status, Date issueDate, Date returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public char getStatus() { return status; }
    public Date getIssueDate() { return issueDate; }
    public Date getReturnDate() { return returnDate; }
}
