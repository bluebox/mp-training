package com.library.app.service;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.app.model.*;
import com.library.app.repo.*;


@Service
public class LibraryService {
	
	private final BookRepo bookRepo;
	private final IssueRecordRepo issueRepo;
	private final MemberRepo memberRepo;
	
	@Autowired
	public LibraryService(BookRepo bookRepo,IssueRecordRepo issueRepo,MemberRepo memberRepo) {
		this.bookRepo=bookRepo;
		this.issueRepo=issueRepo;
		this.memberRepo=memberRepo;
	}

	// Book Services

	public boolean addBook(Book book) throws Exception {
		boolean isAdded=false;
		validateBook(book);
		int res = bookRepo.addBook(book);
		if(res > 0) {
			isAdded=true;
		}
		return isAdded;
	}

	public Book getBookById(int bookId) {
		Book book = null;
		try {
			book = bookRepo.getBookById(bookId);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

	public boolean updateBook(Book book) throws Exception {
		boolean isUpdated=false;
		if (book.getBookId() <= 0)
			throw new Exception("Invalid Book ID");
		validateBook(book);
		int res = bookRepo.updateBookDetails(book);
		isUpdated = res > 0 ? true : false;
		return isUpdated;
	}

	public boolean updateBookAvailability(int bookId, char availability) throws Exception {
		boolean isUpdated = false;
		if (availability != 'A' && availability != 'I') {
			throw new Exception("Invalid availability status");
		}
		int res = bookRepo.updateAvailability(bookId, availability);
		return isUpdated = res > 0 ? true:false;
	}
	
	public List<Book> viewAllBooks() throws Exception {
		List<Book> books = bookRepo.getAllBooks();
		if (books.isEmpty())
			throw new Exception("No books found");
		return books;
	}

	// Member Services
	public boolean addMember(Member member) throws Exception {
		boolean isAdded = false;
		validateMember(member);
		int res = memberRepo.addMember(member);
		return isAdded = res > 0 ? true:false;
	}

	public Member getMemberById(int memberId) {
		Member member = null;
		try {
			member = memberRepo.getMemberById(memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	public boolean updateMember(Member member) throws Exception {
		boolean isUpdated = false;
		if (member.getMemberId() <= 0)
			throw new Exception("Invalid Member ID");
		validateMember(member);
		int res = memberRepo.updateMember(member);
		return isUpdated = res > 0 ? true : false;
	}
	
	public List<Member> viewAllMembers() throws Exception {
		List<Member> members = memberRepo.getAllMembers();
		if (members.isEmpty())
			throw new Exception("No members found");
		return members;
	}

	// Issue services
	public boolean issueBook(int bookId, int memberId) throws Exception {
		boolean isIssued = false;
		Book book = bookRepo.getBookById(bookId);
		if (book == null || book.getAvailability().name().charAt(0) == 'I') {
			throw new Exception("Book is not available for issue.");
		}

		Member member = memberRepo.getMemberById(memberId);
		if (member == null) {
			throw new Exception("Member not found.");
		}

		int issueRes = issueRepo.issueBook(bookId, memberId);
		int updateRes = bookRepo.updateAvailability(bookId, 'I');
		if(issueRes > 0 && updateRes > 0) {
			isIssued = true;
		}
		return isIssued;
	}

	public IssueRecord getIssueById(int issueId) {
		IssueRecord issue = null;
		try {
			issue = issueRepo.getIssueById(issueId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue;
	}

	public boolean returnBook(int issueId) throws Exception {
		boolean isReturned = false;
		IssueRecord record = issueRepo.getIssueById(issueId);
		if (record == null || record.getStatus() == 'R') {
			throw new Exception("Invalid or already returned record.");
		}

		int returnRes = issueRepo.returnBook(issueId);
		int updateRes = bookRepo.updateAvailability(record.getBookId(), 'A');
		
		if(returnRes > 0 && updateRes > 0) {
			isReturned = true;
		}
		return isReturned;
	}	

	public List<IssueRecord> viewIssuedRecords() throws Exception {
		List<IssueRecord> records = issueRepo.getAllIssuedRecords();
		if (records.isEmpty())
			throw new Exception("No members found");
		return records;
	}

	// List of overdue books (books not returned and issued before today)
	public List<IssueRecord> getOverdueBooks() throws SQLException {
		return issueRepo.getAllIssuedRecords().stream()
				.filter(record -> record.getStatus() == 'I' && record.getReturnDate().isBefore(LocalDate.now()))
				.collect(Collectors.toList());
	}

	// Count of books per category
	public Map<String, Long> countBooksByCategory() throws SQLException {
		return bookRepo.getAllBooks().stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
	}

	// List of members with active issued books
	public List<Member> getMembersWithIssuedBooks() throws SQLException {
		Set<Integer> issuedMemberIds = issueRepo.getAllIssuedRecords().stream()
				.filter(record -> record.getStatus() == 'I').map(IssueRecord::getMemberId).collect(Collectors.toSet());

		return memberRepo.getAllMembers().stream().filter(member -> issuedMemberIds.contains(member.getMemberId()))
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