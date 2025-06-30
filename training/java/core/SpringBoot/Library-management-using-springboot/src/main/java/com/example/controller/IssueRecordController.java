package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.IssueRecord;
import com.example.service.IssueBookService;

@Controller
public class IssueRecordController {
	@Autowired
	private IssueBookService ibs;
	Logger log=LoggerFactory.getLogger(IssueRecordController.class.getName());
	@RequestMapping("/issue")
	public String issue(Model m) {
		m.addAttribute("issue", new IssueRecord());
		return "issue.html";
	}
	@RequestMapping("/issueBook")
	public String issueBook(Model m,@RequestParam int issueId,@RequestParam long bookId,@RequestParam int memberId,@RequestParam char statusrec){
		String s=ibs.issueBook(new IssueRecord(issueId, bookId, memberId, statusrec));
		if(s.equals("Book issued")) {
			return showIssues(m);
		}
		else {
			m.addAttribute("error", s);
			log.error(s);
			return issue(m);
		}
	}
	@RequestMapping("/showIssue")
	private String showIssues(Model m) {
		m.addAttribute("l", ibs.showIssues());
		return "issueBook.html";
	}
	@RequestMapping("/returnIssues")
	public String returnIssues(Model m,@RequestParam int issueId) {
		String s=ibs.returnBook(issueId);
		if(s.equals("Book returned")) {
			m.addAttribute("l", ibs.showIssues());
		    return "issueBook.html";
		}
		else {
			m.addAttribute("error", s);
			return "issueBook.html"; 
		}
	}
}
