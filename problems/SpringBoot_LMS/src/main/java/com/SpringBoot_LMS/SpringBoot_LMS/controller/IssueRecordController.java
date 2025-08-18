package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.service.IssueRecordservice;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/Issues")
public class IssueRecordController {

	@Autowired
	private IssueRecordservice service;
	
	@GetMapping("/viewIssues")
	public ResponseEntity<List<IssueRecord>> viewIssues() throws IOException, SQLException {
		    List<IssueRecord> issues=service.getAllIssueRecords();
		    if(issues==null) {
		    	return ResponseEntity.badRequest().body(issues);
		    }
	        return ResponseEntity.ok().body(issues);
	}
	
	@GetMapping("/getIssue")
	public ResponseEntity<String> getissue(@RequestParam(name = "bookid", required = true) int bookid,@RequestParam(name = "memberid", required = true) int memberid) throws IOException, SQLException {
         IssueRecord issue=service.getIssueRecord(bookid, memberid);
         System.out.println(issue.toString());
         if(issue !=null) {
        	 return ResponseEntity.ok().body(issue.toString());
         }
         return ResponseEntity.badRequest().body("Error while fetching the book");
}
	
	
	@RequestMapping(value="/issue",method=RequestMethod.POST)
	public ResponseEntity<String> createissue(@Valid @RequestBody IssueRecord issue) throws SQLException{
		System.out.println(issue);
		int value=service.createBookIssue(issue);
		if(value>0) {
			return ResponseEntity.ok().body("book issued and added to DB successfully");	
		}
		return ResponseEntity.badRequest().body("Error while adding the book issue");
	}
	
	@RequestMapping(value="/returnissue",method=RequestMethod.POST)
	public ResponseEntity<String> returnissue(@RequestParam(name = "bookid", required = true) int bookid,@RequestParam(name = "memberid", required = true) int memberid) throws SQLException{
		System.out.println(bookid+memberid);
		int value=service.returnBook(bookid,memberid);
		if(value!=0) {
			return ResponseEntity.ok().body("book returned and updated to DB successfully");	
		}
		return ResponseEntity.badRequest().body("Error while updating the book and return process");
	}
	
	
	
	
}
