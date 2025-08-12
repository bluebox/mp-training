package com.libraryManagementSystem.domain;

import java.time.LocalDate;

public class CustomOverDueBooks {

	private int bookId;
	private String bookName;
	private int memberId;
	private String memberName;
	private LocalDate issueDate;

	public CustomOverDueBooks(int bookId, String bookName, int memberId, String memberName, LocalDate issueDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.memberId = memberId;
		this.memberName = memberName;
		this.issueDate = issueDate;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

}
