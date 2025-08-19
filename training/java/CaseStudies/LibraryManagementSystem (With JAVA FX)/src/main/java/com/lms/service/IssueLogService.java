package com.lms.service;

import java.util.List;

import com.lms.exceptions.IdNotExistException;
import com.lms.model.Book;
import com.lms.model.Issue_Records;
import com.lms.model.OverDueList;
import com.lms.model.ReportMember;

public interface IssueLogService {
	public void addIssueRecord(Issue_Records newRecord) throws IdNotExistException;
	
	public List<Issue_Records> getAllIssuedRecords();
	void returnIssuedBook(int issueId , boolean isReturned);
	public List<ReportMember> booksOfMemberReport();
	public List<Book> booksOfMember(int memberId); 
	
	public List<OverDueList> getOverDueBooks();
}
