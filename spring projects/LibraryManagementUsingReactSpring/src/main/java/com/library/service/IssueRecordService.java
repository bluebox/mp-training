package com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.BooksDao;
import com.library.dao.IssueRecordDao;
import com.library.domain.IssueRecord;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordDao issueRecordDao;
    
    @Autowired
    private BooksDao bookDao;

    public boolean issueBook(IssueRecord issueRecord) {
        if(issueRecordDao.issueBook(issueRecord)) {
        	bookDao.toggleAvailability(issueRecord.getBookId());
        	return true;
        }
        return false;
    }

    public boolean returnBook(IssueRecord issueRecord) {
        if(issueRecordDao.returnBook(issueRecord)) {
        	bookDao.toggleAvailability(issueRecord.getBookId());
        	return true;
        }
        return false;
    }

    public List<IssueRecord> getIssuedBooksOverOneMonthOld() {
        return issueRecordDao.getIssuedBooksOverOneMonthOld();
    }

    public List<IssueRecord> getAllIssuedRecords() {
        return issueRecordDao.getAllIssuedRecords();
    }

    public List<IssueRecord> getActiveIssuedBooks() {
        return issueRecordDao.getActiveIssuedBooks();
    }

    public boolean alreadyIssued(int bookId, int memberId) {
        return issueRecordDao.alreadyIssued(bookId, memberId);
    }

	
}
