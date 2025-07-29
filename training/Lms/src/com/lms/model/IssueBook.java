package com.lms.model;

import java.time.LocalDate;

public class IssueBook {
    private int issueId;
    private String bookId;
    private int memberId;
    private char status;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate; 

    public IssueBook() {}

    public IssueBook(String bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public IssueBook(int issueId, String bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public IssueBook(String bookId, int memberId, LocalDate issueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public IssueBook(int issueId, String bookId, int memberId, LocalDate issueDate, LocalDate returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public IssueBook(int issueId, String bookId, int memberId, LocalDate issueDate, LocalDate returnDate, LocalDate actualReturnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.actualReturnDate = actualReturnDate;
    }

    public int getIssueId() { return issueId; }
    public void setIssueId(int issueId) { this.issueId = issueId; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public char getStatus() { return status; }
    public void setStatus(char status) { this.status = status; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public LocalDate getActualReturnDate() { return actualReturnDate; }
    public void setActualReturnDate(LocalDate actualReturnDate) { this.actualReturnDate = actualReturnDate; }
}
