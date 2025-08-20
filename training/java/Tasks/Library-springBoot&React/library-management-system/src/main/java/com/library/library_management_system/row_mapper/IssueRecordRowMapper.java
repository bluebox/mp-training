package com.library.library_management_system.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.IssueRecord;

public class IssueRecordRowMapper implements RowMapper<IssueRecord> {

	@Override
	public IssueRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		 IssueRecord record = new IssueRecord();
	        record.setIssueId(rs.getInt("issue_id"));
	        record.setBookId(rs.getInt("book_id"));
	        record.setMemberId(rs.getInt("member_id"));
	        record.setStatus(rs.getString("status"));
	        record.setIssueDate(rs.getDate("issue_date").toLocalDate());
	        if (rs.getDate("return_date") != null) {
	            record.setReturnDate(rs.getDate("return_date").toLocalDate());
	        }
	        return record;
	}

}
