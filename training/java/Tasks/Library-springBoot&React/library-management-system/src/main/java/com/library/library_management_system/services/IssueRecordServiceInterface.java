package com.library.library_management_system.services;

import java.util.List;

import com.library.library_management_system.domain.IssueRecord;

public interface IssueRecordServiceInterface {

	IssueRecord issueBook(IssueRecord issue);

	IssueRecord returnBook(int memberId, int bookId);

	List<IssueRecord> getAllIssues();
}
