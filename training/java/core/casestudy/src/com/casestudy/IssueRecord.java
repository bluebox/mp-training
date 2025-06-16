package com.casestudy;

import java.time.LocalDate;

public class IssueRecord {

	private int issueId;
	private int bookId;
	private int memberId;
	private RecordStatus status; // 'I' or 'R'
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord() {

	}

	public IssueRecord(int bookId, int memberId) {

		this.bookId = bookId;
		this.memberId = memberId;
		this.issueDate = LocalDate.now();

	}

	// Getters and Setters
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

	@Override
	public String toString() {
		return "IssueRecord [issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status="
				+ status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + "]";
	}

	public IssueRecord(int issueId, int bookId, int memberId, RecordStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
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
}
