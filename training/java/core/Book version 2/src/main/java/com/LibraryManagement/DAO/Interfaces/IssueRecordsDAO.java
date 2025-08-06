package com.LibraryManagement.DAO.Interfaces;

import java.util.List;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;

public interface IssueRecordsDAO {

	boolean issueBook(IssueRecords record);

	boolean returnBook(int issueId);

	IssueRecords getActiveIssueByBookId(int bookId);

	List<IssueRecords> getAllIssues();

	List<Integer> getAvailableBookIds();

	List<Integer> getValidMemberIds();

	List<Book> getAvailableBooks();
}
