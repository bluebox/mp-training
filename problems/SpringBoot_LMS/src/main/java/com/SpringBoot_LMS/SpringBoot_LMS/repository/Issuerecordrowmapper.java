package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Gender;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueStatus;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;

@Component
public class Issuerecordrowmapper implements RowMapper<IssueRecord>{

	@Override
	public IssueRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		IssueRecord issue=new IssueRecord();
		    issue.setIssueRecordId(rs.getInt("IssueId"));
		issue.setBookId(rs.getInt("BookId"));
		    issue.setMemberId(rs.getInt("MemberId"));
		    issue.setStatus(IssueStatus.getIssueStatus(rs.getString("Status")));
		    issue.setIssueDate(LocalDate.parse(String.valueOf(rs.getDate("IssueDate"))));
		    issue.setReturnDate(LocalDate.parse(String.valueOf(rs.getDate("ReturnDate"))));
			return issue;
	}

}
