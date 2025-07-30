import java.util.Date;
import java.util.List;

public interface IssueRecordDAO {
    boolean issueBook(int bookId, int memberId, Date issueDate);
    boolean returnBook(int issueId, Date returnDate);
    IssueRecord getIssueRecordById(int issueId);
    List<IssueRecord> getAllIssueRecords();
    List<IssueRecord> getActiveIssueRecords();
    List<IssueRecord> getIssueRecordsByMember(int memberId);
    List<IssueRecord> getIssueRecordsByBook(int bookId);
}