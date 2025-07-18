package com.example.library.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.ApiResponse;
import com.example.library.model.IssueRecord;
import com.example.library.service.IssueService;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(
	    origins = "http://localhost:5173",
	    allowCredentials = "true"
	)
public class IssueRestcontroller {

	private final IssueService issueService;

	@Autowired
	public IssueRestcontroller(IssueService issueService) {
		this.issueService = issueService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<IssueRecord>>> issuedRecords() {
		try {
			List<IssueRecord> records = issueService.getAllIssuedRecords();
			return ResponseEntity.ok(new ApiResponse<>(true, "Issued records fetched", records));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to fetch issued records: " + e.getMessage(), null));
		}
	}

	@GetMapping("/overdue")
	public ResponseEntity<ApiResponse<List<IssueRecord>>> overdueRecords() {
		try {
			List<IssueRecord> overdue = issueService.getOverdueRecords();
			return ResponseEntity.ok(new ApiResponse<>(true, "Overdue records fetched", overdue));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to fetch overdue records: " + e.getMessage(), null));
		}
	}

	@PostMapping("/issue")
	public ResponseEntity<ApiResponse<IssueRecord>> issueBook(@RequestBody IssueRecord issueRecord) {
		try {
			issueRecord.setIssueDate(LocalDate.now());
			issueService.issueBook(issueRecord);
			return ResponseEntity.ok(new ApiResponse<>(true, "Book issued successfully", issueRecord));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to issue book: " + e.getMessage(), null));
		}
	}

	@PostMapping("/return")
	public ResponseEntity<ApiResponse<String>> returnBook(@RequestParam int issueId, @RequestParam int bookId,
			@RequestParam String returnDate) {
		try {
			issueService.returnBook(issueId, bookId, LocalDate.parse(returnDate));
			return ResponseEntity.ok(new ApiResponse<>(true, "Book returned successfully", null));
		} catch (Exception e) {
			return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(false, "Failed to return book: " + e.getMessage(), null));
		}
	}
}
