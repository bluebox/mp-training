package com.dynamic_library_management.services;

import java.util.List;

import com.dynamic_library_management.domain.IssueRecord;

public interface IssueRecordServiceInterface {

	
	List<IssueRecord> getAllIssues();
	List<IssueRecord> getOverdueBooks();
	List<List<String>> getActiveIssuedBooks();
}
