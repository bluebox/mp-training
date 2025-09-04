package com.example.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.Member;
import com.example.web.model.OverDueList;
import com.example.web.model.ReportMember;
import com.example.web.service.BookService;
import com.example.web.service.IssueLogService;
import com.example.web.service.MemberService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("*")
public class IssueRecordsController {
	@Autowired
	IssueLogService issueLogService;
	@Autowired 
	BookService bookService;
	@Autowired
	MemberService memberService;
	
	@GetMapping("/getIssuedrecords")
	public ResponseEntity<List<Issue_Records>> getRecords(){
		List<Issue_Records> issue_records = issueLogService.getAllIssuedRecords();
		return ResponseEntity.ok(issue_records);
	}
	
	@PostMapping("/issuebook")
	public ResponseEntity<String> issueBook(@RequestBody Issue_Records issue_Record) {
		Issue_Records records = new Issue_Records(issue_Record.getBookId(),issue_Record.getMemberId());
		issueLogService.addIssueRecord(records);
		return ResponseEntity.ok("Book Issued");
	}
	
	@GetMapping("/memberstats")
	public ResponseEntity<List<ReportMember>> getMemberStats(){
		List<ReportMember> memberStats = issueLogService.booksOfMemberReport();
		return ResponseEntity.ok(memberStats);
	}
	
	@GetMapping("/overduelist")
	public ResponseEntity<List<OverDueList>> getoverdueBooks(){
		List<OverDueList> overdueBooks = issueLogService.getOverDueBooks();
		return ResponseEntity.ok(overdueBooks);
	}
	
	@PostMapping("/getMemberBooks")
	public ResponseEntity<List<String>> memberBooks(@RequestBody Member member) {
		int memberId=member.getMember_Id();
		List<String> books= issueLogService.getAllIssuedRecords().stream()
				.filter(r->{
					return r.getMemberId() == memberId && r.getStatus().equals("I");
				}).map(r->{
					return r.getIssueId()+"-"+r.getBookId()+" - " +bookService.getBookById(r.getBookId()).getBook_Title();
				})
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(books);
	}
	
	@PostMapping("/returnBook")
	public ResponseEntity<String> returnBook(@RequestBody Issue_Records issue_Record) {
		issueLogService.returnIssuedBook(issue_Record.getIssueId(), true);
		return ResponseEntity.ok("Book Returned Successfully");
	}
	
	@PostMapping("/memberBooks")
	public ResponseEntity<List<Book>> getmemberBooks(@RequestBody Member member){
		List<Book> books = issueLogService.booksOfMember(member.getMember_Id());
		return ResponseEntity.ok(books);
	}
	
	@PostMapping("/bookMembers")
	private ResponseEntity<List<Member>> getbookMembers(@RequestBody Book book) {
		List<Member> members = issueLogService.getAllIssuedRecords().stream().filter(record -> record.getBookId()==book.getBook_Id())
				 .map(record -> memberService.getMemberById(record.getMemberId())).distinct().collect(Collectors.toList());
		return ResponseEntity.ok(members);
	}
	
	
}
