// Updated IssueBook model
package com.lms.model;

import java.time.LocalDate;

public class IssueBook {
    private int issueId;
    private String bookId;
    private int memberId;
    private char status; // 'I' = Issued, 'R' = Returned
    private LocalDate issueDate;
    private LocalDate returnDate;
    public IssueBook() {}
    public IssueBook(String bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
	// Getters and Setters
    public int getIssueId() { return issueId; }
    public IssueBook(int issueId, String bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
    public IssueBook(String bookId, int memberId, LocalDate issueDate, LocalDate returnDate) {
		super();
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
}

// BookDaoInterface

/*
CREATE TABLE issue_records (
	    IssueId INT PRIMARY KEY AUTO_INCREMENT,
	    BookId INT NOT NULL,
	    MemberId INT NOT NULL,
	    Status CHAR(1) NOT NULL CHECK (Status IN ('I', 'R')),
	    IssueDate DATE NOT NULL,
	    ReturnDate DATE,
	    FOREIGN KEY (BookId) REFERENCES books(BookId),
	    FOREIGN KEY (MemberId) REFERENCES members(MemberId)
	);
*/