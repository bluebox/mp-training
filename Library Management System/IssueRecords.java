package com.LibraryManagement.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class IssueRecords {

    private final IntegerProperty issueId = new SimpleIntegerProperty();
    private final IntegerProperty bookId = new SimpleIntegerProperty();
    private final IntegerProperty memberId = new SimpleIntegerProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> issueDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>();

    public IssueRecords(int issueId, int bookId, int memberId, String status, LocalDate issueDate, LocalDate returnDate) {
        this.issueId.set(issueId);
        this.bookId.set(bookId);
        this.memberId.set(memberId);
        this.status.set(status);
        this.issueDate.set(issueDate);
        this.returnDate.set(returnDate);
    }

    public IssueRecords(int bookId, int memberId, String status, LocalDate issueDate) {
        this(0, bookId, memberId, status, issueDate, null);
    }

    public IssueRecords() {}

    public int getIssueId() { return issueId.get(); }
    public void setIssueId(int issueId) { this.issueId.set(issueId); }
    public IntegerProperty issueIdProperty() { return issueId; }

    public int getBookId() { return bookId.get(); }
    public void setBookId(int bookId) { this.bookId.set(bookId); }
    public IntegerProperty bookIdProperty() { return bookId; }

    public int getMemberId() { return memberId.get(); }
    public void setMemberId(int memberId) { this.memberId.set(memberId); }
    public IntegerProperty memberIdProperty() { return memberId; }

    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }

    public LocalDate getIssueDate() { return issueDate.get(); }
    public void setIssueDate(LocalDate issueDate) { this.issueDate.set(issueDate); }
    public ObjectProperty<LocalDate> issueDateProperty() { return issueDate; }

    public LocalDate getReturnDate() { return returnDate.get(); }
    public void setReturnDate(LocalDate returnDate) { this.returnDate.set(returnDate); }
    public ObjectProperty<LocalDate> returnDateProperty() { return returnDate; }
}
