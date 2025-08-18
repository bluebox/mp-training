package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.BookAvailability;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueStatus;


@Repository
public class IssueRecordRepoitory implements IssueRecordInterface {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	private Issuerecordrowmapper rowmapper;
	
	 @Autowired 
	 private BookRepository book;
	
	
	@Override
	public int returnBook(int BookId, int MemebrId) throws SQLException {
		String query="update librarymanagementsystem.issue_records set status=? where BookId=? and MemberId=? and status=?";
		String logquery="insert into librarymanagementsystem.issue_recordslog (IssueId,BookId,MemberId,status,issuedate,ReturnDate) values(?,?,?,?,?,?)";
		IssueRecord recorde=getIssueRecord(BookId,MemebrId);
		if(recorde != null) {
		int logvalue=jdbcTemplate.update(logquery,recorde.getIssueRecordId(),recorde.getBookId(),recorde.getMemberId(),recorde.getStatus().getType(),java.sql.Date.valueOf(recorde.getIssueDate()),java.sql.Date.valueOf(recorde.getReturnDate()));
		int value=jdbcTemplate.update(query,IssueStatus.RETURNED.getType(),BookId,recorde.getMemberId(),IssueStatus.ISSUED.getType());
		if(logvalue>0 && value>0) {
			return 1;
		}
		}
		return 0;
		
	}

	@Override
	public boolean checkBookIssue(int BookId, int MemberId) throws SQLException {
		String query="select IssueId,BookId,MemberId,status,issuedate,ReturnDate from librarymanagementsystem.issue_records where BookId=? and MemberId=? and status=?";
		IssueRecord value=jdbcTemplate.queryForObject(query,rowmapper,BookId,MemberId,IssueStatus.ISSUED.getType());
		if(value == null) {
			return false;
		}
		return true; 
	}

	@Override
	public int createBookIssue(IssueRecord issue) throws SQLException {
		String query="insert into librarymanagementsystem.issue_records (BookId,MemberId,status,issuedate,ReturnDate) values(?,?,?,?,?)";
		int value=0;
		if(!checkBookIssue(issue.getBookId(),issue.getMemberId())) {
		book.updateAvailability(issue.getBookId(),(issue.getStatus()==IssueStatus.RETURNED)?BookAvailability.AVAILABLE:BookAvailability.ISSUED);			   		
		value=jdbcTemplate.update(query,issue.getBookId(),issue.getMemberId(),issue.getStatus().getType(),java.sql.Date.valueOf(issue.getIssueDate()),java.sql.Date.valueOf(issue.getReturnDate()));
		if(value>0) {
			return value;
		}}
		return 0;
		
	}
	
	public List<IssueRecord> getAllIssueRecords() throws SQLException{
		String query ="select IssueId,BookId,MemberId,status,iSsuedate,ReturnDate from librarymanagementsystem.issue_records";
		List<IssueRecord> issues=jdbcTemplate.query(query,rowmapper);
		  if(issues == null) {
			  return null;
		  }
		  return issues;
		
	}
	
	public IssueRecord getIssueRecord(int bookId,int MemberId) throws SQLException {
		String query="select IssueId,BookId,MemberId,status,issuedate,ReturnDate from librarymanagementsystem.issue_records where BookId=? and MemberId=? and status=?";
		IssueRecord value=jdbcTemplate.queryForObject(query,rowmapper,bookId,MemberId,IssueStatus.ISSUED.getType());
		if(value != null) {
		    return value;
		}
		return null; 
	}

}
