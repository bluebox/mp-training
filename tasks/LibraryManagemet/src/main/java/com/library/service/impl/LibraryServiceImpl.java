package com.library.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.library.dao.impl.BookDAOImpl;
import com.library.dao.impl.IssueBookDAOImpl;
import com.library.dao.impl.MemberDAOImpl;
import com.library.domain.Book;
import com.library.domain.IssueRecord;
import com.library.domain.Member;
import com.library.service.LibraryService;
import com.library.utilities.ConnectionMaker;
import com.library.utilities.MemberValidation;

public class LibraryServiceImpl implements LibraryService {
	private final IssueBookDAOImpl issueBookDAO = new IssueBookDAOImpl();
	private final BookDAOImpl bookDAO = new BookDAOImpl();
	private final MemberDAOImpl memberDAO = new MemberDAOImpl();

	private static final Connection conn = ConnectionMaker.getConnection();

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

	public boolean issueBook(IssueRecord issue) {
		boolean bookExists=false;
		boolean memberExists=false;
		try {
				Connection conn = ConnectionMaker.getConnection();
				conn.setAutoCommit(false);

				if(issue==null||issue.getBookId()<=0||issue.getMemberId()<=0) {
					return false;
				}
					bookExists=bookDAO.isBookExists(issue.getBookId(), conn);
					memberExists = memberDAO.isMemberExists(issue.getMemberId(), conn);
				if(bookExists&&memberExists) {
						if(bookDAO.isBookAvailable(issue.getBookId(), conn)) {
								issueBookDAO.issueBook(conn,issue.getBookId(),issue.getMemberId());
								bookDAO.updateBookAvailability(conn,issue.getBookId());
								conn.commit();
								return true;
							}
						
						}
							conn.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			return false;
	}

	public List<IssueRecord> viewAllRecords() {
		List<IssueRecord> issue=null;
			try{
				Connection conn = ConnectionMaker.getConnection();
			    	conn.setAutoCommit(false);
			    	issue = issueBookDAO.getAllIssuedBooks(conn);
			         
			        }
			catch(Exception e) {
				e.printStackTrace();
			}
		return issue;
	}

	@Override
	public boolean addBook(Book book) {

		return bookDAO.insertBook(book);
	}
	public List<Member> viewAllMembers() {
		List<Member> member=null;
			try{
				Connection conn = ConnectionMaker.getConnection();
			    	conn.setAutoCommit(false);
			    	member = memberDAO.getAllMembers(conn);
			         
			        }
			catch(Exception e) {
				e.printStackTrace();
			}
		return member;
	}
	public Boolean updateBookDetails(Book book) {

		boolean bookExists=false;

		try {
			Connection conn = ConnectionMaker.getConnection();
			conn.setAutoCommit(false);
			if(book==null||book.getBookId()<=0||book.getAuthor()==null||book.getCategory()==null||book.getStatus()==null||book.getTitle()==null) {
				return false;
			}
				bookExists=bookDAO.isBookExists(book.getBookId(), conn);
			if(bookExists) {
					bookDAO.updateDetails(conn, book);
					conn.commit();
					return true;
			}
					
		conn.rollback();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			return false;
	}
	


}
