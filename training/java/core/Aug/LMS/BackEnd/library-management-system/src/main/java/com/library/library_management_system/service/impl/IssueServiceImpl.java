package com.library.library_management_system.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.domain.Issue;
import com.library.library_management_system.domain.Member;
import com.library.library_management_system.exception.BookNotFoundException;
import com.library.library_management_system.exception.DatabaseOperationException;
import com.library.library_management_system.exception.InvalidDataException;
import com.library.library_management_system.exception.MemberNotFoundException;
import com.library.library_management_system.repository.BookRepository;
import com.library.library_management_system.repository.IssueRepository;
import com.library.library_management_system.repository.MemberRepository;
import com.library.library_management_system.service.IssueService;
import com.library.library_management_system.utils.BookAvailability;
import com.library.library_management_system.utils.BookStatus;

import jakarta.transaction.Transactional;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueRepository issueRepository;
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final BookServiceImpl bookService;

	public IssueServiceImpl(IssueRepository issueRepository, BookRepository bookRepository,
			MemberRepository memberRepository, BookServiceImpl bookService) {
		this.issueRepository = issueRepository;
		this.bookRepository = bookRepository;
		this.memberRepository = memberRepository;
		this.bookService = bookService;
	}

	@Override
	@Transactional
	public int addIssue(Issue issue) {

		if (issue == null) {
			throw new InvalidDataException("Invalid Issue details");
		}

		Book book = bookRepository.getBookById(issue.getBookId());
		Member member = memberRepository.getMemberById(issue.getMemberId());

		if (book == null) {
			throw new BookNotFoundException("Book not found With Id" + issue.getBookId());
		}

		if (member == null) {
			throw new MemberNotFoundException("Member not found With Id" + issue.getMemberId());
		}

		if (!(book.getAvailability().equals(BookAvailability.AVAILABLE))) {
			throw new InvalidDataException("Book Already Issued");
		}

		if (!(book.getStatus().equals(BookStatus.ACTIVE))) {
			throw new InvalidDataException("Book Not Active");
		}

		int insertedRows = issueRepository.issueBook(issue);

		if (insertedRows <= 0) {
			throw new DatabaseOperationException("Issue Not Added Please try again");
		}

		bookService.updateAvailability(book, BookAvailability.ISSUED);

		return insertedRows;

	}

	@Override
	@Transactional
	public int returnBook(int bookId, int memberId, LocalDate date) {

		Book book = bookRepository.getBookById(bookId);
		Member member = memberRepository.getMemberById(memberId);
		Issue issue = issueRepository.getIssueByMemberIdAndBookId(bookId, memberId);

		if (book == null) {
			throw new BookNotFoundException("Book not found With Id" + issue.getBookId());
		}

		if (member == null) {
			throw new MemberNotFoundException("Member not found With Id" + issue.getMemberId());
		}
		if (issue == null) {
			throw new InvalidDataException("Invalid Issue details ");
		}

		if (date == null) {
			throw new InvalidDataException("Please select Date from date Picker");
		}

		if (date.isAfter(LocalDate.now())) {
			throw new InvalidDataException("Date Should not greater than today!");
		}
		if (date.isBefore(issue.getIssueDate())) {
			throw new InvalidDataException("Date Should not Before Issue Date!");

		}
		if (!(book.getAvailability().equals(BookAvailability.ISSUED))) {
			throw new InvalidDataException("Book Not Issued");
		}

		int updatedRows = issueRepository.returnBook(issue.getId(), date);
		int insertedRows = issueRepository.issueLog(issue);
		Book book1 = bookService.updateAvailability(book, BookAvailability.AVAILABLE);

		if (updatedRows <= 0 || insertedRows <= 0 || book1 == null) {
			throw new DatabaseOperationException("return Not Added Please try again");
		}
		return updatedRows;

	}

	@Override
	public List<Issue> getIssues() {

		return issueRepository.getIssues();
	}

}
