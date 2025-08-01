package com.library_management.utilities;

import javafx.beans.property.SimpleStringProperty;

public class ListOfActiveBooksTable {
    private final SimpleStringProperty memberId;
    private final SimpleStringProperty memberName;
    private final SimpleStringProperty bookTitle;
    private final SimpleStringProperty issueRecordStatus;
    private final SimpleStringProperty bookStatus;

    public ListOfActiveBooksTable(String memberId, String memberName, String bookTitle,
                                  String issueRecordStatus, String bookStatus) {
        this.memberId = new SimpleStringProperty(memberId);
        this.memberName = new SimpleStringProperty(memberName);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.issueRecordStatus = new SimpleStringProperty(issueRecordStatus);
        this.bookStatus = new SimpleStringProperty(bookStatus);
    }

    public String getMemberId() {
        return memberId.get();
    }

    public String getMemberName() {
        return memberName.get();
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public String getIssueRecordStatus() {
        return issueRecordStatus.get();
    }

    public String getBookStatus() {
        return bookStatus.get();
    }
}
