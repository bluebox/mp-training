package Domain;

import java.time.LocalDate;
import java.util.List;

public class IssueRecord {
    private  int IssueRecordId;
    private  int BookId;
    private  int MemberId;
    private  IssueStatus status;
    private  LocalDate issueDate;
    private  LocalDate ReturnDate;
    
	  public int getId() {
		  return IssueRecordId;
	  }
	  public void setId(int id) {
		  IssueRecordId = id;
	  }
	  public int getBookId() {
		  return BookId;
	  }
	  public void setBookId(int bookId) {
		  BookId = bookId;
	  }
	  public int getMemberId() {
		  return MemberId;
	  }
	  public void setMemberId(int memberId) {
		  MemberId = memberId;
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
		  return ReturnDate;
	  }
	  public void setReturnDate(LocalDate returnDate) {
		  ReturnDate = returnDate;
	  }
	  
	  public IssueRecord(int id, int bookId, int memberId, IssueStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		IssueRecordId = id;
		BookId = bookId;
		MemberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		ReturnDate = returnDate;
	  }
	  
	  public IssueRecord() {
		  
	  }
	  
	  @Override
	  public String toString() {
		return "IssueRecord [Id=" + IssueRecordId + ", BookId=" + BookId + ", MemberId=" + MemberId + ", status=" + status
				+ ", issueDate=" + issueDate + ", ReturnDate=" + ReturnDate + "]";
	  }
	
}
