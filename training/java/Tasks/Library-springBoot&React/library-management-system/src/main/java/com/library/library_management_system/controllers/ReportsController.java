package com.library.library_management_system.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.library_management_system.domain.IssueBookDetails;
import com.library.library_management_system.services.ReportsServiceInterface;

@RestController
@RequestMapping("/library/reports")
public class ReportsController {

	private final ReportsServiceInterface reportsService;

	public ReportsController(ReportsServiceInterface reportsService) {
		this.reportsService = reportsService;
	}

	@GetMapping("/categoryCount")
	public ResponseEntity<Map<Object, Long>> getCategoryCount() {
		return ResponseEntity.ok(reportsService.countOfBooksPerCategory());
	}

	@GetMapping("/overdueRecords")
	public ResponseEntity<List<IssueBookDetails>> getOverdueBooks() {
		return ResponseEntity.ok(reportsService.getOverdueBooks());
	}

	@GetMapping("/activeIssuedRecords")
	public ResponseEntity<List<IssueBookDetails>> getActiveIssuedBooks() {
		return ResponseEntity.ok(reportsService.getActiveIssuedBooks());
	}
}
