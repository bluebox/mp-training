package com.library.library_management_system.service;

import java.time.LocalDate;
import java.util.List;

import com.library.library_management_system.domain.Issue;

import jakarta.transaction.Transactional;

public interface IssueService {

	@Transactional
	public int addIssue(Issue issue);

	@Transactional
	public int returnBook(int bookId, int memberId, LocalDate date);

	public List<Issue> getIssues();

}
