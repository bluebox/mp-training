package com.lms.service.interfaces.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.model.Book;
import com.lms.model.IssueRecords;
import com.lms.model.Member;
import com.lms.repositories.IssueReturnRepository;
import com.lms.service.interfaces.IssueReturnService;

@Service
public class IssueReturnServiceImplementation implements IssueReturnService {

	private final IssueReturnRepository issueRepo;

	@Autowired
	public IssueReturnServiceImplementation(IssueReturnRepository issueRepo) {
		this.issueRepo = issueRepo;
	}

	@Override
	public IssueRecords issueBook(IssueRecords record) {
		if (record == null)
			throw new RuntimeException("Issue data is null");

		if (record.getBookId() <= 0 || record.getMemberId() <= 0)
			throw new RuntimeException("Invalid book or member ID");

		return issueRepo.issueBook(record);
	}

	@Override
	public IssueRecords returnBook(IssueRecords record) {
		if (record == null)
			throw new RuntimeException("Issue data is null");

		if (record.getBookId() <= 0 || record.getMemberId() <= 0)
			throw new RuntimeException("Invalid book or member ID");

		return issueRepo.returnBook(record);
	}

	@Override
	public Optional<IssueRecords> getActiveIssueByBookId(int bookId) {
		if (bookId <= 0)
			throw new RuntimeException("Invalid book ID");
		return issueRepo.getActiveIssueByBookId(bookId);
	}

	@Override
	public List<IssueRecords> getAllIssues() {
		return issueRepo.getAllIssues();
	}

	@Override
	public List<Member> getValidMemberIds() {
		return issueRepo.getValidMemberIds();
	}

	@Override
	public List<Book> getAvailabeBooks() {
		return issueRepo.getAvailabeBooks();
	}
	
	@Override
	public List<Book> getIssuedBooks() {
		return issueRepo.getIssuedBooks();
	}
	
	@Override
	public IssueRecords returnBookByBookId(String bookId) {
		
		return issueRepo.returnBookByBookId(bookId);
	}

}
