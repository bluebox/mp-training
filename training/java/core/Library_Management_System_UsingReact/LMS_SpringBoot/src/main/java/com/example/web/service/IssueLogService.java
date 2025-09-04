package com.example.web.service;

import java.util.List;

import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.OverDueList;
import com.example.web.model.ReportMember;


public interface IssueLogService {
	public void addIssueRecord(Issue_Records newRecord);
	public List<Issue_Records> getAllIssuedRecords();
	void returnIssuedBook(int issueId , boolean isReturned);
	public List<ReportMember> booksOfMemberReport();
	public List<Book> booksOfMember(int memberId); 
	public List<OverDueList> getOverDueBooks();
	public Issue_Records getRecordById(int IssueId);
}
