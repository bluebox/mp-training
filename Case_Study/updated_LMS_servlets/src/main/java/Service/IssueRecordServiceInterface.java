package Service;

import domain.Issue_records;
import java.sql.SQLException;
import java.util.List;

public interface IssueRecordServiceInterface {

    boolean issueBook(int bookId, int memberId) throws SQLException;

    boolean returnBook(int bookId, int memberId) throws SQLException;

    List<Issue_records> getAllIssuedRecords() throws SQLException;
}
