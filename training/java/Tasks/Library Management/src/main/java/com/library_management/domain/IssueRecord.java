package com.library_management.domain;

import java.time.LocalDate;

public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private IssueRecordStatus status;
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord(int issueId, int bookId, int memberId, IssueRecordStatus status, LocalDate issueDate) {
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
	}

	public IssueRecord(int bookId, int memberId, IssueRecordStatus status, LocalDate issueDate) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
	}
	
	public IssueRecord(int bookId, int memberId, IssueRecordStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		this(bookId, memberId, status, issueDate);
		this.returnDate = returnDate;
	}
	public IssueRecord(int issueId, int bookId, int memberId, IssueRecordStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		this(issueId, bookId, memberId, status, issueDate);
		this.returnDate = returnDate;
	}

	public IssueRecord() {
	}

	public IssueRecord(int bookId, int memberId, LocalDate issueDate) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.issueDate = issueDate;
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

	public IssueRecordStatus getStatus() {
		return status;
	}

	public void setStatus(IssueRecordStatus status) {
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
		return "issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status=" + status
				+ ", issueDate=" + issueDate + ", returnDate=" + returnDate;
	}

	
}
