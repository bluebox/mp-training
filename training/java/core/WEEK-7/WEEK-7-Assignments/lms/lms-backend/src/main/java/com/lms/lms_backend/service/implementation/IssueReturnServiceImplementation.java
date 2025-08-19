package com.lms.lms_backend.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.lms_backend.constant.BookAvailability;
import com.lms.lms_backend.constant.BookStatus;
import com.lms.lms_backend.dao.BookDao;
import com.lms.lms_backend.dao.IssueReturnDao;
import com.lms.lms_backend.dao.MemberDao;
import com.lms.lms_backend.exception.DatabaseException;
import com.lms.lms_backend.exception.InvalidOperationException;
import com.lms.lms_backend.exception.ResourceNotFoundException;
import com.lms.lms_backend.model.Book;
import com.lms.lms_backend.model.IssueRecord;
import com.lms.lms_backend.service.IssueReturnService;

@Service
public class IssueReturnServiceImplementation implements IssueReturnService {
    private final IssueReturnDao issueReturnDao;
    private final BookDao bookDao;
    private final MemberDao memberDao;

    public IssueReturnServiceImplementation(IssueReturnDao issueReturnDao, BookDao bookDao, MemberDao memberDao) {
        this.issueReturnDao = issueReturnDao;
        this.bookDao = bookDao;
        this.memberDao = memberDao;
    }

    @Override
    @Transactional
    public void issueBook(IssueRecord issue) {
        if (memberDao.selectMemberById(issue.getMemberId()) == null) {
            throw new ResourceNotFoundException("Member not found with ID: " + issue.getMemberId());
        }

        Book book = bookDao.selectBookById(issue.getBookId());
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with ID: " + issue.getBookId());
        }
        if (book.getStatus() != BookStatus.ACTIVE) {
            throw new InvalidOperationException("Book is not active for issue");
        }
        if (book.getAvailability() != BookAvailability.AVAILABLE) {
            throw new InvalidOperationException("Book is not available for issue");
        }

        issueReturnDao.issueBook(issue);
        bookDao.insertBookLog(book);
        bookDao.updateBookAvailability(book, "I");
    }

    @Override
    @Transactional
    public void returnBook(IssueRecord issue) {
    	int logged=issueReturnDao.logIssue(issue.getIssueId());
    	if (logged == 0) {
			throw new DatabaseException("Failed to log issue record with ID: " + issue.getIssueId());
		}
        if (memberDao.selectMemberById(issue.getMemberId()) == null) {
            throw new ResourceNotFoundException("Member not found with ID: " + issue.getMemberId());
        }

        Book book = bookDao.selectBookById(issue.getBookId());
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with ID: " + issue.getBookId());
        }
        if (book.getAvailability() != BookAvailability.ISSUED) {
            throw new InvalidOperationException("Book not issued yet");
        }

        issueReturnDao.returnBook(issue);
        bookDao.insertBookLog(book);
        bookDao.updateBookAvailability(book, "A");
    }

    @Override
    public List<IssueRecord> getAllIssues() {
        return issueReturnDao.getAllIssues();
    }
}
