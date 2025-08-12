package com.libraryManagementSystem.services;

import java.time.LocalDate;

import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidIssueDataException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;

public interface IssueService {

	void addIssue(Book book, Member member, LocalDate date) throws InvalidIssueDataException, BookNotFoundException,
			DatabaseOperationException, InvalidMemberDataException;

	void returnBook(Book book, Member member, LocalDate date) throws DatabaseOperationException,
			InvalidIssueDataException, BookNotFoundException, InvalidMemberDataException;
}
