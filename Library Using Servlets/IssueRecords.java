package com.library.model;

import java.time.LocalDate;

public class IssueRecords {

    private int issueId;
    private int bookId;
    private int memberId;
    private String availability;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecords() {}

    public IssueRecords(int issueId, int bookId, int memberId, String availability, LocalDate issueDate, LocalDate returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.availability = availability;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public IssueRecords(int bookId, int memberId, String availability, LocalDate issueDate) {
        this(0, bookId, memberId, availability, issueDate, null);
    }
}
