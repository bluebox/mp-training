package com.library.library_management_system.services.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library_management_system.dao.BookDaoInterface;
import com.library.library_management_system.dao.IssueRecordDaoInterface;
import com.library.library_management_system.dao.MemberDaoInterface;
import com.library.library_management_system.domain.Book;
import com.library.library_management_system.domain.IssueRecord;
import com.library.library_management_system.exceptions.ApiException;
import com.library.library_management_system.services.IssueRecordServiceInterface;

@Service
public class IssueRecordServiceImpl implements IssueRecordServiceInterface {

	@Autowired
	private IssueRecordDaoInterface issueRecordDao;

	@Autowired
	private MemberDaoInterface memberDao;

	@Autowired
	private BookDaoInterface bookDao;

	@Override
	public IssueRecord issueBook(IssueRecord issue) {
		if (memberDao.getMemberById(issue.getMemberId()) == null) {
			throw new ApiException("Member does not exist");
		}
		Book book = bookDao.getBookById(issue.getBookId());
		if (book == null) {
			throw new ApiException("Book does not exist");
		}
		if (!"A".equals(book.getStatus())) {
			throw new ApiException("Book is not active");
		}
		if (!"A".equals(book.getAvailability())) {
			throw new ApiException("Book is not available for issue");
		}
		issue.setStatus("I");
		issue.setIssueDate(LocalDate.now());
		issue.setReturnDate(null);
		IssueRecord issued = issueRecordDao.issueBook(issue);
		if (issued == null) {
			throw new ApiException("Failed to issue the book");
		}
		bookDao.updateBookAvailability(issued.getBookId(), "I");
		return issued;
	}

	@Override
	public IssueRecord returnBook(int memberId, int bookId) {
		if (memberDao.getMemberById(memberId) == null) {
			throw new ApiException("Member does not exist");
		}
		Book book = bookDao.getBookById(bookId);
		if (book == null) {
			throw new ApiException("Book does not exist");
		}
		IssueRecord returned = issueRecordDao.returnBook(memberId, bookId);
		if (returned == null) {
			throw new ApiException("Issue record not found or already returned");
		}
		bookDao.updateBookAvailability(returned.getBookId(), "A");
		return returned;
	}

	@Override
	public List<IssueRecord> getAllIssues() {
		List<IssueRecord> issues = issueRecordDao.getAllIssues();
        if (issues == null || issues.isEmpty()) {
            throw new ApiException("No issue records found");
        }
        return issues;
	}

}
