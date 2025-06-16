package com.library.dao;

import java.sql.Date;
import com.library.enums.StatusRecords;

public class Issue {
	private int issueId;
	private int bookId;
	private int memberId;
	private StatusRecords status;
	private Date issueDate;
	private Date returnDate;
	public Issue(int issueId, int bookId, int memberId, StatusRecords status, Date issueDate, Date returnDate) {
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
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
	public StatusRecords getStatus() {
		return status;
	}
	public void setStatus(StatusRecords status) {
		this.status = status;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status=" + status
				+ ", issueDate=" + issueDate + ", returnDate=" + returnDate + "]";
	}
}
