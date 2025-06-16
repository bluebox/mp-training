package pojo;
import java.util.Date;
public class IssueRecord {
	public enum Status {
        ISSUED,
        RETURNED
    }
	private int issueid;
    private int bookid;
    private int memberid;
    private Status status;
    private Date issuedate;
    private Date returndate;
	public int getIssueid() {
		return issueid;
	}
	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getMemberid() {
		return memberid;
	}
	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public Date getReturndate() {
		return returndate;
	}
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
	@Override
	public String toString() {
		return "IssueRecord [issueid=" + issueid + ", bookid=" + bookid + ", memberid=" + memberid + ", status="
				+ status + ", issuedate=" + issuedate + ", returndate=" + returndate + "]";
	}
	
}
