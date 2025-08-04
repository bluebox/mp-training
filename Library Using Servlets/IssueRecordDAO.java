package com.library.dao;

import java.util.List;

import com.library.model.IssueRecords;

public interface IssueRecordDAO {
	
	boolean issueBook(IssueRecords record);
    boolean returnBook(int issueId);
    IssueRecords getActiveIssueByBookId(int bookId);
    List<IssueRecords> getAllIssues();
	List<Integer> getAvailableBookIds();
	List<Integer> getValidMemberIds();
}
