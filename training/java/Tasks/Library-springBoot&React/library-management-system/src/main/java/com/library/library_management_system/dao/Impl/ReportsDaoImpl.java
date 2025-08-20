package com.library.library_management_system.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.dao.ReportsDaoInterface;
import com.library.library_management_system.domain.IssueBookDetails;
import com.library.library_management_system.row_mapper.IssueBookDetailsRowMapper;

@Repository
public class ReportsDaoImpl implements ReportsDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<String> getAllBookCategories() {
		String sql = "SELECT category FROM books WHERE status='A'";
		return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("category"));
	}

	@Override
	public List<IssueBookDetails> getAllIssueBookDetails() {
		String sql = "SELECT m.member_id, m.name, b.book_id, b.title, ir.status as issue_status, "
				+ "ir.issue_date, ir.return_date, b.status as book_status " + "FROM members m "
				+ "JOIN issue_records ir ON m.member_id = ir.member_id " + "JOIN books b ON ir.book_id = b.book_id";

		return jdbcTemplate.query(sql, new IssueBookDetailsRowMapper());
	}

}
