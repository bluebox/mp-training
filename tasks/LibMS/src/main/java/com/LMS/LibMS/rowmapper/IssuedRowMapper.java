package com.LMS.LibMS.rowmapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.model.enums.IssueStatus;

public class IssuedRowMapper implements RowMapper<IssueRecord>{

	@Override
	public IssueRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		IssueRecord issueRecord = new IssueRecord();
		
		
		issueRecord.setBookId(rs.getInt("IssueId"));
		issueRecord.setBookId(rs.getInt("BookId"));
		issueRecord.setMemberId(rs.getInt("MemberId"));
		issueRecord.setStatus(IssueStatus.fromCode(rs.getString("Status")));
		issueRecord.setIssueDate(rs.getTimestamp("IssueDate").toLocalDateTime());
		issueRecord.setIssuedBy(rs.getString("issued_by"));
		if(rs.getTimestamp("ReturnDate")!=null) {
			issueRecord.setReturnDate(rs.getTimestamp("ReturnDate").toLocalDateTime());			
		}
		issueRecord.setReturnedBy(rs.getString("returned_by"));
		return issueRecord;
	}
	

}
