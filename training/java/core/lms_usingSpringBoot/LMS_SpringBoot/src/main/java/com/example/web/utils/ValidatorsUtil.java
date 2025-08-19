package com.example.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.web.dao.BookDao;
import com.example.web.dao.MemberDao;
import com.example.web.exceptions.DBConstrainsException;
import com.example.web.exceptions.IdNotExistException;
import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.Member;

@Component
public class ValidatorsUtil {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	
	public void validateBook(Book book) throws DBConstrainsException {
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
	
	
	public void validateIssueRecord(Issue_Records newRecord) throws IdNotExistException {
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
	
	public void validateMember(Member member) throws IllegalArgumentException {
		
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
        
        if (!(member.getGender().equals("M") || member.getGender().equals("F"))) {
            throw new IllegalArgumentException("Invalid gender. Must be M or F");
        }
	}
}
