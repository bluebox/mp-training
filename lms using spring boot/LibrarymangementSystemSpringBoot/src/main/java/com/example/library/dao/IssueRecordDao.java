package com.example.library.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.library.domain.Book;
import com.example.library.domain.IssueRecord;

public interface IssueRecordDao {

	int issueBook(IssueRecord issue);

	int returnBook(int issueId, LocalDate returnDate);

	List<IssueRecord> getAllIssues();

	IssueRecord findActiveIssue(int memberId, int bookId);

	int logIssue(int issueId);

	List<Book> getIssuedBooksForMember(int memberId);

}
