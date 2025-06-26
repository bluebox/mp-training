package com.library.model;

import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private String status;      // Issued or Returned 
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	
	public IssueRecord(int issueId, int bookId, int memberId, String status, LocalDate issueDate, LocalDate returnDate) {
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
    
    // Constructors, getters, setters, toString
}
