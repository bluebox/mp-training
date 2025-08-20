package com.library.library_management_system.row_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.IssueBookDetails;

public class IssueBookDetailsRowMapper implements RowMapper<IssueBookDetails> {

    @Override
    public IssueBookDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        IssueBookDetails dto = new IssueBookDetails();
        dto.setMemberId(rs.getInt("member_id"));
        dto.setMemberName(rs.getString("name"));
        dto.setBookId(rs.getInt("book_id"));
        dto.setBookTitle(rs.getString("title"));
        dto.setIssueStatus(rs.getString("issue_status"));
        dto.setIssueDate(rs.getDate("issue_date") != null ? rs.getDate("issue_date").toLocalDate() : null);
        dto.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);
        dto.setBookStatus(rs.getString("book_status"));
        return dto;
    }
}
