package com.casestudy.spring.library.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.casestudy.spring.library.beans.IssueRecord;
import com.casestudy.spring.library.beans.RecordStatus;

public class IssueRecordRowMapper implements RowMapper<IssueRecord>{

	@Override
	public IssueRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		IssueRecord issueRecord = new IssueRecord();
		issueRecord.setBookId(rs.getInt("bookId"));
		issueRecord.setMemberId(rs.getInt("memberId"));
		issueRecord.setIssueId(rs.getInt("issueId"));
		issueRecord.setStatus(RecordStatus.fromCode(rs.getString("status")));
		issueRecord.setIssueDate(rs.getDate("issueDate").toLocalDate());
		Date returnDateRaw = rs.getDate("returnDate");
		issueRecord.setReturnDate(returnDateRaw != null ? returnDateRaw.toLocalDate() : null);
		
		return issueRecord;
	}

}
