package com.LibraryManagement.service.Implementation;

import java.util.List;

import com.LibraryManagement.DAO.Implementation.IssueRecordsDAOImplementation;
import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.Interfaces.IssueRecordsService;

public class IssueRecordsServiceImplementation implements IssueRecordsService {
	private final IssueRecordsDAOImplementation dao = new IssueRecordsDAOImplementation();

	@Override
	public boolean issueBook(IssueRecords record) throws Exception {
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
	
	@Override
	public List<Integer> getAvailableBookIds() throws Exception {
		return dao.getAvailableBookIds();
	}
	
	@Override
	public List<Integer> getValidMemberIds() throws Exception {
		return dao.getValidMemberIds();
	}
	
	@Override
	public List<Book> getAvailabeBooks() {
		return dao.getAvailableBooks();
	}
}