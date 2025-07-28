package domain;


import java.sql.Date;

import domain.checking_enum.Status_issue;

public class Issue_records {
	@Override
	public String toString() {
		return "Issue_records [memberid=" + memberid + ", bookid=" + bookid + ", returndate=" + returndate
				+ ", issuedate=" + issuedate + ", status_issue=" + status_issue + "]";
	}
	private int memberid;
	private int bookid;
	private Date returndate;
	private  Date issuedate;
	Status_issue status_issue;
	public Issue_records(int bookid, int memberid,Status_issue status_issue,Date issuedate,Date returndate) {
		super();
		this.memberid=memberid;
		this.bookid = bookid;
		this.returndate=returndate;
		this.issuedate=issuedate;
		this.status_issue=status_issue;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public Status_issue getStatus_issue() {
		return status_issue;
	}
	public void setStatus_issue(Status_issue status_issue) {
		this.status_issue = status_issue;
	}
	
	
	
	

}
