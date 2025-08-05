package com.library.dao.interfaces;

import com.library.model.IssueRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IssueRecordDao {

    boolean issueBook(IssueRecord record, Connection conn) throws SQLException;
    boolean returnBook(int issueId, Connection conn) throws SQLException;
    IssueRecord getActiveIssueByBookId(int bookId);
    List<IssueRecord> getAllIssues();
    List<IssueRecord> getAllIssuedRecords();
}
