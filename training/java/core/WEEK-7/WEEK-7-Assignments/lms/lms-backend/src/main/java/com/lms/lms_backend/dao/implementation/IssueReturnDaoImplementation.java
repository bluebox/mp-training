package com.lms.lms_backend.dao.implementation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.lms_backend.constant.IssueRecordStatus;
import com.lms.lms_backend.dao.IssueReturnDao;
import com.lms.lms_backend.exception.DatabaseException;
import com.lms.lms_backend.model.IssueRecord;

@Repository
public class IssueReturnDaoImplementation implements IssueReturnDao {
	private final JdbcTemplate jdbcTemplate;

	public IssueReturnDaoImplementation(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final RowMapper<IssueRecord> issueMapper = (rs, rowNum) -> {
		IssueRecord issue = new IssueRecord();
		issue.setIssueId(rs.getInt("issue_id"));
		issue.setBookId(rs.getInt("book_id"));
		issue.setMemberId(rs.getInt("member_id"));
		issue.setStatus(IssueRecordStatus.fromDbName(rs.getString("status")));
		issue.setIssueDate(rs.getDate("issue_date").toLocalDate());
		issue.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);
		return issue;
	};

	@Override
	public void issueBook(IssueRecord issue) {
		String sql = "INSERT INTO issue_records (book_id, member_id, status, issue_date, return_date) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, issue.getBookId(), issue.getMemberId(), IssueRecordStatus.ISSUED.getDbName(),
				Date.valueOf(issue.getIssueDate()),
				issue.getReturnDate() != null ? Date.valueOf(issue.getReturnDate()) : null);
	}

	@Override
	public void returnBook(IssueRecord issue) {
		logIssue(issue.getIssueId());
		String sql = "UPDATE issue_records SET status = ?, return_date = ? WHERE issue_id = ?";
		int updated = jdbcTemplate.update(sql, IssueRecordStatus.RETURNED.getDbName(), Date.valueOf(LocalDate.now()),
				issue.getIssueId());
		if (updated == 0) {
			throw new DatabaseException("Failed to update issue record with ID: " + issue.getIssueId());
		}
	}

	@Override
	public List<IssueRecord> getAllIssues() {
		return jdbcTemplate.query("SELECT * FROM issue_records", issueMapper);
	}

	@Override
	public int logIssue(int issueId) {
		String sql = """
				INSERT INTO issue_records_log (issue_id, book_id, member_id, status, issue_date, return_date)
				SELECT issue_id, book_id, member_id, status, issue_date, return_date
				FROM issue_records
				WHERE issue_id = ?
				""";
		int logged = jdbcTemplate.update(sql, issueId);
		return logged;
	}
}
