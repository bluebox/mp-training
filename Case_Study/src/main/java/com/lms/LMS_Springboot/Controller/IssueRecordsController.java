package com.lms.LMS_Springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Issue_records;
import com.lms.LMS_Springboot.Service.IssueRecordsService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/issuerecords")
@ResponseBody
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class IssueRecordsController {
	
	@Autowired
	private IssueRecordsService issuerecordsservice;
	
	@RequestMapping(value="/issuebook",method=RequestMethod.POST)
	@ResponseBody

	public ResponseEntity<String> addbookcontroller(@RequestBody ObjectNode json) {
		int bookid=json.get("bookid").asInt();
		int memberid=json.get("memberid").asInt();

		
		System.out.println("issuebook"+bookid+memberid);
		if(issuerecordsservice.issuebook( bookid, memberid) ){
			return ResponseEntity.ok("Book issued sucessfully");
		}
		else {
		
		return ResponseEntity.badRequest().body("book not issued");
		}
	}
	
	@RequestMapping(value="/returnbook",method=RequestMethod.POST)
	@ResponseBody

	public ResponseEntity<String> returnbookcontroller(@RequestBody ObjectNode json) {
		int bookid=json.get("bookid").asInt();
		int memberid=json.get("memberid").asInt();

		
		System.out.println("returnbook");
		if(issuerecordsservice.returnbook( bookid, memberid) ){
			return ResponseEntity.ok("Book returned sucessfully");
		}
		else {
		
		return ResponseEntity.badRequest().body(" error book not returned ");
		}
	}
	
	@RequestMapping(value="/viewallissuerecords",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Issue_records>> viewallissuerecordscontroller() {
		return  ResponseEntity.ok(issuerecordsservice.viewallissuerecords());
		//return ""+bookdao.viewallBooks();
	}

}
