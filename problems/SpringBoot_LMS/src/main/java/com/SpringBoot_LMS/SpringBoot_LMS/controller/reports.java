package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;
import com.SpringBoot_LMS.SpringBoot_LMS.service.BookService;
import com.SpringBoot_LMS.SpringBoot_LMS.service.IssueRecordservice;
import com.SpringBoot_LMS.SpringBoot_LMS.service.MemberService;

@RestController
@RequestMapping("/Reports")
public class reports {
        
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private IssueRecordservice issuerecordservice;
	
	@Autowired
	private MemberService memberservice;
	
	
	@GetMapping("/overduebooks")
	public ResponseEntity<List<Book>> overduebooks() throws IOException, SQLException{
		
		List<Book> books=bookservice.getBooks();
		List<IssueRecord> issues=issuerecordservice.getAllIssueRecords();
		LocalDate today=LocalDate.now();
		
		List<Integer> overdues=issues.stream()
				 .filter(issue -> issue.getIssueDate().plusDays(15).isAfter(today))
				    .map(issue -> issue.getBookId())
				    .collect(Collectors.toList());
		
		
		List<Book> overdueBooks = books.stream()
			    .filter(book -> overdues.contains(book.getBookId()))
			    .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(overdueBooks);
	}
	
	
	@GetMapping("/BooksperCategory")
	public ResponseEntity<Map<String,Long>> bookspercategory() throws IOException, SQLException{
		List<Book> books=bookservice.getBooks();
		Map<String, Long> map = books.stream()
			         .collect(Collectors.groupingBy(
			             Book::getCategory,
			            Collectors.counting()
			    ));   
		
		return ResponseEntity.ok().body(map);
	}
	
	
	@GetMapping("/ActiveMemberswithbooks")
	public ResponseEntity<List<Member>> activemembers() throws Exception{
		List<Member> members=memberservice.getMembers();
		
		List<IssueRecord> issues=issuerecordservice.getAllIssueRecords();
		
		List<Integer> issueMemberIds = issues.stream()
			    .filter(issue -> "I".equalsIgnoreCase(issue.getStatus().getType()))
			    .map(IssueRecord::getMemberId)
			    .collect(Collectors.toList());
		
		List<Member> activeMembers = members.stream()
			    .filter(member -> issueMemberIds.contains(member.getMemberId()))
			    .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(activeMembers);
	
	}
	
	
}
