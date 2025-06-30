package com.library.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.domain.IssueRecord;
import com.library.domain.RecordStatus;

@Repository
public class IssueRecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<IssueRecord> rowMapper = new RowMapper<>() {
		@Override
		public IssueRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			LocalDate returnDate = null;
			if (rs.getDate("returnDate") != null) {
				returnDate = rs.getDate("returnDate").toLocalDate();
			}
			return new IssueRecord(rs.getInt("issueId"), rs.getInt("bookId"), rs.getInt("memberId"),
					RecordStatus.fromCode(rs.getString("status")), rs.getDate("issueDate").toLocalDate(), returnDate);
		}
	};

	@Transactional
	public boolean issueBook(IssueRecord issueRecord) {
		// Insert into IssueRecords
		String sqlIssue = "INSERT INTO IssueRecords (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sqlIssue, issueRecord.getBookId(), issueRecord.getMemberId(),
				RecordStatus.ISSUED.getCode(), Date.valueOf(LocalDate.now()));

		if (rows <= 0)
			return false;

		// Insert into LogIssueRecords
		String sqlLog = "INSERT INTO LogIssueRecords (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		int logRows = jdbcTemplate.update(sqlLog, issueRecord.getBookId(), issueRecord.getMemberId(),
				RecordStatus.ISSUED.getCode(), Date.valueOf(LocalDate.now()));

		return logRows > 0;
	}

	@Transactional
	public boolean returnBook(IssueRecord issueRecord) {
		int bookId = issueRecord.getBookId();
		int memberId = issueRecord.getMemberId();
		String sql = "SELECT issueDate FROM IssueRecords WHERE bookId = ? AND memberId = ? AND status = ?";
		LocalDate issueDate = jdbcTemplate.queryForObject(
		    sql, 
		    (rs, rowNum) -> rs.getDate("issueDate").toLocalDate(), 
		    bookId, 
		    memberId, 
		    RecordStatus.ISSUED.getCode()
		);

		
		
		// Update IssueRecords status and returnDate
		String sqlUpdate = "UPDATE IssueRecords SET status = ?, returnDate = ? WHERE bookId = ? AND memberId = ? AND status = ?";
		int rowsUpdated = jdbcTemplate.update(sqlUpdate, RecordStatus.RETURNED.getCode(), Date.valueOf(LocalDate.now()),
				issueRecord.getBookId(), issueRecord.getMemberId(), RecordStatus.ISSUED.getCode());

		if (rowsUpdated <= 0)
			return false;

		// Log return into LogIssueRecords
		String sqlLog = "INSERT INTO LogIssueRecords (bookId, memberId, status, issueDate, returnDate) VALUES (?, ?, ?, ?, ?)";
		int logRows = jdbcTemplate.update(sqlLog, issueRecord.getBookId(), issueRecord.getMemberId(),
				RecordStatus.RETURNED.getCode(), Date.valueOf(issueDate),
				Date.valueOf(LocalDate.now()));

		return logRows > 0;
	}

	public List<IssueRecord> getIssuedBooksOverOneMonthOld() {
		String sql = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM IssueRecords WHERE status = ? AND issueDate < ?";
		LocalDate cutoffDate = LocalDate.now().minusMonths(1);

		return jdbcTemplate.query(sql, rowMapper, RecordStatus.ISSUED.getCode(), java.sql.Date.valueOf(cutoffDate));
	}

	public List<IssueRecord> getAllIssuedRecords() {
		String sql = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM IssueRecords";
		return jdbcTemplate.query(sql, rowMapper);
	}

	public List<IssueRecord> getActiveIssuedBooks() {
		String sql = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM IssueRecords WHERE status = ?";
		return jdbcTemplate.query(sql, rowMapper, RecordStatus.ISSUED.getCode());
	}

	public boolean alreadyIssued(int bookId, int memberId) {
		String sql = "SELECT COUNT(*) FROM IssueRecords WHERE bookId = ? AND memberId = ? AND status = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookId, memberId,
				RecordStatus.ISSUED.getCode());
		return count != null && count > 0;
	}
}
