package com.libraryManagementSystem.services.impl;

import java.time.LocalDate;

import com.libraryManagementSystem.dao.BookDao;
import com.libraryManagementSystem.dao.IssueRecordDao;
import com.libraryManagementSystem.dao.impl.BookDaoImpl;
import com.libraryManagementSystem.dao.impl.IssueRecordDaoImpl;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.IssueService;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookStatus;

public class IssueServiceImpl implements IssueService {

	private final BookDao bookDao = new BookDaoImpl();
	private final IssueRecordDao issueDao = new IssueRecordDaoImpl();

	@Override
	public void addIssue(IssueRecord newIssue) throws InvalidException {

		if (newIssue.getIssueDate().isAfter(LocalDate.now())) {
			throw new InvalidException("Date Should not greater than today!");
		}

		Book book = bookDao.getBookById(newIssue.getBookId());
		if (book == null) {
			throw new InvalidException("Book Details not found");
		}

		if (!(book.getAvailability().equals(BookAvailability.AVAILABLE))) {
			throw new InvalidException("Book Already Issued");
		}

		if (!(book.getStatus().equals(BookStatus.ACTIVE))) {
			throw new InvalidException("Book Not Active");
		}

		issueDao.issueBook(newIssue, book);
	}

	@Override
	public void returnBook(Book book, int id, LocalDate date) throws InvalidException {

		if (date.isAfter(LocalDate.now())) {
			throw new InvalidException("Date Should not greater than today!");
		}

		if (book == null) {
			throw new InvalidException("Book Details not found");
		}

		if (!(book.getAvailability().equals(BookAvailability.ISSUED))) {
			throw new InvalidException("Book Not Issued");
		}

		issueDao.returnBook(book, id, date);

	}

}
