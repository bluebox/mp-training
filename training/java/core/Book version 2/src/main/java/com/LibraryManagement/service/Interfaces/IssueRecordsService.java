package com.LibraryManagement.service.Interfaces;

import java.util.List;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;

public interface IssueRecordsService {

	boolean issueBook(IssueRecords record) throws Exception;

	boolean returnBook(int issueId) throws Exception;

	IssueRecords getActiveIssueByBookId(int bookId) throws Exception;

	List<IssueRecords> getAllIssues() throws Exception;

	List<Integer> getAvailableBookIds() throws Exception;

	List<Integer> getValidMemberIds() throws Exception;

	List<Book> getAvailabeBooks();
}
