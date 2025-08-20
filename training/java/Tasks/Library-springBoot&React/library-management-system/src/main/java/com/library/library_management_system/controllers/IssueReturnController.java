package com.library.library_management_system.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.domain.IssueRecord;
import com.library.library_management_system.services.BookServiceInterface;
import com.library.library_management_system.services.IssueRecordServiceInterface;
import com.library.library_management_system.services.MemberServiceInterface;

@RestController
@RequestMapping("/library/issues")
public class IssueReturnController {

	private final IssueRecordServiceInterface issueService;

	public IssueReturnController(IssueRecordServiceInterface issueService, MemberServiceInterface memberService,
			BookServiceInterface bookService) {
		this.issueService = issueService;
	}

	@GetMapping("/allIssues")
	public ResponseEntity<List<IssueRecord>> getAllIssues() {
		List<IssueRecord> issues = issueService.getAllIssues();
		return ResponseEntity.ok(issues);
	}

	@PostMapping("/issue")
	public ResponseEntity<IssueRecord> issueBook(@RequestBody IssueRecord issue) {
		return ResponseEntity.ok(issueService.issueBook(issue));
	}


	@PostMapping("/return")
	public ResponseEntity<IssueRecord> returnBook(@RequestBody IssueRecord issue) {
		return ResponseEntity.ok(issueService.returnBook(issue.getMemberId(), issue.getBookId()));
	}
}
