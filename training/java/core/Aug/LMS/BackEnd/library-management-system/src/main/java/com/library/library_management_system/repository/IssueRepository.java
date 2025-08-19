package com.library.library_management_system.repository;

import java.time.LocalDate;
import java.util.List;

import com.library.library_management_system.domain.Issue;

public interface IssueRepository {

	public int issueBook(Issue issue);

	public int returnBook(int issueId, LocalDate issueDate);

	public Issue getIssueByMemberIdAndBookId(int bookId, int memberId);

	public List<Issue> getIssues();

	public int issueLog(Issue issue);
}
