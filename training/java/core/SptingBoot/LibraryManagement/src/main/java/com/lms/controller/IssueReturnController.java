package com.lms.controller;

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

import com.lms.model.Book;
import com.lms.model.IssueRecords;
import com.lms.model.Member;
import com.lms.service.interfaces.implementation.IssueReturnServiceImplementation;

@RestController
@RequestMapping("/issueReturn")
@CrossOrigin("http://localhost:5173/")

public class IssueReturnController {

	public final IssueReturnServiceImplementation issueReturnService;

	@Autowired
	public IssueReturnController(IssueReturnServiceImplementation issueReturnService) {
		this.issueReturnService = issueReturnService;
	}

	@GetMapping
	public ResponseEntity<List<IssueRecords>> all() {
		return new ResponseEntity<>(issueReturnService.getAllIssues(), HttpStatus.OK);
	}

	@PostMapping("/issue")
	public ResponseEntity<IssueRecords> issueBook(@RequestBody IssueRecords record) {
		return new ResponseEntity<>(issueReturnService.issueBook(record), HttpStatus.CREATED);
	}

	@PostMapping("/return")
	public ResponseEntity<IssueRecords> returnBook(@RequestBody Map<String, String> payload) {
		String bookId = payload.get("bookId"); 
		if (bookId == null || bookId.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		IssueRecords record = issueReturnService.returnBookByBookId(bookId);
		return ResponseEntity.ok(record);
	}

	@GetMapping("/{id}")
	public ResponseEntity<IssueRecords> one(@PathVariable int id) {
		return issueReturnService.getActiveIssueByBookId(id).map(record -> new ResponseEntity<>(record, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/members")
	public ResponseEntity<List<Member>> allMembers() {
		return new ResponseEntity<>(issueReturnService.getValidMemberIds(), HttpStatus.OK);
	}

	@GetMapping("/issued")
	public ResponseEntity<List<Book>> issuedBooks() {
		return new ResponseEntity<>(issueReturnService.getIssuedBooks(), HttpStatus.OK);
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> allBooks() {
		return new ResponseEntity<>(issueReturnService.getAvailabeBooks(), HttpStatus.OK);
	}
}
