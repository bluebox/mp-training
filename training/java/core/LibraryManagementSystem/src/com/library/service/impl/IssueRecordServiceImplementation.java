package com.library.service.impl;

import com.library.dao.impl.IssueRecordDaoImplementation;
import com.library.dao.interfaces.IssueRecordDao;
import com.library.model.IssueRecord;
import com.library.service.interfaces.IssueRecordService;

import java.util.List;

public class IssueRecordServiceImplementation implements IssueRecordService {

    private final IssueRecordDao dao = new IssueRecordDaoImplementation();

    @Override
    public boolean issueBook(IssueRecord record) throws Exception {
     
        if (record == null)
            throw new Exception("Issue data is null");

        if (record.getBookId() <= 0 || record.getMemberId() <= 0)
            throw new Exception("Invalid book or member ID");

        return dao.issueBook(record);
    }

    @Override
    public boolean returnBook(int issueId) throws Exception {
        if (issueId <= 0)
            throw new Exception("Invalid issue ID");

        return dao.returnBook(issueId);
    }

    @Override
    public IssueRecord getActiveIssueByBookId(int bookId) throws Exception {
        if (bookId <= 0)
            throw new Exception("Invalid book ID");

        return dao.getActiveIssueByBookId(bookId);
    }

    @Override
    public List<IssueRecord> getAllIssues() throws Exception {
        return dao.getAllIssues();
    }
}
