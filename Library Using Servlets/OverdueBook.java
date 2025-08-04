package com.library.dto;

import java.time.LocalDate;

public class OverdueBook {
    private final String title;
    private final String memberName;
    private final LocalDate issueDate;

    public OverdueBook(String title, String memberName, LocalDate issueDate) {
        this.title = title;
        this.memberName = memberName;
        this.issueDate = issueDate;
    }

    public String getTitle() { return title; }
    public String getMemberName() { return memberName; }
    public LocalDate getIssueDate() { return issueDate; }
}
