package com.lms.Services.Implementation;


import com.lms.Dao.Interfaces.IssueRecordDao;
import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;
import com.lms.Services.Interfaces.IssueRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueRecordServiceImplementation implements IssueRecordService {

    private final IssueRecordDao dao;

    @Autowired
    public IssueRecordServiceImplementation(IssueRecordDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean issueBook(IssueRecords record)  {
        if (record == null)
            throw new RuntimeException("Issue data is null");

        if (record.getBookId() <= 0 || record.getMemberId() <= 0)
            throw new RuntimeException("Invalid book or member ID");

        return dao.issueBook(record);
    }

    @Override
    public boolean returnBook(int issueId)  {
        if (issueId <= 0)
            throw new RuntimeException("Invalid issue ID");

        return dao.returnBook(issueId);
    }

//    @Override
//    public IssueRecords getActiveIssueByBookId(int bookId) {
//        if (bookId <= 0)
//            throw new RuntimeException("Invalid book ID");
//
//        return dao.getActiveIssueByBookId(bookId);
//    }

    @Override
    public List<IssueRecords> getAllIssues()  {
        return dao.getAllIssues();
    }

    public List<Integer> getAvailableBookIds()  {
        return dao.getAvailableBookIds();
    }

    public List<Member> getValidMemberIds(){
    	return dao.getValidMemberIds();
    }
    public List<Book> getAvailabeBooks() {
    	return dao.getAvailabeBooks();
    }
}

