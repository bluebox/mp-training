package com.example.Backend.service;

import java.util.List;

import com.example.Backend.domain.Book;
import com.example.Backend.domain.IssueRecord;

public interface IssueRecordInterface {

	String returnBook(int memberId, int bookId);

	List<IssueRecord> getAllIssues();

	List<Book> getAvailableBooks();

	List<Book> getIssuedBooksForMember(int memberId);

}
