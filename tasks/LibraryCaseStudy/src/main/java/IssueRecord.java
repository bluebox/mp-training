import java.util.Date;

public class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private char status; // 'I' or 'R'
    private Date issueDate;
    private Date returnDate;

    public IssueRecord() {}

    public IssueRecord(int issueId, int bookId, int memberId, char status, Date issueDate, Date returnDate) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public int getIssueId() { return issueId; }
    public void setIssueId(int issueId) { this.issueId = issueId; }
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public char getStatus() { return status; }
    public void setStatus(char status) { this.status = status; }
    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "IssueRecord [issueId=" + issueId + ", bookId=" + bookId + ", memberId=" + memberId +
                ", status=" + status + ", issueDate=" + issueDate + ", returnDate=" + returnDate + "]";
    }
}