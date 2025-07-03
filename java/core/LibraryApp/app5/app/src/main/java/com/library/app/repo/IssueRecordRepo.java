package com.library.app.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.model.IssueRecord;
import com.library.app.rowMappers.IssueRecordMapper;


@Repository
@Transactional
public class IssueRecordRepo {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public IssueRecordRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int issueBook(int bookId, int memberId) throws DataAccessException {
		String issueSql = "insert into issue_records (BookId, MemberId, Status, IssueDate,ReturnDate) values (?, ?, 'I', ?, ?)";
		String issueLogSql = "insert into issue_records_log (BookId, MemberId, Status, IssueDate,ReturnDate) values (?, ?, 'I', ?, ?)";
		jdbcTemplate.update(issueSql, bookId, memberId, java.sql.Date.valueOf(LocalDate.now()),
				java.sql.Date.valueOf(LocalDate.now().plusDays(20)));
		return jdbcTemplate.update(issueLogSql, bookId, memberId, java.sql.Date.valueOf(LocalDate.now()),
				java.sql.Date.valueOf(LocalDate.now().plusDays(20)));
	}

	public int returnBook(int issueId) throws DataAccessException {
		String returnSql = "update issue_records set Status='R', ReturnDate=? where IssueId=?";
		String returnLogSql = "insert into issue_records_log (IssueId, Status, ReturnDate) values (?, 'R', ?)";
		jdbcTemplate.update(returnSql, java.sql.Date.valueOf(LocalDate.now()), issueId);
		return jdbcTemplate.update(returnLogSql, issueId, java.sql.Date.valueOf(LocalDate.now()));

	}

	public IssueRecord getIssueById(int issueId) throws Exception{
		String sql="select * from issue_records where IssueId=?";
		IssueRecord issue = null;
		try {
		issue=jdbcTemplate.queryForObject(sql, new IssueRecordMapper(), issueId);
		}
		catch(Exception e) {
			throw new Exception("No issue found with this id :"+issueId);
		}
		return issue;
	}
	
	public List<IssueRecord> getAllIssuedRecords() throws DataAccessException{
		String sql="select * from issue_records";
		return jdbcTemplate.query(sql,new IssueRecordMapper());
		
	}

}
