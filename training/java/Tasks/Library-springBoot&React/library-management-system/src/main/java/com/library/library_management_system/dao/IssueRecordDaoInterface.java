package com.library.library_management_system.dao;

import java.util.List;

import com.library.library_management_system.domain.IssueRecord;

public interface IssueRecordDaoInterface {

	IssueRecord issueBook(IssueRecord issue);

	IssueRecord returnBook(int memberId, int bookId);

	List<IssueRecord> getAllIssues();
}
