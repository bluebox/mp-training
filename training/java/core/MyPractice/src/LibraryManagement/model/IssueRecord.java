package com.LibraryManagement.model;

import java.time.LocalDate;

public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private char status; // 'I' or 'R'
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

	public IssueRecord(int issueId, int bookId, int memberId, char status, LocalDate issueDate, LocalDate returnDate) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public IssueRecord() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "IssueRecord{" +
				"issueId=" + issueId +
				", bookId=" + bookId +
				", memberId=" + memberId +
				", status=" + status +
				", issueDate=" + issueDate +
				", returnDate=" + (returnDate != null ? returnDate : "N/A") +
				'}';
	}

	// Constructors, getters, setters, toString
}
