package com.library.library_management_system.repository.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.domain.Issue;
import com.library.library_management_system.exception.BookNotFoundException;
import com.library.library_management_system.mapper.IssueRowMapper;
import com.library.library_management_system.repository.IssueRepository;
import com.library.library_management_system.utils.IssueStatus;
import com.library.library_management_system.utils.SQLQueries;

@Repository
public class IssueRepositoryImpl implements IssueRepository {
	private final JdbcTemplate jdbcTemplate;

	public IssueRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int issueBook(Issue issue) {

		return jdbcTemplate.update(SQLQueries.ISSUE_INSERT, issue.getBookId(), issue.getMemberId(),
				IssueStatus.ISSUED.getDbName(), Date.valueOf(issue.getIssueDate()));

	}

	@Override
	public int returnBook(int issueId, LocalDate issueDate) {

		return jdbcTemplate.update(SQLQueries.ISSUE_UPDATE_RETURN_DATE, Date.valueOf(issueDate), issueId);

	}

	@Override
	public Issue getIssueByMemberIdAndBookId(int bookId, int memberId) {

		List<Issue> issues = jdbcTemplate.query(SQLQueries.ISSUE_SELECT_RETURN_DATE, new IssueRowMapper(), bookId,
				memberId);
		if (issues.isEmpty()) {
			throw new BookNotFoundException(
					"Issue with Book id " + bookId + " and member Id " + memberId + " is  not found");
		}
		return issues.get(0);

	}

	@Override
	public List<Issue> getIssues() {

		return jdbcTemplate.query(SQLQueries.ISSUE_SELECT_ALL, new IssueRowMapper());

	}

	@Override
	public int issueLog(Issue issue) {

		return jdbcTemplate.update(SQLQueries.ISSUE_LOG_INSERT, issue.getId(), issue.getBookId(), issue.getMemberId(),
				issue.getStatus().getDbName(), Date.valueOf(issue.getIssueDate()), null);

	}

}
