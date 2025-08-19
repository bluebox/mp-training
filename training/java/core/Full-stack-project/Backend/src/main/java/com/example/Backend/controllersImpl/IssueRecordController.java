package com.example.Backend.controllersImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend.domain.Book;
import com.example.Backend.domain.IssueRecord;
import com.example.Backend.serviceImplementation.IssueRecordServiceImplementation;

@RestController
@RequestMapping("/issues")
public class IssueRecordController {

	@Autowired
	private IssueRecordServiceImplementation issueRecordService;

	@PostMapping("/issue")
	public ResponseEntity<?> issueBook(@RequestBody IssueRecord issueRecord) {

		int memberId = issueRecord.getMemberId();
		int bookId = issueRecord.getBookId();
		issueRecordService.issueBook(bookId, memberId);
		return ResponseEntity.ok(Map.of("status", "success", "message", "book added successfully"));

	}

	@GetMapping("/available-books")
	public List<Book> getAvailableBooks() {
		return issueRecordService.getAvailableBooks();
	}

	@GetMapping("/issued-books/{memberId}")
	public List<Book> getIssuedBooksForMember(@PathVariable int memberId) {
		return issueRecordService.getIssuedBooksForMember(memberId);
	}

	@PostMapping("/return")
	public ResponseEntity<?> returnBook(@RequestBody IssueRecord issueRecord) {

		int memberId = issueRecord.getMemberId();
		int bookId = issueRecord.getBookId();
		issueRecordService.returnBook(memberId, bookId);
		return ResponseEntity.ok(Map.of("status", "success", "message", "book added successfully"));
	}

	@GetMapping
	public List<IssueRecord> getAllIssues() {
		return issueRecordService.getAllIssues();
	}

}
