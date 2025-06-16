package com.medplus.model;

import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private char status;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecord(int bookId, int memberId, char status, LocalDate issueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.issueId = 0;
        this.returnDate = null;
    }

    public IssueRecord(int issueId, int bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public char getStatus() {
        return status;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "IssueRecord{" +
               "issueId=" + issueId +
               ", bookId=" + bookId +
               ", memberId=" + memberId +
               ", status=" + status +
               ", issueDate=" + issueDate +
               ", returnDate=" + returnDate +
               '}';
    }
}