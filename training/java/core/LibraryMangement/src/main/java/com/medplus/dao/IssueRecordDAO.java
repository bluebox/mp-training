package com.medplus.dao;

import com.medplus.model.IssueRecord;

import java.sql.SQLException;
import java.util.List;

public interface IssueRecordDAO {
    void issueBook(IssueRecord record) throws Exception;
    IssueRecord getIssueById(int issueId) throws SQLException;
    List<IssueRecord> getAllIssuedBooks() throws Exception;
    void returnBook(int issueId) throws Exception;
    
}
