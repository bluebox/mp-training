package com.library_management.services;

import java.util.List;
import com.library_management.domain.IssueRecord;

public interface IssueRecordServiceInterface {

	String issueBook(IssueRecord issue);
	String returnBook(int memberId, int bookId);
	List<IssueRecord> getAllIssues();
	List<IssueRecord> getOverdueBooks();
	List<List<String>> getActiveIssuedBooks();
}
