package com.library.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.library.app.model.Book;
import com.library.app.model.IssueRecord;
import com.library.app.service.LibraryService;

import javax.validation.Valid;
@Controller
@RequestMapping("/issue")
public class IssueController {
	@Autowired
	private LibraryService libraryService;
	
//	@RequestMapping(value="/issueBook",method=RequestMethod.GET)
	@GetMapping("/issueBook")
	public String issueBookPage(Model model) {
		model.addAttribute("issueRecord",new IssueRecord());
		return "issueBook";
	}
	
	
//	@RequestMapping(value="/issueBook", method=RequestMethod.POST)
	@PostMapping("/issueBook")
	public String issueBookPage(@Valid @ModelAttribute("issueRecord") IssueRecord issueRecord, Model model) {
	    try {
	        libraryService.issueBook(issueRecord.getBookId(), issueRecord.getMemberId());
	        model.addAttribute("success", "Book issued successfully.");
	        model.addAttribute("issueRecord", new IssueRecord());
	    } 
	    catch (Exception e) {
	        model.addAttribute("error", "Failed to issue book: " + e.getMessage());
	    }
	    return "issueBook";
	}


	
//	@RequestMapping(value="/returnBook", method=RequestMethod.GET)
	@GetMapping("/returnBook")
	public String returnBookPage(Model model) {
	    model.addAttribute("issueRecord", new IssueRecord());
	    return "returnBook";  
	}

//	@RequestMapping(value="/returnBook", method=RequestMethod.POST)
	@PostMapping("/returnBook")
	public String returnBookPage(@Valid @ModelAttribute("issueRecord") IssueRecord issueRecord, Model model) {
	    try {
	        libraryService.returnBook(issueRecord.getIssueId());
	        model.addAttribute("success", "Book returned successfully.");
	        model.addAttribute("issueRecord", new IssueRecord());
	    } catch (Exception e) {
	        model.addAttribute("error", "Failed to return book: " + e.getMessage());
	    }
	    return "returnBook";  
	}

	
//	@RequestMapping(value="/issuedRecords", method=RequestMethod.GET)
	@GetMapping("/issuedRecords")
	public String issuedRecordsPage(Model model) {
	    try {
	        List<IssueRecord> issuedRecords = libraryService.viewIssuedRecords();  
	        model.addAttribute("issuedRecords",issuedRecords);               
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "No records found!");
	    }
	    return "issuedRecords";
	}

}
