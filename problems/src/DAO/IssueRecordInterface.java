package DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import Domain.IssueRecord;
import Domain.IssueStatus;

public interface IssueRecordInterface {
    int createBookIssue(int BookId, int MemberId, IssueStatus status, LocalDate issueDate, LocalDate ReturnDate) throws SQLException;
   int returnBook(int BookId, int MemebrId) throws SQLException;
   boolean checkBookIssue(int BookId, int MemberId) throws SQLException;
   List<IssueRecord> getAllIssueRecords() throws SQLException;
   IssueRecord getIssueRecord(int bookId,int MemberId) throws SQLException;
}
