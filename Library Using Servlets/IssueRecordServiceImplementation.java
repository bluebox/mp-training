package com.library.service;

import java.util.List;

import com.library.dao.IssueRecordDAOImplementation;
import com.library.model.IssueRecords; 

public class IssueRecordServiceImplementation implements IssueRecordService{
	 private final IssueRecordDAOImplementation dao = new IssueRecordDAOImplementation();

	    @Override
	    public boolean issueBook(IssueRecords record) throws Exception {
	        // Validation
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
	    public IssueRecords getActiveIssueByBookId(int bookId) throws Exception {
	        if (bookId <= 0)
	            throw new Exception("Invalid book ID");

	        return dao.getActiveIssueByBookId(bookId);
	    }

	    @Override
	    public List<IssueRecords> getAllIssues() throws Exception {
	        return dao.getAllIssues();
	    }


public List<Integer> getAvailableBookIds() throws Exception {
    return dao.getAvailableBookIds(); 
}

public List<Integer> getValidMemberIds() throws Exception {
    return dao.getValidMemberIds(); // DAO should return member IDs eligible to borrow
}
}