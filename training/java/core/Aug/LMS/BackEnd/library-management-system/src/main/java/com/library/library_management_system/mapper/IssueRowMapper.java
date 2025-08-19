package com.library.library_management_system.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.Issue;
import com.library.library_management_system.utils.IssueStatus;

public class IssueRowMapper implements RowMapper<Issue> {

	@Override
	public Issue mapRow(ResultSet rs, int rowNum) throws SQLException {

		Issue issue = new Issue();
		issue.setId(rs.getInt("issue_id"));
		issue.setBookId(rs.getInt("book_id"));
		issue.setMemberId(rs.getInt("member_id"));
		issue.setStatus(IssueStatus.fromDbName(rs.getString("status")));
		issue.setIssueDate(rs.getDate("issue_date").toLocalDate());
		Date returnDate = rs.getDate("return_date");
		if (returnDate == null) {
			issue.setReturnDate(null);
		} else {
			issue.setReturnDate(returnDate.toLocalDate());
		}
		return issue;
	}

}
