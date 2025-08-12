package com.webLibraryManagementSystem.services.impl;

import java.time.LocalDate;

import com.webLibraryManagementSystem.dao.BookDao;
import com.webLibraryManagementSystem.dao.IssueRecordDao;
import com.webLibraryManagementSystem.dao.MemberDao;
import com.webLibraryManagementSystem.dao.impl.BookDaoImpl;
import com.webLibraryManagementSystem.dao.impl.IssueRecordDaoImpl;
import com.webLibraryManagementSystem.dao.impl.MemberDaoImpl;
import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseConnectionException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.InvalidIssueDataException;
import com.webLibraryManagementSystem.exceptions.InvalidMemberDataException;
import com.webLibraryManagementSystem.services.IssueService;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookStatus;

public class IssueServiceImpl implements IssueService {

	private final BookDao bookDao = new BookDaoImpl();
	private final MemberDao memberDao = new MemberDaoImpl();
	private final IssueRecordDao issueDao = new IssueRecordDaoImpl();

	@Override
	public void addIssue(IssueRecord issue) throws InvalidIssueDataException, BookNotFoundException,
			DatabaseOperationException, InvalidMemberDataException {
		Book book = null;
		Member member = null;
		try {
			book = bookDao.getBookById(issue.getBookId());
		} catch (BookNotFoundException | DatabaseOperationException e) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}
		if (book == null) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}
		try {

			member = memberDao.getMemberId(issue.getMemberId());
		} catch (Exception e) {
			throw new InvalidMemberDataException("Member not found please select form Member Options");
		}

		if (member == null) {
			throw new InvalidMemberDataException("Member not found please select form Member Options");
		}
		if (issue.getIssueDate() == null) {
			throw new InvalidIssueDataException("Please select Date from date Picker");
		}

		if (issue.getIssueDate().isAfter(LocalDate.now())) {
			throw new InvalidIssueDataException("Date Should not greater than today!");
		}

		if (!(book.getAvailability().equals(BookAvailability.AVAILABLE))) {
			throw new InvalidIssueDataException("Book Already Issued");
		}

		if (!(book.getStatus().equals(BookStatus.ACTIVE))) {
			throw new InvalidIssueDataException("Book Not Active");
		}

		try {
			issueDao.issueBook(issue, book);
		} catch (DatabaseOperationException | DatabaseConnectionException e) {
			throw new DatabaseOperationException(e.getMessage());
		}
	}

	@Override
	public void returnBook(int bookId, int memberId, LocalDate date) throws DatabaseOperationException,
			InvalidIssueDataException, BookNotFoundException, InvalidMemberDataException {
		Member member = null;
		Book book = null;

		try {
			book = bookDao.getBookById(bookId);
		} catch (BookNotFoundException | DatabaseOperationException e) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}

		if (book == null) {
			throw new BookNotFoundException("Book not found please select form Book Options");
		}

		try {

			member = memberDao.getMemberId(memberId);
		} catch (Exception e) {
			throw new InvalidMemberDataException("Member not found please select form Member Options");
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
