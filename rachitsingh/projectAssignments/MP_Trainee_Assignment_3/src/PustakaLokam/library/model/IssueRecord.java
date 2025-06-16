package PustakaLokam.library.model;

import java.time.LocalDate;

public class IssueRecord {
	private int issueID;
	private int bookID;
	private int memberID;
	private char status;
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord() {
	}

	public IssueRecord(int bookID, int memberID) {
		this.bookID = bookID;
		this.memberID = memberID;
		this.status = 'I';
		this.issueDate = LocalDate.now();
	}

	public IssueRecord(int issueID, int bookID, int memberID, char status, LocalDate issueDate, LocalDate returnDate) {
		this.issueID = issueID;
		this.bookID = bookID;
		this.memberID = memberID;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	// ─── Getters and Setters ─────────────────────────────────────────

	public int getissueID() {
		return issueID;
	}

	public void setissueID(int issueID) {
		this.issueID = issueID;
	}

	public int getbookID() {
		return bookID;
	}

	public void setbookID(int bookID) {
		this.bookID = bookID;
	}

	public int getmemberID() {
		return memberID;
	}

	public void setmemberID(int memberID) {
		this.memberID = memberID;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
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

	public String toString() {
		return "IssueRecord{" + "issueID=" + issueID + ", bookID=" + bookID + ", memberID=" + memberID + ", status="
				+ status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
	}
}
