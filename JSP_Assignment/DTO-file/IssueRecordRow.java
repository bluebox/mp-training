package com.library.dto;

import java.time.LocalDate;

public class IssueRecordRow {
    private int bookId;
    private String title;
    private int memberId;
    private LocalDate issueDate;

    public IssueRecordRow(int bookId, String title, int memberId, LocalDate issueDate) {
        this.bookId = bookId;
        this.title = title;
        this.memberId = memberId;
        this.issueDate = issueDate;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public int getMemberId() { return memberId; }
    public LocalDate getIssueDate() { return issueDate; }
}