package dev.tulasidhar.lms.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Issue_Records {
	private int issueId;
	private int bookId;
	private int memberId;
	private char status;
	private Date issueDate;
	private Date returnDate;
	
	public Issue_Records() {
		this.bookId=0;
		this.memberId=0;
		this.status = RecordStatus.Issued.getCode();
		this.issueDate = null;
		this.returnDate = null;
	}
	
	public Issue_Records(int bookId, int memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = RecordStatus.Issued.getCode();
		this.issueDate = new Date(new java.util.Date().getTime());
		this.returnDate = null;
	}

	@Override
	public String toString() {
		return "Issue_Records [issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId + ", status="
				+ status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + "]";
	}
	
	
	
	
}
