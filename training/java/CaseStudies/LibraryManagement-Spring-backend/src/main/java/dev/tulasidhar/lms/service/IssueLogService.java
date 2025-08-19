package dev.tulasidhar.lms.service;

import java.util.List;

import dev.tulasidhar.lms.Exceptions.IdNotExistException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.model.Issue_Records;
import dev.tulasidhar.lms.model.OverDueList;
import dev.tulasidhar.lms.model.ReportMember;

public interface IssueLogService {
	public void addIssueRecord(Issue_Records newRecord) throws IdNotExistException;
	
	public List<Issue_Records> getAllIssuedRecords();
	void returnIssuedBook(int issueId , boolean isReturned);
	public List<ReportMember> booksOfMemberReport();
	public List<Book> booksOfMember(int memberId); 
	
	public List<OverDueList> getOverDueBooks();
}
