package com.lms.model;

import java.sql.Date;

public class OverDueList {
	private int issueId;
	private int bookId;
	private String title;
	private String memberName;
	private Date overDueDate;
	
	public OverDueList(int issueId, int bookId, String title, String memberName, Date overDueDate) {
		this.issueId = issueId;
		this.bookId = bookId;
		this.title = title;
		this.memberName = memberName;
		this.overDueDate = overDueDate;
	}
	
	public int getIssueId() {
		return issueId;
	}
	public int getBookId() {
		return bookId;
	}
	public String getTitle() {
		return title;
	}
	public String getMemberName() {
		return memberName;
	}
	public Date getOverDueDate() {
		return overDueDate;
	}
	
}
