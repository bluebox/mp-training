package PustakaLokam.library.model;

import PustakaLokam.library.enums.IssueStatus;
import java.time.LocalDate;

public class IssueRecord {
	private Integer issueID;
	private Integer bookID;
	private Integer memberID;
	private IssueStatus status;
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord() {
	}

	public IssueRecord(Integer bookID, Integer memberID) {
		this.bookID = bookID;
		this.memberID = memberID;
		this.status = IssueStatus.I;
		this.issueDate = LocalDate.now();
	}

	public IssueRecord(Integer issueID, Integer bookID, Integer memberID, IssueStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		this.issueID = issueID;
		this.bookID = bookID;
		this.memberID = memberID;
		this.status = status;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public Integer getIssueID() {
		return issueID;
	}

	public void setIssueID(Integer issueID) {
		this.issueID = issueID;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
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

	@Override
	public String toString() {
		return "IssueRecord{" + "issueID=" + issueID + ", bookID=" + bookID + ", memberID=" + memberID + ", status="
				+ status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
	}
}
