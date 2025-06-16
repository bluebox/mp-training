package com.Service;

import com.Dao.BookDao;
import com.Dao.IssueDao;
import com.Exception.*;

import com.Models.Issue;

import java.time.LocalDate;
import java.util.List;

public class IssueService {
    private final IssueDao issueDao = new IssueDao();
    private final BookDao bookDao = new BookDao();

    public void issueBook(int bookId, int memberId) throws DatabaseException, BookAlreadyIssuedException {
        if (issueDao.isBookIssued(bookId)) throw new BookAlreadyIssuedException("Book is already issued");

        Issue record = new Issue();
        record.setBookId(bookId);
        record.setMemberId(memberId);
        record.setStatus('I');
        record.setIssueDate(LocalDate.now());
      //  record.setReturnDate(record.getIssueDate().plusDays(15));
        try {
            issueDao.issueBook(record);
            bookDao.updateAvailability(bookId, 'I');
        } catch (Exception e) {
            throw new DatabaseException("Error during issuing transaction", e);
        }
    }

    public void returnBook(int bookId,int memberId) throws DatabaseException {
        try {
            issueDao.returnBook(bookId,memberId, LocalDate.now());
            bookDao.updateAvailability(bookId, 'A');
        } catch (Exception e) {
            throw new DatabaseException("Error during return transaction", e);
        }
    }

    public List<Issue> getAllIssuedRecords() throws DatabaseException {
        return issueDao.getAllIssuedRecords();
    }
    public List<Issue> getAllOverDueBooks() throws DatabaseException {
        return issueDao.getAllOverDueRecords();
    }
    

//	public Object viewOverdueBooks() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

