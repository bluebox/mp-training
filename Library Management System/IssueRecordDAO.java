package com.LibraryManagement.DAO;

import java.util.List;

import com.LibraryManagement.models.IssueRecords;

public interface IssueRecordDAO {
	
	boolean issueBook(IssueRecords record);
    boolean returnBook(int issueId);
    IssueRecords getActiveIssueByBookId(int bookId);
    List<IssueRecords> getAllIssues();
}
