package com.lms.App;


import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.lms.Exceptions.MemberDaoException;
import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;
import com.lms.Repository.BookRepo;
import com.lms.Repository.IssueBookRepo;
import com.lms.Services.Interfaces.IssueRecordService;
import com.lms.Services.Interfaces.MemberService;



@SpringBootApplication(scanBasePackages = "com.lms")
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		 ApplicationContext context =SpringApplication.run(LibraryManagementSystemApplication.class, args);
			
//			 
//			IssueRecordService  issueService = context.getBean(IssueRecordService.class);
//			  
//			 IssueRecords record = new
//			  IssueRecords(); record.setBookId(2); 
//			  record.setMemberId(1); 
//			  record.setIssueDate(LocalDate.now());
			  
//			try { boolean result = issueService.issueBook(record);
//			  System.out.println("Issue Result: " + result); } catch (Exception e) {
//			 System.err.println("Error issuing book: " + e.getMessage());
//			 e.printStackTrace(); }
			 
//		 BookRepo bookRepo = context.getBean(BookRepo.class);
//
//	        // Create dummy book
//	        Book book = new Book();
//	        book.setTitle("Test Book");
//	        book.setAuthor("Jane Doe");
//	        book.setCategory("Fiction");
//
//	        try {
//	            int bookId = bookRepo.addBook(book);
//	            System.out.println("Book inserted with ID: " + bookId);
//	        } catch (Exception e) {
//	            System.err.println(" Failed to insert book: " + e.getMessage());
//	            e.printStackTrace();
//	        }
	}

}
