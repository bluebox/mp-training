package com.lms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.model.Book;
import com.lms.model.IssueRecords;
import com.lms.model.Member;

@Service
public interface IssueReturnService {

	public IssueRecords issueBook(IssueRecords record);

	public IssueRecords returnBook(IssueRecords record);

	public Optional<IssueRecords> getActiveIssueByBookId(int bookId);

	public List<IssueRecords> getAllIssues();

	public List<Member> getValidMemberIds();

	public List<Book> getAvailabeBooks();
	
	public List<Book> getIssuedBooks();

	public IssueRecords returnBookByBookId(String bookId);
}
