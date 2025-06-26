package com.casestudy.serviceimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.casestudy.dao.BooksDao;
import com.casestudy.dao.IssueRecordDao;
import com.casestudy.dao.MembersDao;
import com.casestudy.domain.Book;
import com.casestudy.domain.IssueRecord;
import com.casestudy.domain.Member;

public class Service {
	private BooksDao bookDao = new BooksDao();
	private MembersDao membersDao = new MembersDao();
	private IssueRecordDao issueRecordDao = new IssueRecordDao();
	
	//books
	public boolean addBook(Book book) {
		try {
			bookDao.createBook(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBookService(Book book) {
		try {
			return bookDao.updateBook(book);
						
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void updateBookAvailabilityService(int id) {
		bookDao.updateBookAvailability(id);
	}

	public List<Book> viewAllBooksService() {
		List<Book> result = bookDao.viewAllBooks();
		return result;
	}
	public Book getBookById(int bookId) {
		return bookDao.searchBook(bookId);
	}
	//Member
	public boolean addMemberService(Member member) {

		try {
			membersDao.addMember(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateMemberService(Member member) {
		return membersDao.updateMember(member);
	}

	public List<Member> getAllMembersService() {
		List<Member> allMembers = membersDao.getAllMembers();
		return allMembers;
	}
	
	public Member getMemberById(int id) {
		return membersDao.getMemberById(id);
	}
	//issuebooks
	public boolean issueBookService(IssueRecord issueRecord) {
		MembersDao membersDao = new MembersDao();
		if (bookDao.CanBeIssued(issueRecord.getBookId()) && membersDao.findMember(issueRecord.getMemberId())) {
			IssueRecordDao issueRecordDao = new IssueRecordDao();
			try {
				System.out.println("issuing");
				issueRecordDao.issueBook(issueRecord);
				bookDao.updateBookAvailability(issueRecord.getBookId());
				return true;
			} catch (SQLException e) {
				System.out.println("Someting went wrong in insertion .");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean returnBookService(IssueRecord issueRecord) {
		if (issueRecordDao.alreadyIssued(issueRecord)) {
			try {
				issueRecordDao.returnBook(issueRecord);
				bookDao.updateBookAvailability(issueRecord.getBookId());
				System.out.println("insdie try service , return book service");
				return true;
			} catch (SQLException e) {
				System.out.println("Return unsucessful !!!");
				e.printStackTrace();
				System.out.println("insdie try service , return book service");
				return false;
			}
		}
		return false;
	}

	public List<IssueRecord> getAllIssuedRecordsService() {
		return issueRecordDao.getAllIssuedRecords();
	}

	public List<IssueRecord> getOverdueBooks() {
		return issueRecordDao.getIssuedBooks();
	}

	public Map<String, Long> getBooksCountPerCategory() {
		List<Book> books = bookDao.viewAllBooks();
		Map<String, Long> categoryCountMap = books.stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
		return categoryCountMap;

	}

	public List<IssueRecord> getActiveIssuedBooksSerivce() {
		return issueRecordDao.getActiveIssuedBooks();
	}

}
