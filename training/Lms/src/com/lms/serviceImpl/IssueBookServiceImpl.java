
package com.lms.serviceImpl;

import com.lms.dao.BookIssueDaoInterface;
import com.lms.daoImpl.IssueBookDaoImpl;
import com.lms.model.IssueBook;
import com.lms.service.IssueBookServiceInterface;

import java.time.LocalDate;
import java.util.List;

public class IssueBookServiceImpl implements IssueBookServiceInterface {

    private final BookIssueDaoInterface issueBookDao;

    public IssueBookServiceImpl() {
        this.issueBookDao = new IssueBookDaoImpl();
    }

    public boolean issueBook(IssueBook record) {
        return issueBookDao.issueBook(record);
    }

    public boolean returnBook(int issueId, LocalDate returnDate) {
        return issueBookDao.returnBook(issueId, returnDate);
    }

    public List<IssueBook> getAllIssueRecords() {
        return issueBookDao.getAllIssueRecords();
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
        return issueBookDao.getActiveIssuesByMember(memberId);
    }

    @Override
    public boolean isBookAlreadyIssued(int bookId) {
        return issueBookDao.isBookAlreadyIssued(bookId);
    }
}