package com.libraryManagementSystem.dao;

import java.time.LocalDate;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.InvalidException;

public interface IssueRecordDao {

	void issueBook(IssueRecord newIssue, Book book) throws InvalidException;

	void returnBook(Book book, int id, LocalDate date) throws InvalidException;

	void issueLog(IssueRecord issue) throws InvalidException;
}
