package com.webLibraryManagementSystem.services.impl;

import java.time.LocalDate;

import com.webLibraryManagementSystem.dao.BookDao;
import com.webLibraryManagementSystem.dao.IssueRecordDao;
import com.webLibraryManagementSystem.dao.impl.BookDaoImpl;
import com.webLibraryManagementSystem.dao.impl.IssueRecordDaoImpl;
import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.services.IssueService;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookStatus;
import com.webLibraryManagementSystemexceptions.InvalidException;

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
