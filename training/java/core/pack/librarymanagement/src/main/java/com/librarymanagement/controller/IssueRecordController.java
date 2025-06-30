package com.librarymanagement.controller;

import com.librarymanagement.exceptions.NoBookException;
import com.librarymanagement.exceptions.NoMemberException;
import com.librarymanagement.exceptions.IssuedBookException;

import com.librarymanagement.model.Book;
import com.librarymanagement.model.IssueRecord;
import com.librarymanagement.model.Member;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.IssueRecordService;
import com.librarymanagement.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issueRecordService;
    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;
   
    @PostMapping("/issue")
    public String issueBook(
            @RequestParam("bookId") int bookId,
            @RequestParam("memberId") int memberId) throws Exception {
    	Book book=bookService.getBookById(bookId);
    	
    	
    	if(book== null)
    		throw new NoBookException("Book not found");
    	if(book.getAvailability()=='I')
    		throw new IssuedBookException("Book is already issued");
    	Member member=memberService.getMemberById(memberId);
    	if(member==null) 
    		throw new NoMemberException("No member found");
    	
        issueRecordService.issueBook(bookId, memberId);
        return "Book issued successfully.";
    }

   
    @PostMapping("/return")
    public String returnBook(
            @RequestParam("bookId") int bookId,
            @RequestParam("memberId") int memberId) throws Exception {
    	Book book=bookService.getBookById(bookId);
    	if(book== null) 
    		throw new NoBookException("Book not found");
    	if(book.getAvailability()=='A') 
    	throw new IssuedBookException("Book is not issued to anyone currently");
    	Member member=memberService.getMemberById(memberId);
    	if(member==null) 
    		throw new NoMemberException("No member found");
    	
        issueRecordService.returnBook(bookId, memberId);
        return "Book returned successfully.";
    }

    
    @GetMapping
    public List<IssueRecord> getAllIssueRecords() throws Exception {
        return issueRecordService.getAllIssueRecords();
    }

  
    @GetMapping("/active/{memberId}")
    public List<IssueRecord> getActiveIssuesByMember(
            @PathVariable("memberId") int memberId) throws Exception {
        return issueRecordService.getActiveIssuesByMember(memberId);
    }


    @GetMapping("/overdue")
    public List<IssueRecord> getOverdueBooks() throws Exception {
        return issueRecordService.getOverdueBooks();
    }
}
