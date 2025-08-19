package dev.tulasidhar.lms.DAO.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.tulasidhar.lms.model.Issue_Records;
import dev.tulasidhar.lms.DAO.BookDao;
import dev.tulasidhar.lms.DAO.IssueRecordDao;
import dev.tulasidhar.lms.DAO.MemberDao;
import dev.tulasidhar.lms.DAO.mappers.IssueRecordRowMapper;


@Component
public class IssueRecordDaoImpl implements IssueRecordDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	BookDao bookDao;

	@Override
	public int addIssueRecord(Issue_Records newRecord) {
		String query="INSERT INTO issue_records(BookId,MemberId,Status,IssueDate,ReturnDate) VALUES (?,?,?,?,?);";
		
		int rowsAffected = jdbcTemplate.update(query , newRecord.getBookId(),
									newRecord.getMemberId(),
									String.valueOf(newRecord.getStatus()),
									newRecord.getIssueDate(),
									newRecord.getReturnDate());
		
		return rowsAffected;
	}

	
	//TODO: throw exception and handle
	@Override
	public List<Issue_Records> getAllIssuedRecords() {
		String query="SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records;";
		return jdbcTemplate.query(query,new IssueRecordRowMapper());
		
	}
	
	
	@Transactional
	@Override
	public void updateIssueRecord(int issueId , boolean isReturned) {
		
		String query="UPDATE issue_records SET Status=?,ReturnDate=? WHERE IssueId=?";
		String bookQuery = "UPDATE books SET Availability=? WHERE BookId=?";
		//NEW method
				jdbcTemplate.update(query,
									isReturned ? "R" : "I",
									isReturned? java.sql.Date.valueOf(LocalDate.now()) : null,
									issueId);
				
				Issue_Records record=getRecordById(issueId);
				
				jdbcTemplate.update(bookQuery,
										"A",
										record.getBookId());
		
	}
	
	@Override
	public int addIssue_Records_Log(Issue_Records record) {
		String query="INSERT INTO issue_records_log(IssueId,BookId,MemberId,Status,IssueDate,ReturnDate) VALUES (?,?,?,?,?,?);";
		return jdbcTemplate.update(query,
										record.getIssueId(),
										record.getBookId(),
										record.getMemberId(),
										String.valueOf(record.getStatus()),
										record.getIssueDate(),
										record.getReturnDate());
	}
	
	@Override
	public Issue_Records getRecordById(int issueId) {
		String query="SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records WHERE IssueId=?;";
		Issue_Records record = jdbcTemplate.queryForObject(query,new Object[]{issueId} , new IssueRecordRowMapper());
		return record;	
	
	}
	
	@Override
	public List<Issue_Records> getAllIssuedRecordLogs() {
		String query="SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records_log;";
		
		return jdbcTemplate.query(query,new IssueRecordRowMapper());
	}


}
