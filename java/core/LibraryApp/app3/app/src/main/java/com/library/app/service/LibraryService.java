package com.library.app.service;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.app.dao.BookDAO;
import com.library.app.dao.IssueRecordDAO;
import com.library.app.dao.MemberDAO;
import com.library.app.model.*;


@Service
public class LibraryService {
	private final BookDAO bookDAO;
	private final MemberDAO memberDAO;
	private final IssueRecordDAO issueDAO;

	public LibraryService() throws SQLException {
		bookDAO = new BookDAO();
		memberDAO = new MemberDAO();
		issueDAO = new IssueRecordDAO();
	}

	// Book Services
	public void addBook(Book book) throws Exception {
		validateBook(book);
		bookDAO.addBook(book);
	}

	public Book getBookById(int bookId) {
		Book book = null;
		try {
			book = bookDAO.getBookById(bookId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public void updateBook(Book book) throws Exception {
		if (book.getBookId() <= 0)
			throw new Exception("Invalid Book ID");
		validateBook(book);
		bookDAO.updateBookDetails(book);
	}

	public void updateBookAvailability(int bookId, char availability) throws Exception {
		if (availability != 'A' && availability != 'I') {
			throw new Exception("Invalid availability status");
		}
		bookDAO.updateAvailability(bookId, availability);
	}

	// Member Services
	public void addMember(Member member) throws Exception {
		validateMember(member);
		memberDAO.addMember(member);
	}

	public Member getMemberById(int memberId) {
		Member member = null;
		try {
			member = memberDAO.getMemberById(memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	public void updateMember(Member member) throws Exception {
		if (member.getMemberId() <= 0)
			throw new Exception("Invalid Member ID");
		validateMember(member);
		memberDAO.updateMember(member);
	}

	// Issue services
	public void issueBook(int bookId, int memberId) throws Exception {
		Book book = bookDAO.getBookById(bookId);
		if (book == null || book.getAvailability().name().charAt(0) == 'I') {
			throw new Exception("Book is not available for issue.");
		}

		Member member = memberDAO.getMemberById(memberId);
		if (member == null) {
			throw new Exception("Member not found.");
		}

		issueDAO.issueBook(bookId, memberId);
		bookDAO.updateAvailability(bookId, 'I');
	}

	public IssueRecord getIssueById(int issueId) {
		IssueRecord issue = null;
		try {
			issue = issueDAO.getIssueById(issueId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return issue;
	}

	public void returnBook(int issueId) throws Exception {
		IssueRecord record = issueDAO.getIssueById(issueId);
		if (record == null || record.getStatus() == 'R') {
			throw new Exception("Invalid or already returned record.");
		}

		issueDAO.returnBook(issueId);
		bookDAO.updateAvailability(record.getBookId(), 'A');
	}

	// View &Streams
	public List<Book> viewAllBooks() throws Exception {
		List<Book> books = bookDAO.getAllBooks();
		if (books.isEmpty())
			throw new Exception("No books found");
		return books;
	}

	public List<Member> viewAllMembers() throws Exception {
		List<Member> members = memberDAO.getAllMembers();
		if (members.isEmpty())
			throw new Exception("No members found");
		return members;
	}

	public List<IssueRecord> viewIssuedRecords() throws Exception {
		List<IssueRecord> records = issueDAO.getAllIssuedRecords();
		if (records.isEmpty())
			throw new Exception("No members found");
		return records;
	}

	// List of overdue books (books not returned and issued before today)
	public List<IssueRecord> getOverdueBooks() throws SQLException {
		return issueDAO.getAllIssuedRecords().stream()
				.filter(record -> record.getStatus() == 'I' && record.getReturnDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
	}

	// Count of books per category
	public Map<String, Long> countBooksByCategory() throws SQLException {
		return bookDAO.getAllBooks().stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
	}

	// List of members with active issued books
	public List<Member> getMembersWithIssuedBooks() throws SQLException {
		Set<Integer> issuedMemberIds = issueDAO.getAllIssuedRecords().stream()
				.filter(record -> record.getStatus() == 'I').map(IssueRecord::getMemberId).collect(Collectors.toSet());

		return memberDAO.getAllMembers().stream().filter(member -> issuedMemberIds.contains(member.getMemberId()))
				.collect(Collectors.toList());
	}

	// Input Validation
	private void validateBook(Book book) throws Exception {
		if (book.getTitle() == null || book.getTitle().trim().isEmpty())
			throw new Exception("Book title is required");
		if (book.getAuthor() == null || book.getAuthor().trim().isEmpty())
			throw new Exception("Book author is required");
		if (book.getCategory() == null || book.getCategory().trim().isEmpty())
			throw new Exception("Book category is required");
		if (book.getStatus().name().charAt(0) != 'A' && book.getStatus().name().charAt(0) != 'I')
			throw new Exception("Invalid book status");
		if (book.getAvailability().name().charAt(0) != 'A' && book.getAvailability().name().charAt(0) != 'I')
			throw new Exception("Invalid book availability");
	}

	private void validateMember(Member member) throws Exception {
		if (member.getName() == null || member.getName().trim().isEmpty())
			throw new Exception("Member name is required");

		if (member.getEmail() == null || member.getEmail().trim().isEmpty())
			throw new Exception("Member email is required");
		if (!member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
			throw new Exception("Invalid email format");

		if (member.getMobile()<=0)
			throw new Exception("Member mobile number is required");

		if (!String.valueOf(member.getMobile()).matches("^[6-9][0-9]{9}$"))
			throw new Exception("Invalid mobile number");

		if (member.getGender().name().charAt(0) != 'M' && member.getGender().name().charAt(0) != 'F')
			throw new Exception("Invalid gender");

		if (member.getAddress() == null || member.getAddress().trim().isEmpty())
			throw new Exception("Address is required");

	}
}