package com.library_management.dao;

import java.util.List;
import com.library_management.domain.IssueRecord;

public interface IssueRecordDaoInterface {
	
	String issueBook(IssueRecord issue);
	String returnBook(int memberId, int bookId);
	List<IssueRecord> getAllIssues();
	List<List<String>> getStatusTable();
}
