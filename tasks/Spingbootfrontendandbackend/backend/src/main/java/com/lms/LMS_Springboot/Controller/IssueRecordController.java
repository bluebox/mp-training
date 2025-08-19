package com.lms.LMS_Springboot.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lms.LMS_Springboot.DAO.Issue_RecordDAO;
import com.lms.LMS_Springboot.Model.*;
import com.lms.LMS_Springboot.Service.IssueRecordService;
import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/issue")
@RestController
public class IssueRecordController {
	@GetMapping("/")
	public String greet(HttpServletRequest req) {
		return "welcome to home";
	}
	@Autowired
	IssueRecordService issue;
	@PostMapping("/issuethebook/{bookId}/{memberId}")
	public ResponseEntity<String> issuethebook1(@PathVariable int bookId, @PathVariable int memberId){
	if(issue.issueBook(bookId, memberId)) {
        return ResponseEntity.ok("book issued successfully!");
	}
	else {
		return ResponseEntity.ok("book failedd to issue");
	}		
	}	
	@GetMapping("/printAllIssueRecords")
	public ResponseEntity<List<Issue_records>> getprintAllIssueRecords() throws SQLException {
		return ResponseEntity.ok(issue.getAllIssueRecords());	
	}
	@PostMapping("/returnbook/{bookId}/{memberId}")
	public ResponseEntity<String> returnbook(@PathVariable int bookId, @PathVariable int memberId){
		if(issue.returnBook(bookId, memberId)) {
	        return ResponseEntity.ok("book returned successfully!");
		}
		else {
			return ResponseEntity.ok("book failedd to return");
		}
		}
}