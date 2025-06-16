package com.library.service;

import java.sql.SQLException;
import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.IssueBookDAO;
import com.library.dao.MemberDAO;
import com.library.domain.Book;
import com.library.domain.IssueRecord;
import com.library.domain.Member;
import com.library.utilities.MemberValidation;

public class LibraryServiceImplementation extends SQLQuery implements LibraryService {
	private final IssueBookDAO issueBookDAO = new IssueBookDAO();
	private final BookDAO bookDAO = new BookDAO();

	public boolean addMember(Member member) {
		MemberDAO memberDAO = new MemberDAO();
		if (!MemberValidation.isValidMember(member)) {
			throw new IllegalArgumentException("Invalid member data");
		}
		return memberDAO.addMember(member, insertMember);
	}

	public List<Book> fetchAllBooks() {
		return bookDAO.getAllBooks(selectAllBooks);
	}

	public boolean returnBook(int bookId, int memberId) throws SQLException {
		
		if (bookDAO.changeAvailability(bookId, changeBookAvaliability)
				&& issueBookDAO.returnBook(bookId, memberId, updateReturnBook)
				&& issueBookDAO.logReturn(bookId, memberId, returnIssueLog)) {
			return true;
		}
		return false;
	}

	public boolean isBookIssuedToMember(int bookId, int memberId) throws SQLException {
		return issueBookDAO.isBookIssuedToMember(bookId, memberId, isBookIssued);
	}

	public boolean issueBook(IssueRecord record) throws SQLException {
		if(!issueBookDAO.isBookIssuedToMember(record.getBookId(), record.getMemberId(), isBookIssued))
		{
			return false;
		}
		if (issueBookDAO.isBookAvailable(record.getBookId())) {
			issueBookDAO.issueBook(record);
			return true;
		}
		return false;
	}

	public List<IssueRecord> getAllIssuedBooks() throws SQLException {
		return issueBookDAO.getAllIssuedBooks();
	}
	@Override
	public boolean addBook(Book book) {

		return bookDAO.insertBook(book);
	}
}
