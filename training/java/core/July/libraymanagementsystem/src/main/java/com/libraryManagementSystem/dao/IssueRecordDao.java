package com.libraryManagementSystem.dao;

import java.time.LocalDate;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidIssueDataException;

public interface IssueRecordDao {

	void issueBook(IssueRecord newIssue, Book book) throws DatabaseConnectionException, DatabaseOperationException;

	void returnBook(Book book, int id, LocalDate date) throws DatabaseOperationException, InvalidIssueDataException;

	void issueLog(IssueRecord issue) throws DatabaseOperationException;
}
