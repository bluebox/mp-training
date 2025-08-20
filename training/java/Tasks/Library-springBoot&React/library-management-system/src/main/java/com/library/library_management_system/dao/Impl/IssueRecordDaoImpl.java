package com.library.library_management_system.dao.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.dao.IssueRecordDaoInterface;
import com.library.library_management_system.domain.IssueRecord;
import com.library.library_management_system.row_mapper.IssueRecordRowMapper;

@Repository
public class IssueRecordDaoImpl implements IssueRecordDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public IssueRecord issueBook(IssueRecord issue) {
		String sql = "INSERT INTO issue_records (book_id, member_id, status, issue_date, return_date) VALUES (?, ?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sql, issue.getBookId(), issue.getMemberId(), issue.getStatus(),
				Date.valueOf(issue.getIssueDate()),
				issue.getReturnDate() != null ? Date.valueOf(issue.getReturnDate()) : null);
		if (rows == 0) {
		    return null;
		}
		return issue;
	}

	@Override
	public IssueRecord returnBook(int memberId, int bookId) {
		String checkSql = "SELECT COUNT(*) FROM issue_records WHERE member_id=? AND book_id=? AND status='I'";
		Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, memberId, bookId);
		if (count == null || count == 0) {
			return null;
		}
		String updateSql = "UPDATE issue_records SET status='R', return_date=? WHERE member_id=? AND book_id=? AND status='I'";
		int rows = jdbcTemplate.update(updateSql, Date.valueOf(LocalDate.now()), memberId, bookId);
		if (rows == 0) {
		    return null;
		}
		IssueRecord returned = new IssueRecord();
		returned.setMemberId(memberId);
		returned.setBookId(bookId);
		returned.setStatus("R");
		returned.setReturnDate(LocalDate.now());
		return returned;
	}

	@Override
	public List<IssueRecord> getAllIssues() {
		String sql = "SELECT * FROM issue_records";
		return jdbcTemplate.query(sql, new IssueRecordRowMapper());
	}
}
