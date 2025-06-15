package com.library.domain;

import java.sql.Date;

public class IssueRecord {
    private int bookId;
    private int memberId;
    private Date issueDate;
    private Date returnDate;

    // Constructor for issuing a book (no return date yet)
    public IssueRecord(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    // Constructor for reading full record from database
    public IssueRecord(int bookId, int memberId, Date issueDate, Date returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
