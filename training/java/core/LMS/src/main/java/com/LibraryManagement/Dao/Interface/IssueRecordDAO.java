package com.LibraryManagement.Dao.Interface;

import java.util.List;

import com.LibraryManagement.Models.IssueRecords;

public interface IssueRecordDAO {
	
	boolean issueBook(IssueRecords record);
    boolean returnBook(int issueId);
    IssueRecords getActiveIssueByBookId(int bookId);
    List<IssueRecords> getAllIssues();
	List<Integer> getAvailableBookIds();
	List<Integer> getValidMemberIds();
}
