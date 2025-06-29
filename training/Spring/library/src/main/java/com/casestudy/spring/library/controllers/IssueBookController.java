package com.casestudy.spring.library.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.casestudy.spring.library.beans.IssueRecord;
import com.casestudy.spring.library.impl.Implementation;

@Controller
public class IssueBookController {
	@Autowired
	Implementation impl;

	@GetMapping("/IssueBook")
	public String issueBookForm(Model model) {
		model.addAttribute("issueRecord", new IssueRecord());
		return "IssueBook";
	}

	@PostMapping("/SaveIssueRecord")
	public String issueBook(@ModelAttribute IssueRecord issueRecord, Model model) {
		issueRecord.setIssueDate(LocalDate.now());
		boolean flag = impl.issueBookService(issueRecord);
		if (flag == true) {
			model.addAttribute("message", "Book Issued successfully!");
			model.addAttribute("targetUrl", "/IssueBook");
			model.addAttribute("buttonLabel", "Go to IssueRecord");
			return "Response";
		} else {
			model.addAttribute("message", "Cannot Issue Book!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}

	}
	
	@GetMapping("/ReturnBook")
	public String returnBookForm(Model model) {
		model.addAttribute("issueRecord", new IssueRecord());
		return "ReturnBook";
	}

	@PostMapping("/SaveRetrunBook")
	public String returnBook(@ModelAttribute IssueRecord issueRecord, Model model) {
		issueRecord.setReturnDate(LocalDate.now());
		boolean flag = impl.returnBookService(issueRecord);
		if (flag == true) {
			model.addAttribute("message", "Book Returned successfully!");
			model.addAttribute("targetUrl", "/ReturnBook");
			model.addAttribute("buttonLabel", "Go to ReturnBook");
			return "Response";
		} else {
			model.addAttribute("message", "Cannot Return Book!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}

	}
	
	 @GetMapping("/ViewAllRecords")
	    public String viewAllIssueRecords(Model model) {
	        List<IssueRecord> issueRecords = impl.getAllIssuedRecordsService();
	        model.addAttribute("issueRecords", issueRecords);
	        model.addAttribute("heading", "All Issued Books");
	        return "IssueRecords"; 
	    }
}
