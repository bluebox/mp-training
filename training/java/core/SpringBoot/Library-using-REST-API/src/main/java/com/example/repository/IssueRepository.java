package com.example.repository;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.model.IssueRecord;

@Repository
public class IssueRepository {
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public IssueRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	public boolean bookAvailability(long bookId) {
		if(jdbcTemplate.queryForList("SELECT * FROM books WHERE bookId = ?" , bookId).size()<=0) {
			return false;
		}
		return (jdbcTemplate.queryForObject("SELECT availabilty FROM books WHERE BookId = ?",Character.class , bookId).equals('A'));
	}
	public boolean checkMember(int memberId) {
		return (jdbcTemplate.queryForList("SELECT * FROM member WHERE memberId = ?" , memberId)).size()>0;
	}
	public boolean bookIssued(long bookId) {
		return (jdbcTemplate.queryForList("SELECT * FROM issuerecords WHERE bookId = ? AND statusrec = 'I'",bookId).size()>0);
	}
	public boolean issueIdPresent(int issueId) {
		return (jdbcTemplate.queryForList("select * from issuerecords where issueId=?",issueId).size()>0);
	}
	public int insertIssue(IssueRecord i) {
		return jdbcTemplate.update("INSERT INTO issuerecords VALUES (?, ?, ?, ?, ?, ?)",i.getIssueId(),i.getBookId(),i.getMemberId(),Character.toString(i.getStatusrec()),Date.valueOf(i.getIssueDate()),Date.valueOf(i.getReturnDate()));
	}
	public int updateAvaillability(long bookId) {
		return jdbcTemplate.update("UPDATE books SET availabilty = 'I' WHERE bookId = ?",bookId);
	}
	public Long findBook(int issueId) {
		try{
			return jdbcTemplate.queryForObject("SELECT bookId FROM issuerecords WHERE issueId = ?",Long.class,issueId);
		}
		catch(NullPointerException e) {
			return -1l;
		}
	}
	
}
