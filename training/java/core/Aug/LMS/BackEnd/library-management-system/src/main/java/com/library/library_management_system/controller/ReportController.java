package com.library.library_management_system.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library_management_system.domain.CustomCategoryCount;
import com.library.library_management_system.domain.CustomReportDetails;
import com.library.library_management_system.response.CustomResponse;
import com.library.library_management_system.service.ReportService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

	private final ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/bookCategoryCount")
	public ResponseEntity<CustomResponse<List<CustomCategoryCount>>> getBookCountByCategory() {

		List<CustomCategoryCount> bookCountCategory = reportService.getBookCountByCategory();
		CustomResponse<List<CustomCategoryCount>> response = new CustomResponse<>(true,
				"Book CategoryCount Retrived successfully!", bookCountCategory);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/activeIssuedBooks")
	public ResponseEntity<CustomResponse<List<CustomReportDetails>>> getActiveIssuedBooks() {

		List<CustomReportDetails> activeIssueBooks = reportService.getActiveIssuedBooks();
		CustomResponse<List<CustomReportDetails>> response = new CustomResponse<>(true,
				"Active Issue Books Retrived successfully!", activeIssueBooks);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/overDueBooks")
	public ResponseEntity<CustomResponse<List<CustomReportDetails>>> getoverDueBooks() {

		List<CustomReportDetails> overDueBooks = reportService.getOverDueBooks();
		CustomResponse<List<CustomReportDetails>> response = new CustomResponse<>(true,
				"Over Due Books Retrived successfully!", overDueBooks);
		return ResponseEntity.ok(response);

	}
}
