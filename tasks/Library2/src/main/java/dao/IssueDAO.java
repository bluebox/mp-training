package dao;

import model.IssueRecord;
import java.time.LocalDate;
import java.util.List;

public interface IssueDAO {
    void issueBook(IssueRecord issue) throws Exception;
    void returnBook(int issueId, int bookId, LocalDate returnDate) throws Exception;
    List<IssueRecord> getAllIssuedRecords() throws Exception;
    IssueRecord getRecordById(int issueId) throws Exception;
}
