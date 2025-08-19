package com.library.library_management_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.domain.Issue;
import com.library.library_management_system.domain.Return;
import com.library.library_management_system.response.CustomResponse;
import com.library.library_management_system.service.IssueService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/issues")
public class IssueController {

	private final IssueService issueService;

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@PostMapping(value = "/issue")
	public ResponseEntity<CustomResponse<Issue>> issueBook(@Valid @RequestBody Issue issue) {

		issueService.addIssue(issue);
		CustomResponse<Issue> response = new CustomResponse<>(true, "Issue Added successfully!", issue);
		return ResponseEntity.ok(response);

	}

	@PostMapping(value = "/return")
	public ResponseEntity<CustomResponse<String>> issueBook(@Valid @RequestBody Return returnIssue) {

		issueService.returnBook(returnIssue.getBookId(), returnIssue.getMemberId(), returnIssue.getReturnDate());
		CustomResponse<String> response = new CustomResponse<>(true, "Return successfully!", null);
		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/getIssues")
	public ResponseEntity<CustomResponse<List<Issue>>> getIssues() {

		issueService.getIssues();
		CustomResponse<List<Issue>> response = new CustomResponse<>(true, "Issues Retrived successfully!", null);
		return ResponseEntity.ok(response);

	}
}
