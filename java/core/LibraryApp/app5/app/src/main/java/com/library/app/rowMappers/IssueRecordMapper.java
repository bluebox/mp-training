package com.library.app.rowMappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.app.model.IssueRecord;

public class IssueRecordMapper implements RowMapper<IssueRecord> {

    @Override
    public IssueRecord mapRow(ResultSet rs, int rowNumber) throws SQLException {
        IssueRecord issued = new IssueRecord();
        issued.setIssueId(rs.getInt("IssueId"));
		issued.setBookId(rs.getInt("BookId"));
		issued.setMemberId(rs.getInt("MemberId"));
		issued.setStatus(rs.getString("Status").charAt(0));
		issued.setIssueDate(rs.getDate("IssueDate").toLocalDate());
		Date returnDate = rs.getDate("ReturnDate");
		if (returnDate != null)
			issued.setReturnDate(returnDate.toLocalDate());
        return issued;
    }
}
