package com.example.Backend.serviceImplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.constants.IssueRecordStatus;
import com.example.Backend.daoImplementation.BookDaoImpl;
import com.example.Backend.daoImplementation.IssueRecordDaoImpl;
import com.example.Backend.domain.Book;
import com.example.Backend.domain.IssueRecord;
import com.example.Backend.service.IssueRecordInterface;

@Service
public class IssueRecordServiceImplementation implements IssueRecordInterface {

	@Autowired
	private IssueRecordDaoImpl issueDao;

	@Autowired
	private BookDaoImpl bookDao;

	public String issueBook(int bookId, int memberId) {
		IssueRecord issue = new IssueRecord();
		issue.setBookId(bookId);
		issue.setMemberId(memberId);
		issue.setStatus(IssueRecordStatus.ISSUED);
		issue.setIssueDate(LocalDate.now());
		issue.setReturnDate(null);

		issueDao.issueBook(issue);

		bookDao.updateAvailability(bookId, "I");
		return "Book issued Successfully";

	}

	@Override
	public String returnBook(int memberId, int bookId) {

		IssueRecord issue = issueDao.findActiveIssue(memberId, bookId);
		if (issue == null) {
			return "no Active books";
		}

		issueDao.returnBook(issue.getIssueId(), LocalDate.now());
		bookDao.updateAvailability(bookId, "A");
		return "Book returned successfully";

	}

	@Override
	public List<IssueRecord> getAllIssues() {
		return issueDao.getAllIssues();
	}

	@Override
	public List<Book> getAvailableBooks() {
		return issueDao.getAvailableBooks();
	}

	@Override
	public List<Book> getIssuedBooksForMember(int memberId) {
		return issueDao.getIssuedBooksForMember(memberId);
	}

}
