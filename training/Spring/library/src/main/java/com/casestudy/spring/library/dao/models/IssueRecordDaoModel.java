package com.casestudy.spring.library.dao.models;

import java.sql.SQLException;
import java.util.List;

import com.casestudy.spring.library.beans.IssueRecord;

public interface IssueRecordDaoModel {
	
	void issueBook(IssueRecord issueRecord) throws SQLException;
	
	void returnBook(IssueRecord issueRecord) throws SQLException;
	
	List<IssueRecord> getIssuedBooks();
	
	List<IssueRecord> getAllIssuedRecords();
	
	List<IssueRecord> getActiveIssuedBooks() ;
	
	boolean alreadyIssued(IssueRecord issueRecord) ;
	
}

