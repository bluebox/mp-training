package com.LMS.LibMS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.service.interfaces.IssueService;

@Controller
public class IssueController {
	
	private IssueService IssueService;
	
	@Autowired
	public IssueController(IssueService IssueService) {
		this.IssueService=IssueService;
	}
	
	@GetMapping("/issuedbooks")
	@ResponseBody
	public List<IssueRecord> issuedBooks(){
		List<IssueRecord> issuedBooks = IssueService.findIssuedBooks();
		return issuedBooks;
	}
	
	

}
