package com.Models;

import java.time.LocalDate;

public class IssueLogs {
	private int issueId;
    private int bookId;
    private int memberId;
    private char status; // I (Issued), R (Returned)
    private LocalDate issueDate;
    private LocalDate returnDate;

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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "IssueRecord [ID=" + issueId + ", BookId=" + bookId + ", MemberId=" + memberId + ", Status=" + status + ", IssueDate=" + issueDate + ", ReturnDate=" + returnDate + "]";
    }
}
