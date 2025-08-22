package com.libraryManagementSystem.services;

import java.time.LocalDate;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.InvalidException;

public interface IssueService {

	void addIssue(IssueRecord newIssue) throws InvalidException;

	void returnBook(Book book, int id, LocalDate date) throws InvalidException;
}
