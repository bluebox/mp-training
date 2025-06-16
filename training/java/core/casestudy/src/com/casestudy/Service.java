package com.casestudy;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {

	public boolean addBook(Book book) {
		BooksDao bookDao = new BooksDao();
		try {
			bookDao.createBook(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBookService(Book book) {
		BooksDao bookDao = new BooksDao();
		try {
			bookDao.updateBook(book);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void updateBookAvailabilityService(int id) {
		BooksDao bookDao = new BooksDao();
		bookDao.updateBookAvailability(id);
	}

	public List<Book> viewAllBooksService() {
		BooksDao bookDao = new BooksDao();
		List<Book> result = bookDao.viewAllBooks();
		return result;
	}

	public boolean addMemberService(Member member) {
		MembersDao membersDao = new MembersDao();

		try {
			membersDao.addMember(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateMemberService(Member member) {
		MembersDao membersDao = new MembersDao();
		return membersDao.updateMember(member);
	}

	public List<Member> getAllMembersService() {
		MembersDao membersDao = new MembersDao();
		List<Member> allMembers = membersDao.getAllMembers();
		return allMembers;
	}

	public boolean issueBookService(IssueRecord issueRecord) {
		BooksDao booksDao = new BooksDao();
		MembersDao membersDao = new MembersDao();
		if (booksDao.CanBeIssued(issueRecord.getBookId()) && membersDao.findMember(issueRecord.getMemberId())) {
			IssueRecordDao issueRecordDao = new IssueRecordDao();
			try {
				System.out.println("issuing");
				issueRecordDao.issueBook(issueRecord);
				booksDao.updateBookAvailability(issueRecord.getBookId());
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
		IssueRecordDao issueRecordDao = new IssueRecordDao();
		BooksDao booksDao = new BooksDao();
		if (issueRecordDao.alreadyIssued(issueRecord)) {
			try {
				issueRecordDao.returnBook(issueRecord);
				booksDao.updateBookAvailability(issueRecord.getBookId());
				return true;
			} catch (SQLException e) {
				System.out.println("Return unsucessful !!!");
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public List<IssueRecord> getAllIssuedRecordsService() {
		IssueRecordDao issueRecordDao = new IssueRecordDao();
		return issueRecordDao.getAllIssuedRecords();
	}

	public List<IssueRecord> getOverdueBooks() {
		IssueRecordDao issueRecordDao = new IssueRecordDao();
		return issueRecordDao.getIssuedBooks();
	}

	public Map<String, Long> getBooksCountPerCategory() {
		BooksDao booksDao = new BooksDao();
		List<Book> books = booksDao.viewAllBooks();
		Map<String, Long> categoryCountMap = books.stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
		return categoryCountMap;

	}

	public List<IssueRecord> getActiveIssuedBooksSerivce() {
		IssueRecordDao issueRecordDao = new IssueRecordDao();
		return issueRecordDao.getActiveIssuedBooks();
	}

}
