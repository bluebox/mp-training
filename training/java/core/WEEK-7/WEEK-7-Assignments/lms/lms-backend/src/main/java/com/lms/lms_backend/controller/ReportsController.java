package com.lms.lms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lms_backend.model.CategoryCount;
import com.lms.lms_backend.model.ReportDetails;
import com.lms.lms_backend.service.ReportsService;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

	private final ReportsService reportsService;

	@Autowired
	public ReportsController(ReportsService reportsService) {
		this.reportsService = reportsService;
	}

	@GetMapping("/bookCategoryCount")
	public ResponseEntity<List<CategoryCount>> getBookCountByCategory() {

		List<CategoryCount> bookCountCategory = reportsService.getBookCountByCategory();
		return ResponseEntity.ok(bookCountCategory);

	}

	@GetMapping("/activeIssuedBooks")
	public ResponseEntity<List<ReportDetails>> getActiveIssuedBooks() {

		List<ReportDetails> activeIssuedBooks = reportsService.getActiveIssuedBooks();
		return ResponseEntity.ok(activeIssuedBooks);
	}

	@GetMapping("/overdueBooks")
	public ResponseEntity<List<ReportDetails>> getoverDueBooks() {

		List<ReportDetails> overdueBooks = reportsService.getOverdueBooks();
		return ResponseEntity.ok(overdueBooks);

	}
}
