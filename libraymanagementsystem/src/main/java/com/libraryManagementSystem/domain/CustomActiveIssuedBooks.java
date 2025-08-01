package com.libraryManagementSystem.domain;

import java.time.LocalDate;

public class CustomActiveIssuedBooks {
	private int memberId;
	private String memberName;
	private int bookId;
	private String bookTitle;
	private LocalDate issueDate;

	public CustomActiveIssuedBooks(int memberId, String memberName, int bookId, String bookTitle, LocalDate issueDate) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.issueDate = issueDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	@Override
	public String toString() {
		return "CustomActiveIssuedBooks [memberId=" + memberId + ", memberName=" + memberName + ", bookId=" + bookId
				+ ", bookTitle=" + bookTitle + ", issueDate=" + issueDate + "]";
	}

}
