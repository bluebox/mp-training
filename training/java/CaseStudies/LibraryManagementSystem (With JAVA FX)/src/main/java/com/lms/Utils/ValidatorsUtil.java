package com.lms.Utils;

import com.lms.dao.BookDao;
import com.lms.dao.DataBookDao;
import com.lms.dao.IssueRecordDao;
import com.lms.dao.IssueRecordDaoImpl;
import com.lms.dao.MemberDao;
import com.lms.dao.MemberDaoImpl;
import com.lms.exceptions.DBConstrainsException;
import com.lms.exceptions.IdNotExistException;
import com.lms.model.Book;
import com.lms.model.Issue_Records;
import com.lms.model.Member;


public class ValidatorsUtil {


	private static BookDao bookDao;
	private static MemberDao memberDao;
	private static IssueRecordDao issueRecordDao;

	static{
		bookDao = new DataBookDao();
		memberDao = new MemberDaoImpl();
		issueRecordDao = new IssueRecordDaoImpl();
	}
	
	public static void validateBook(Book book) throws DBConstrainsException {
		String bookTitle=book.getBook_Title().trim();
		String bookAuthor=book.getBook_Author().trim();
		String bookCategory=book.getBook_Category().trim();
		char bookStatus=book.getBook_Status();
		char bookAvailability=book.getBook_Availability();
		if(bookTitle == null || bookAuthor == null || bookCategory == null || bookStatus == '\u0000' || bookAvailability == '\u0000' ||  bookTitle.isEmpty() || bookAuthor.isEmpty() || bookCategory.isEmpty() ) {
			throw new DBConstrainsException("Book fields cannot be empty");
		}
		if(bookTitle.length()>255 || (!bookTitle.matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$"))) {
			throw new DBConstrainsException("Please enter a valid Title!");
		}
		if(bookAuthor.length()>255 || (!bookAuthor.matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$"))) {
			throw new DBConstrainsException("Please enter a valid Author Name!");
		}
		if(bookCategory.length()>100 || (!bookCategory.matches("^[A-Za-z]{2}[A-Za-z0-9\\s-]{0,98}$"))) {
			throw new DBConstrainsException("Please enter a valid Category Name!");
		}
		if(bookStatus!='A' && bookStatus!='I') {
			throw new DBConstrainsException("Entered An Invalid Book Status");
		}
		if(bookAvailability!='A' && bookAvailability!='I') {
			throw new DBConstrainsException("Entered An Invalid Book Availability");
		}
	}
	
	
	public static void validateIssueRecord(Issue_Records newRecord) throws IdNotExistException {
		if(bookDao.getBookById(newRecord.getBookId())==null) {
			throw new IdNotExistException("Entered BookId Is Invalid");
		}
		if(memberDao.getMemberById(newRecord.getMemberId())==null) {
			throw new IdNotExistException("Entered MemberId Is Invalid");
		}
		
		if(newRecord.getBookId() < 0) {
			throw new IdNotExistException("Entered id(s) are not valid");

		}
	}
	
	public static void validateMember(Member member) throws IllegalArgumentException {
		
		if (member.getMember_Name() == null || member.getEmail() == null || member.getAddress() == null || member.getMobile_No() == null ||member.getMember_Name().isEmpty() || member.getEmail().isEmpty() || String.valueOf(member.getMobile_No()).isEmpty() || member.getAddress().isEmpty()) {
			throw new IllegalArgumentException("Member fields cannot be empty");
        }
        
        if(!member.getMember_Name().matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$")) {
        	throw new IllegalArgumentException("Please enter a valid name!");
        }
       
        if (!member.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        	throw new IllegalArgumentException("Please enter a valid email address!");
        }
        
        if (!String.valueOf(member.getMobile_No()).matches("\\d{10}")) {
        	throw new IllegalArgumentException("Please enter a valid mobile number!");
        }
        
        if (member.getGender() != 'M' && member.getGender() != 'F') {
            throw new IllegalArgumentException("Invalid gender. Must be M or F");
        }
	}
}
