package com.LibraryManagement.utilites.pojos;

import java.sql.Date;


public class IssueRecord {
    private Integer issueId;
    private Integer bookId;
    private Integer memberId;
    private Character status;
    private Date issueDate;
    private Date returnDate;
    
    public IssueRecord(Integer issueId, Integer bookId, Integer memberId, Character status, Date issueDate, Date returnDate) {
    	this.issueId=issueId;
    	this.bookId=bookId;
    	this.memberId=memberId;
    	this.status=status;
    	this.issueDate=issueDate;
    	this.returnDate=returnDate;
    }
    public IssueRecord( Integer bookId, Integer memberId, Character status, Date issueDate, Date returnDate) {
    	super();
    }
    
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
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
		// TODO Auto-generated method stub
		return issueId+" "+bookId+" "+memberId+" "+status+" "+issueDate+" "+returnDate;
	}
    
}