package com.LibraryManagement.models;

import java.time.LocalDate;

public class IssueRecords {

	private int issueId;
	private int bookId;
	private int memberId;
	private char status;
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecords() {
	}

	public IssueRecords(int issueId, int bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
	
	
	public IssueRecords(int issueId, int bookId, int memberId, char status, LocalDate issueDate) {
		this(0, bookId, memberId, status, issueDate, null);
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}
	
	public String getStatusAsString() {
	    return String.valueOf(status);
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

}
