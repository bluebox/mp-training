package com.lms.model;

import java.sql.Date;

public class Issue_Records {
	private int issueId;
	private int bookId;
	private int memberId;
	private char status;
	private Date issueDate;
	private Date returnDate;
	
	public Issue_Records() {
		this.bookId=0;
		this.memberId=0;
		this.status = RecordStatus.Issued.getCode();
		this.issueDate = null;
		this.returnDate = null;

	}
	
	public Issue_Records(int bookId, int memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = RecordStatus.Issued.getCode();
		this.issueDate = new Date(new java.util.Date().getTime());
		this.returnDate = null;
	}
	
	public Issue_Records(int issueId, int bookId, int memberId, char status, Date issueDate, Date returnDate) {
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
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
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
	
}
