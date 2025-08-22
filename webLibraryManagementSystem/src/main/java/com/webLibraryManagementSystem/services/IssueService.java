package com.webLibraryManagementSystem.services;

import java.time.LocalDate;

import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystemexceptions.InvalidException;

public interface IssueService {

	void addIssue(IssueRecord newIssue) throws InvalidException;

	void returnBook(Book book, int id, LocalDate date) throws InvalidException;
}
