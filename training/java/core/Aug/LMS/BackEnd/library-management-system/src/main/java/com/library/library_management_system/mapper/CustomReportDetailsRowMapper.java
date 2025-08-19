package com.library.library_management_system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.CustomReportDetails;

public class CustomReportDetailsRowMapper implements RowMapper<CustomReportDetails> {

	@Override
	public CustomReportDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		CustomReportDetails report = new CustomReportDetails();
		report.setId(rs.getInt("issue_id"));
		report.setBookId(rs.getInt("book_id"));
		report.setBookTitle(rs.getString("book_title"));
		report.setMemberId(rs.getInt("member_id"));
		report.setMemberName(rs.getString("member_name"));
		report.setIssueDate(rs.getDate("issue_date").toLocalDate());

		return report;
	}

}
