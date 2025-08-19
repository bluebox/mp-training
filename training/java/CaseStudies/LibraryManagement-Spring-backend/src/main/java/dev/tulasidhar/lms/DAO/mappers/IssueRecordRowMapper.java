package dev.tulasidhar.lms.DAO.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.tulasidhar.lms.model.Issue_Records;

public class IssueRecordRowMapper implements RowMapper<Issue_Records>{
	@Override
	public Issue_Records mapRow(ResultSet rs, int rowNum) throws SQLException {
		Issue_Records record = new Issue_Records();
		record.setIssueId(rs.getInt("IssueId"));
		record.setBookId(rs.getInt("BookId"));
		record.setMemberId(rs.getInt("MemberId"));
		record.setStatus(rs.getString("Status").charAt(0));
		record.setIssueDate(rs.getDate("IssueDate"));
		record.setReturnDate(rs.getDate("ReturnDate"));
		return record;
	}
}
