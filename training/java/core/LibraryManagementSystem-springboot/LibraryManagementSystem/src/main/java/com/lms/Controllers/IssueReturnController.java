package com.lms.Controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;
import com.lms.Services.Implementation.IssueRecordServiceImplementation;
import com.lms.Services.Implementation.MemberServiceImplementation;



@RestController
@RequestMapping("/issueReturn")
@CrossOrigin("http://localhost:5173/")

public class IssueReturnController {

	public  IssueRecordServiceImplementation issueReturnService;
	
	

	@Autowired
	public IssueReturnController(IssueRecordServiceImplementation issueReturnService) {
		this.issueReturnService = issueReturnService;
	}
//	public  MemberServiceImplementation msi;
//	@Autowired
//	public IssueReturnController(MemberServiceImplementation msi) {
//		super();
//		this.msi = msi;
//	}

	@GetMapping
	public ResponseEntity<List<IssueRecords>> all() {
		return new ResponseEntity<>(issueReturnService.getAllIssues(), HttpStatus.OK);
	}

	@PostMapping("/issue")
	public boolean issueBook(@RequestBody IssueRecords record) {
	    boolean result = issueReturnService.issueBook(record);
	    return result; 
	}

	@PostMapping("/return")
	public boolean returnBook(@RequestBody IssueRecords payload) {
	    int bookId=payload.getIssueId();

	   

	    try {
	        //int bookId = Integer.parseInt(bookIdStr);
	        issueReturnService.returnBook(bookId); 
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<IssueRecords> one(@PathVariable int id) {
//		return issueReturnService.getActiveIssueByBookId(id).map(record -> new ResponseEntity<>(record, HttpStatus.OK))
//				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}

	@GetMapping("/members")
	public ResponseEntity<List<Member>> getallMembers() {
		return new ResponseEntity<>(issueReturnService.getValidMemberIds(), HttpStatus.OK);
	}

	@GetMapping("/issued")
	public ResponseEntity<List<IssueRecords>> issuedBooks() {
		return new ResponseEntity<>(issueReturnService.getAllIssues(), HttpStatus.OK);
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> allBooks() {
		return new ResponseEntity<>(issueReturnService.getAvailabeBooks(), HttpStatus.OK);
	}
}