package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.IssueRecord;
import com.example.library.service.IssueService;

import java.time.LocalDate;

@Controller
@RequestMapping("/issues")
public class IssueController {
	private final IssueService issueService;

	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@GetMapping("")
	public String issuedRecords(Model model) throws Exception {
		model.addAttribute("issuedRecords", issueService.getAllIssuedRecords());
		return "issues/issuedRecords";
	}

	@GetMapping("/issue")
	public String issueBookForm(Model model) {
		model.addAttribute("issueRecord", new IssueRecord(null, null, null, LocalDate.now()));
		return "issues/issueBook";
	}

	@PostMapping("/issue")
	public String issueBook(@ModelAttribute IssueRecord issueRecord, Model model) throws Exception {
		issueService.issueBook(issueRecord);
		return "redirect:/issues";
	}

	@GetMapping("/return")
	public String returnBookForm(Model model) {
		model.addAttribute("issueRecord", new IssueRecord(null, null, null, LocalDate.now()));
		return "issues/returnBook";
	}

	@PostMapping("/return")
	public String returnBook(@RequestParam int issueId, @RequestParam int bookId, @RequestParam String returnDate,
			Model model) throws Exception {
		issueService.returnBook(issueId, bookId, LocalDate.parse(returnDate));
		return "redirect:/issues";
	}

	@GetMapping("/overdue")
	public String overdueRecords(Model model) throws Exception {
		model.addAttribute("overdueRecords", issueService.getOverdueRecords());
		return "issues/overdueRecords";
	}

}