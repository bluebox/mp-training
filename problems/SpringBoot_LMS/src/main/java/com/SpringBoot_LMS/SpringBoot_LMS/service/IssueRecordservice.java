package com.SpringBoot_LMS.SpringBoot_LMS.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.repository.IssueRecordRepoitory;

@Service
public class IssueRecordservice {

	@Autowired
	private IssueRecordRepoitory issuerecord;
	
	public List<IssueRecord> getAllIssueRecords() throws IOException, SQLException{
		List<IssueRecord> issues=issuerecord.getAllIssueRecords();
		return issues;
	}
	
	public IssueRecord getIssueRecord(int bookid,int memberid) throws SQLException {
	   IssueRecord recorde=issuerecord.getIssueRecord(bookid,memberid);
		return recorde;
	}
	
	public int createBookIssue(IssueRecord issue) throws SQLException {
		int value=issuerecord.createBookIssue(issue);
		return value;
	}
	
	
	public int returnBook(int BookId, int MemebrId) throws SQLException {
		int value=issuerecord.returnBook(BookId,MemebrId);
		return value;
	}
	
	
	
}
