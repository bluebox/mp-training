package DAO;

import java.sql.SQLException;
import java.time.LocalDate;

import Domain.IssueStatus;

public interface IssueRecordInterface {
    void createBookIssue(int BookId, int MemberId, IssueStatus status, LocalDate issueDate, LocalDate ReturnDate) throws SQLException;
   void returnBook(int BookId, int MemebrId) throws SQLException;
   boolean checkBookIssue(int BookId, int MemberId) throws SQLException;
}
