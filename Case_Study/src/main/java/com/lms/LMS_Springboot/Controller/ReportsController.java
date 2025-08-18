package com.lms.LMS_Springboot.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.BookService;
import com.lms.LMS_Springboot.Service.Reports;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/Reports")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class ReportsController {
	
	@Autowired 
	private Reports reports;
	@RequestMapping(value="/overduebooks",method=RequestMethod.GET)
	public ResponseEntity<List<Book>> overduecontroller() {
		
		
		System.out.println("reports");
		
			return ResponseEntity.ok(reports.overduebooks());
}
	@RequestMapping(value="/countpercategory",method=RequestMethod.GET)
	public ResponseEntity<Map<String, Long>> countpercategorycontroller() {
		
		
		System.out.println("reports");
		
			return ResponseEntity.ok(reports.count_of_books_percategory());
}
	
	@RequestMapping(value="/isuuemembers",method=RequestMethod.GET)
	public ResponseEntity<List<Member>> addbookcontroller() {
		
		
		System.out.println("reports");
		
			return ResponseEntity.ok(reports.members_with_statusissue());
}
	
}