package com.library.dao.interfaces;

import com.library.model.IssueRecord;

import java.util.List;

public interface IssueRecordDao {
    boolean issueBook(IssueRecord record);
    boolean returnBook(int issueId);
    IssueRecord getActiveIssueByBookId(int bookId);
    List<IssueRecord> getAllIssues();
}
