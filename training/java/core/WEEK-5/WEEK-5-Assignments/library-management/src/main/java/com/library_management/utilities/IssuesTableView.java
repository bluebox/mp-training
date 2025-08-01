package com.library_management.utilities;

import com.library_management.domain.IssueRecordStatus;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IssuesTableView {
	private final SimpleIntegerProperty issueId;
    private final SimpleIntegerProperty memberId;
    private final SimpleIntegerProperty bookId;
    private final SimpleStringProperty status;
    private final SimpleStringProperty issueDate;
    private final SimpleStringProperty returnDate;

    public IssuesTableView(int issueId,  int bookId, int memberId, IssueRecordStatus status,String issueDate, String returnDate) {
        this.issueId = new SimpleIntegerProperty(issueId);
        this.memberId = new SimpleIntegerProperty(memberId);
        this.bookId = new SimpleIntegerProperty(bookId);
        this.status=new SimpleStringProperty(String.valueOf(status));
        this.issueDate = new SimpleStringProperty(issueDate);
        this.returnDate = new SimpleStringProperty(returnDate);
    }

    public int getIssueId() {
        return issueId.get();
    }

    public int getMemberId() {
        return memberId.get();
    }

    public int getBookId() {
        return bookId.get();
    }

    public String getStatus() {
		return status.get();
	}
    
    public String getIssueDate() {
        return issueDate.get();
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleIntegerProperty issueIdProperty() {
        return issueId;
    }

    public SimpleIntegerProperty memberIdProperty() {
        return memberId;
    }

    public SimpleIntegerProperty bookIdProperty() {
        return bookId;
    }

    public SimpleStringProperty statusProperty() {
		return status;
	}
    
    public SimpleStringProperty issueDateProperty() {
        return issueDate;
    }

    public SimpleStringProperty returnDateProperty() {
        return returnDate;
    }
}
