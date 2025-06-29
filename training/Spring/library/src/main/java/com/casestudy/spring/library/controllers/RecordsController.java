package com.casestudy.spring.library.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.casestudy.spring.library.beans.IssueRecord;
import com.casestudy.spring.library.impl.Implementation;

@Controller
public class RecordsController {

	@Autowired
	Implementation impl;

	@GetMapping("/OverDueBooks")
	public String overDueBooks(Model model) {
		List<IssueRecord> issueRecords = impl.getOverdueBooks();
		model.addAttribute("issueRecords", issueRecords);
		model.addAttribute("heading", "Over Due Books");
		return "IssueRecords";
	}

	@GetMapping("/ActiveIssuedBooks")
	public String activeIssuedBooks(Model model) {
		List<IssueRecord> issueRecords = impl.getActiveIssuedBooksSerivce();
		model.addAttribute("issueRecords", issueRecords);
		model.addAttribute("heading", "Active Issued Books");
		return "IssueRecords";
	}

	@GetMapping("/BooksPerCategory")
	public String booksPerCategory(Model model) {
		Map<String, Long> booksPerCategory = impl.getBooksCountPerCategory();
		model.addAttribute("categoryCount", booksPerCategory);
		return "CountOfBooksPerCategory";
	}

}
