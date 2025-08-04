package DAO;

import domain.Issue_records;
import java.sql.SQLException;
import java.util.List;

public interface issuerecordinterface {

    boolean isMemberRegistered(int memberId) throws SQLException;

    boolean isBookStatusActive(int bookId) throws SQLException;

    boolean isBookAvailable(int bookId) throws SQLException;

    boolean issueBook(int bookId, int memberId) throws SQLException;

    boolean isBookIssued(int bookId, int memberId) throws SQLException;

    boolean returnBook(int bookId, int memberId) throws SQLException;

    List<Issue_records> printAllIssueRecords() throws SQLException;
}
