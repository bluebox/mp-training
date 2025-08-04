package library.model;

import library.model.enums.IssueStatus;
import java.time.LocalDateTime;

public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private IssueStatus status; 
	private LocalDateTime issueDate;
	private String issuedBy; 
	private LocalDateTime returnDate;
	private String returnedBy;

	
	public IssueRecord(int bookId, int memberId, IssueStatus status, LocalDateTime issueDate, String issuedBy) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.issuedBy = issuedBy;
		this.returnDate = null; 
		this.returnedBy = null; 
	}

	public IssueRecord(int issueId, int bookId, int memberId, IssueStatus status, LocalDateTime issueDate,
			String issuedBy, LocalDateTime returnDate, String returnedBy) {
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.issuedBy = issuedBy;
		this.returnDate = returnDate;
		this.returnedBy = returnedBy;
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

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnedBy() {
		return returnedBy;
	}

	public void setReturnedBy(String returnedBy) {
		this.returnedBy = returnedBy;
	}

	@Override
	public String toString() {
		return "IssueRecord{" + "issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status="
				+ status.getCode() + ", issueDate=" + issueDate + ", issuedBy='" + issuedBy + '\'' + ", returnDate="
				+ returnDate + ", returnedBy='" + returnedBy + '\'' + '}';
	}
}