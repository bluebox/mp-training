package com.webLibraryManagementSystem.services;

import java.time.LocalDate;

import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.InvalidIssueDataException;
import com.webLibraryManagementSystem.exceptions.InvalidMemberDataException;

public interface IssueService {

	void addIssue(IssueRecord issue) throws InvalidIssueDataException, BookNotFoundException,
			DatabaseOperationException, InvalidMemberDataException;

	void returnBook(int bookId, int member, LocalDate date) throws DatabaseOperationException,
			InvalidIssueDataException, BookNotFoundException, InvalidMemberDataException;
}
