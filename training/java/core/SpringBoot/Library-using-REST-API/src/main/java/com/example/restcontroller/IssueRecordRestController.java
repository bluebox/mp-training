package com.example.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.IssueRecord;
import com.example.service.IssueBookService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/issueBook")
public class IssueRecordRestController {
	@Autowired
	private IssueBookService ibs;
	@GetMapping("/showIssue")
	public ArrayList<IssueRecord> showIssues(Model m) {
		return ibs.showIssues();
	}
	@PostMapping("/add")
	public String issueBook(@RequestBody IssueRecord i) {
		return ibs.issueBook(i);
	}
	@PutMapping("/return")
	public String returnBook(@RequestParam int issueId) {
		return ibs.returnBook(issueId);
	}
}
