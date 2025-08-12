package com.webLibraryManagementSystem.dao;

import java.sql.Connection;
import java.time.LocalDate;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.exceptions.DatabaseConnectionException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.InvalidIssueDataException;

public interface IssueRecordDao {

	void issueBook(IssueRecord newIssue, Book book) throws DatabaseConnectionException, DatabaseOperationException;

	void returnBook(Book book, int id, LocalDate date) throws DatabaseOperationException, InvalidIssueDataException;

	void issueLog(IssueRecord issue, Connection con) throws DatabaseOperationException;
}
