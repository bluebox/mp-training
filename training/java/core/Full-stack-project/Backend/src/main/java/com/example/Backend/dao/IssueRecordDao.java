package com.example.Backend.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.Backend.domain.Book;
import com.example.Backend.domain.IssueRecord;

public interface IssueRecordDao {
	int issueBook(IssueRecord issue);

	int returnBook(int issueId, LocalDate returnDate);

	List<IssueRecord> getAllIssues();

	IssueRecord findActiveIssue(int memberId, int BookId);

	List<Book> getAvailableBooks();

	List<Book> getIssuedBooksForMember(int memberId);

	int logIssue(int issueId);

}
