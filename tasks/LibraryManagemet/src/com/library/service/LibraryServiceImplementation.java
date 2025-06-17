package com.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.IssueBookDAO;
import com.library.dao.MemberDAO;
import com.library.domain.Book;
import com.library.domain.IssueRecord;
import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;
import com.library.utilities.MemberValidation;

public class LibraryServiceImplementation implements LibraryService {
	private final IssueBookDAO issueBookDAO = new IssueBookDAO();
	private final BookDAO bookDAO = new BookDAO();
	private final MemberDAO memberDAO = new MemberDAO();

	private final Connection conn = ConnectionMaker.getConnection();

	public boolean addMember(Member member) {

		if (!MemberValidation.isValidMember(member)) {
			throw new IllegalArgumentException("Invalid member data");
		}
		return memberDAO.addMember(member, conn);
	}

	public boolean returnBook(int bookId, int memberId) throws SQLException {
		if (!issueBookDAO.isBookIssuedToMember(bookId, memberId, conn)) {
			return false;
		}
		conn.setAutoCommit(false);

		if (bookDAO.changeAvailability(bookId, conn) && issueBookDAO.returnBook(bookId, memberId, conn)
				&& issueBookDAO.logReturn(bookId, memberId, conn)) {
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		}
		conn.rollback();
		conn.setAutoCommit(true);
		return false;
	}
	public List<Book> fetchAllBooks() {
		return bookDAO.getAllBooks(conn);
	}

	public boolean isBookIssuedToMember(int bookId, int memberId) throws SQLException {
		return issueBookDAO.isBookIssuedToMember(bookId, memberId, conn);
	}

	public boolean issueBook(IssueRecord record) throws SQLException {

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
