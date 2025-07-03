package com.casestudy.spring.library.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.spring.library.beans.IssueRecord;
import com.casestudy.spring.library.dao.models.IssueRecordDaoModel;
import com.casestudy.spring.library.rowmapper.IssueRecordRowMapper;

@Repository
public class IssueRecordDaoUsingJdbcTemplate implements IssueRecordDaoModel {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public IssueRecordDaoUsingJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	@Transactional
	public void issueBook(IssueRecord issueRecord) throws SQLException {
		String sql = "INSERT INTO IssueRecords (bookId, memberId, status, issueDate) VALUES (:bookId,:memberId,:status,:issueDate)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId", issueRecord.getBookId());
		params.addValue("memberId", issueRecord.getMemberId());
		params.addValue("status", "I");
		params.addValue("issueDate", Date.valueOf(issueRecord.getIssueDate()));
		namedParameterJdbcTemplate.update(sql, params);
		sql = "INSERT INTO LogIssueRecords (bookId, memberId, status, issueDate) VALUES (:bookId,:memberId,:status,:issueDate)";
		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public void returnBook(IssueRecord issueRecord) throws SQLException {

		String isReturned = "SELECT issueId,bookId, memberId, status, issueDate, returnDate FROM IssueRecords WHERE bookId = :bookId AND memberId = :memberId AND status = 'I'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId", issueRecord.getBookId());
		params.addValue("memberId", issueRecord.getMemberId());
		List<IssueRecord> records = namedParameterJdbcTemplate.query(isReturned, params, new IssueRecordRowMapper());

		if (records.isEmpty()) {
			throw new RuntimeException("No matching issued record found for return.");
		} else if (records.size() > 1) {
			throw new RuntimeException("Multiple active issue records found. Data inconsistency.");
		}

		IssueRecord tempIssueRecord = records.get(0);

		String sql = "UPDATE IssueRecords SET status = 'R', returnDate = :returnDate WHERE bookId = :bookId and memberId = :memberId and status = 'I'";
		MapSqlParameterSource updateParams = new MapSqlParameterSource();
		updateParams.addValue("returnDate", Date.valueOf(LocalDate.now()));
		updateParams.addValue("bookId", issueRecord.getBookId());
		updateParams.addValue("memberId", issueRecord.getMemberId());
		namedParameterJdbcTemplate.update(sql, updateParams);

		sql = "INSERT INTO LogIssueRecords (issueId,bookId, memberId, status, issueDate, returnDate) VALUES (:issueId,:bookId,:memberId,:status,:issueDate,:returnDate)";
		MapSqlParameterSource tempParams = new MapSqlParameterSource();
		tempParams.addValue("issueId", tempIssueRecord.getIssueId());
		tempParams.addValue("bookId", tempIssueRecord.getBookId());
		tempParams.addValue("memberId", tempIssueRecord.getMemberId());
		tempParams.addValue("status", "R");
		tempParams.addValue("issueDate", Date.valueOf(tempIssueRecord.getIssueDate()));
		tempParams.addValue("returnDate", Date.valueOf(LocalDate.now()));
		namedParameterJdbcTemplate.update(sql, tempParams);

	}

	@Override
	public List<IssueRecord> getIssuedBooks() {
		String sql = "SELECT bookId, memberId, status, issueDate, returnDate FROM IssueRecords WHERE status = 'I' AND issueDate < :issueDate";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("issueDate", Date.valueOf(LocalDate.now().minusMonths(1)));
		return namedParameterJdbcTemplate.query(sql, params, new IssueRecordRowMapper());
	}

	@Override
	public List<IssueRecord> getAllIssuedRecords() {
		String sql = "SELECT issueId,bookId, memberId, status, issueDate, returnDate FROM IssueRecords";
		return namedParameterJdbcTemplate.query(sql, new IssueRecordRowMapper());
	}

	@Override
	public List<IssueRecord> getActiveIssuedBooks() {
		String sql = "SELECT issueId,bookId, memberId, status, issueDate, returnDate from IssueRecords where status = 'I'";
		return namedParameterJdbcTemplate.query(sql, new IssueRecordRowMapper());
	}

	@Override
	public boolean alreadyIssued(IssueRecord issueRecord) {
		String sql = "select status from IssueRecords where bookId = :bookId and memberId = :memberId and status = 'I' ";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookId", issueRecord.getBookId());
		params.addValue("memberId", issueRecord.getMemberId());
		return "I".equals(namedParameterJdbcTemplate.queryForObject(sql, params, String.class)) ? true : false;
	}

}
