package dev.kaushik.library.controller;

import dev.kaushik.library.model.IssueRecord;
import dev.kaushik.library.model.Member;
import dev.kaushik.library.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {

	private final IssueService issueService;

	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@PostMapping("/issueBook")
	public ResponseEntity<Integer> issueBook(@RequestParam int bookId, @RequestParam int memberId) {
		Integer issueId = issueService.issueBook(bookId, memberId, "ADMIN");
		return new ResponseEntity<>(issueId, HttpStatus.CREATED);
	}

	@PostMapping("/returnBook")
	public ResponseEntity<Boolean> returnBook(@RequestParam int bookId) {
		Boolean updatedCount = issueService.returnBook(bookId, "ADMIN");
		return new ResponseEntity<>(updatedCount, HttpStatus.OK);
	}

	@GetMapping("/viewIssuedRecords")
	public ResponseEntity<List<IssueRecord>> viewIssuedRecords() {
		List<IssueRecord> issuedRecords = issueService.getAllIssuedRecords();
		return new ResponseEntity<>(issuedRecords, HttpStatus.OK);
	}

	@GetMapping("/reports/overdueBooks")
	public ResponseEntity<List<IssueRecord>> getOverdueBooks() {
		List<IssueRecord> overdueBooks = issueService.getOverdueBooks();
		return new ResponseEntity<>(overdueBooks, HttpStatus.OK);
	}

	@GetMapping("/reports/membersWithActiveBooks")
	public ResponseEntity<List<Member>> getMembersWithActiveBooks() {
		List<Member> members = issueService.getMembersWithActiveBooks();
		return new ResponseEntity<>(members, HttpStatus.OK);
	}
}