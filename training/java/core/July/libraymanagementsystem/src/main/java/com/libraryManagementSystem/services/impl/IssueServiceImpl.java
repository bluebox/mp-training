package com.libraryManagementSystem.services.impl;

import java.time.LocalDate;

import com.libraryManagementSystem.dao.IssueRecordDao;
import com.libraryManagementSystem.dao.impl.IssueRecordDaoImpl;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidIssueDataException;
import com.libraryManagementSystem.exceptions.InvalidMemberDataException;
import com.libraryManagementSystem.services.IssueService;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookStatus;
import com.libraryManagementSystem.utilities.IssueStatus;

public class IssueServiceImpl implements IssueService {

	private final IssueRecordDao issueDao = new IssueRecordDaoImpl();

	@Override
	public void addIssue(Book book, Member member, LocalDate date) throws InvalidIssueDataException,
			BookNotFoundException, DatabaseOperationException, InvalidMemberDataException {

		if (book == null) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}
		if (member == null) {
			throw new InvalidMemberDataException("Member not found please select form Member Options");
		}
		if (date == null) {
			throw new InvalidIssueDataException("Please select Date from date Picker");
		}

		IssueRecord newIssue = new IssueRecord(-1, book.getBookId(), member.getMemberId(), IssueStatus.ISSUED, date,
				null);
		if (newIssue.getIssueDate().isAfter(LocalDate.now())) {
			throw new InvalidIssueDataException("Date Should not greater than today!");
		}

		if (!(book.getAvailability().equals(BookAvailability.AVAILABLE))) {
			throw new InvalidIssueDataException("Book Already Issued");
		}

		if (!(book.getStatus().equals(BookStatus.ACTIVE))) {
			throw new InvalidIssueDataException("Book Not Active");
		}

		try {
			issueDao.issueBook(newIssue, book);
		} catch (DatabaseOperationException | DatabaseConnectionException e) {
			throw new DatabaseOperationException(e.getMessage());
		}
	}

	@Override
	public void returnBook(Book book, Member member, LocalDate date) throws DatabaseOperationException,
			InvalidIssueDataException, BookNotFoundException, InvalidMemberDataException {
		if (book == null) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}

		if (member == null) {
			throw new InvalidMemberDataException("Member not found please select form Member Options");
		}

		if (date == null) {
			throw new InvalidIssueDataException("Please select Date from date Picker");
		}

		if (date.isAfter(LocalDate.now())) {
			throw new InvalidIssueDataException("Date Should not greater than today!");
		}

		if (!(book.getAvailability().equals(BookAvailability.ISSUED))) {
			throw new InvalidIssueDataException("Book Not Issued");
		}

		issueDao.returnBook(book, member.getMemberId(), date);

	}

}
