package com.library.app.model;

import java.time.LocalDate;

import lombok.Data;
@Data
public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private char status;
	private LocalDate issueDate;
	private LocalDate returnDate;

//	public int getIssueId() {
//		return issueId;
//	}
//
//	public void setIssueId(int issueId) {
//		this.issueId = issueId;
//	}
//
//	public int getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(int bookId) {
//		this.bookId = bookId;
//	}
//
//	public int getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(int memberId) {
//		this.memberId = memberId;
//	}
//
//	public char getStatus() {
//		return status;
//	}
//
//	public void setStatus(char status) {
//		this.status = status;
//	}
//
//	public LocalDate getIssueDate() {
//		return issueDate;
//	}
//
//	public void setIssueDate(LocalDate issueDate) {
//		this.issueDate = issueDate;
//	}
//
//	public LocalDate getReturnDate() {
//		return returnDate;
//	}
//
//	public void setReturnDate(LocalDate returnDate) {
//		this.returnDate = returnDate;
//	}
//
//	public String toString() {
//		return "IssueRecord{" + "issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status="
//				+ status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
//	}
}
