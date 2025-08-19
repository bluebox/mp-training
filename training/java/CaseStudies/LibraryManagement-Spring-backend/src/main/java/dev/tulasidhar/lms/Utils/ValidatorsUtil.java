package dev.tulasidhar.lms.Utils;

import dev.tulasidhar.lms.DAO.impl.MemberDaoImpl;
import dev.tulasidhar.lms.Exceptions.DBConstrainsException;
import dev.tulasidhar.lms.Exceptions.IdNotExistException;
import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.DAO.impl.DataBookDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.tulasidhar.lms.DAO.BookDao;
import dev.tulasidhar.lms.DAO.IssueRecordDao;
import dev.tulasidhar.lms.DAO.MemberDao;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.model.Issue_Records;
import dev.tulasidhar.lms.model.Member;

@Component
public class ValidatorsUtil {
	
	@Autowired
	private  BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private IssueRecordDao issueRecordDao;

	
	public static void validateBook(Book book) throws ValidationException {
		String bookTitle=book.getBook_Title().trim();
		String bookAuthor=book.getBook_Author().trim();
		String bookCategory=book.getBook_Category().trim();
		char bookStatus=book.getBook_Status();
		char bookAvailability=book.getBook_Availability();
		
		if(bookTitle == null || bookAuthor == null || bookCategory == null || bookStatus == '\u0000' || bookAvailability == '\u0000' ||  bookTitle.isEmpty() || bookAuthor.isEmpty() || bookCategory.isEmpty() ) {
			throw new ValidationException("Book fields cannot be empty");
		}
		if(bookTitle.length()>255 || (!bookTitle.matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$"))) {
			throw new ValidationException("Please enter a valid Title!");
		}
		if(bookAuthor.length()>255 || (!bookAuthor.matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$"))) {
			throw new ValidationException("Please enter a valid Author Name!");
		}
		if(bookCategory.length()>100 || (!bookCategory.matches("^[A-Za-z]{2}[A-Za-z0-9\\s-]{0,98}$"))) {
			throw new ValidationException("Please enter a valid Category Name!");
		}
		if(bookStatus!='A' && bookStatus!='I') {
			throw new ValidationException("Entered An Invalid Book Status");
		}
		if(bookAvailability!='A' && bookAvailability!='I') {
			throw new ValidationException("Entered An Invalid Book Availability");
		}
	}
	
	
	public void validateIssueRecord(Issue_Records newRecord) throws IdNotExistException {
		if(bookDao.getBookById(newRecord.getBookId())==null) {
			throw new IdNotExistException("Entered BookId Is Invalid");
		}
		if(memberDao.getMemberById(newRecord.getMemberId())==null) {
			throw new IdNotExistException("Entered MemberId Is Invalid");
		}

		Book book=bookDao.getBookById(newRecord.getBookId());
		char status=book.getBook_Status();
		char available=book.getBook_Availability();
		if((String.valueOf(status).equals("I")) || String.valueOf(available).equals("I")){
			throw new IllegalArgumentException("This book is already issued or not avaliable");
		}
		
		if(newRecord.getBookId() < 0) {
			throw new IdNotExistException("Entered id(s) are not valid");

		}
	}
	
	public void validateMember(Member member) throws ValidationException {
		
		if (member.getMember_Name() == null || member.getEmail() == null || member.getAddress() == null || member.getMobile_No() == null ||member.getMember_Name().isEmpty() || member.getEmail().isEmpty() || String.valueOf(member.getMobile_No()).isEmpty() || member.getAddress().isEmpty()) {
			throw new ValidationException("Member fields cannot be empty");
        }
        
        if(!member.getMember_Name().matches("^[A-Za-z]{2}[A-Za-z0-9\\s]{0,253}$")) {
        	throw new ValidationException("Please enter a valid name!");
        }
       
        if (!member.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        	throw new ValidationException("Please enter a valid email address!");
        }
        
        if (!String.valueOf(member.getMobile_No()).matches("\\d{10}")) {
        	throw new ValidationException("Please enter a valid mobile number!");
        }
        
        if (member.getGender() != 'M' && member.getGender() != 'F') {
            throw new ValidationException("Invalid gender. Must be M or F");
        }
	}
}
