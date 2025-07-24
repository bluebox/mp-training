package service;

import dao.IssueRecordDao;
import domain.IssueRecord;

public class IssueService {
    private final IssueRecordDao issueDao = new IssueRecordDao();

    public void issueBook(IssueRecord record) throws Throwable {
        if (issueDao.isBookAlreadyIssued(record.getBookId())) {
            throw new Exception("Book is already issued.");
        }
        issueDao.issueBook(record);
    }

    public void returnBook(int bookId, int memberId) throws Throwable {
        issueDao.returnBook(bookId, memberId);
    }
}
