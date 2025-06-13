package application;

import java.time.LocalDate;

public class IssueRecord {

	private int issueId;
	private int bookId;
	private int memberId;
	private BookStatus status;
	private LocalDate issue_date;
	private LocalDate return_date;

	public IssueRecord(int issueId, int bookId, int memberId, BookStatus status, LocalDate issue_date,
			LocalDate return_date) {
		super();
		this.issueId = issueId;
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issue_date = issue_date;
		this.return_date = return_date;
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

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}

	@Override
	public String toString() {
		return "IssueRecord [issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status="
				+ status + ", issue_date=" + issue_date + ", return_date=" + return_date + "]";
	}

}
