package com.example.Backend.controllersImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.constants.BookCategory;
import com.example.Backend.domain.IssueRecord;
import com.example.Backend.domain.Member;
import com.example.Backend.serviceImplementation.ReportsServiceImplementation;

@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private ReportsServiceImplementation reportService;

	@GetMapping("/overdue")
	public List<IssueRecord> getOverdueBooks(@RequestParam(defaultValue = "14") int days) {
		return reportService.getOverdueBooks(days);
	}

	@GetMapping("/book-count")
	public Map<BookCategory, Long> getBookCountPerCategory() {
		return reportService.getBookCountPerCategory();
	}

	@GetMapping("/active-members")
	public List<Member> getMembersWithActiveIssuedBooks() {
		return reportService.getMembersWithActiveIssuedBooks();
	}

}
