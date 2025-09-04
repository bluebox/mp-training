package com.vardhan.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.main.model.IssueBook;
import com.vardhan.main.service.IssueBookService;
import com.vardhan.main.util.ValidationUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class IssueBookController {
	
	
	@Autowired
    private IssueBookService issueBookService;
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<ApiResponse<IssueBook>> issueBook(@Valid @RequestBody IssueBookRequest request) {
        log.info("Issuing book {} to member {}", request.getBookId(), request.getMemberId());
        
        validationUtil.validateIssueBookRequest(request.getMemberId(), request.getBookId());
        
        IssueBook issueBook = IssueBook.builder()
                .memberId(request.getMemberId())
                .bookId(request.getBookId())
                .issueDate(LocalDate.now())
                .returnDate(request.getReturnDate() != null ? request.getReturnDate() : 
                           issueBookService.calculateDueDate(LocalDate.now()))
                .build();
        
        IssueBook savedIssue = issueBookService.issueBook(issueBook);
        log.info("Successfully issued book with issue ID: {}", savedIssue.getIssueId());
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(savedIssue, "Book issued successfully"));
    }

    @PutMapping("/{issueId}/return")
    public ResponseEntity<ApiResponse<Void>> returnBook(
            @PathVariable Integer issueId,
            @RequestBody ReturnBookRequest request) {
        log.info("Returning book for issue ID: {}", issueId);
        
        validationUtil.validatePositiveId(issueId, "Issue ID");
        
        LocalDate returnDate = request.getActualReturnDate() != null ? 
                request.getActualReturnDate() : LocalDate.now();
        
        boolean returned = issueBookService.returnBook(issueId, returnDate);
        if (returned) {
            log.info("Successfully returned book for issue ID: {}", issueId);
            return ResponseEntity.ok(ApiResponse.success(null, "Book returned successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IssueBook>>> getAllIssueRecords() {
        log.info("Fetching all issue records");
        
        List<IssueBook> issues = issueBookService.getAllIssueRecords();
        
        return ResponseEntity.ok(ApiResponse.success(issues, "Issue records retrieved"));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<List<IssueBook>>> getActiveIssuesByMember(@PathVariable Integer memberId) {
        log.info("Fetching active issues for member: {}", memberId);
        
        validationUtil.validatePositiveId(memberId, "Member ID");
        
        List<IssueBook> issues = issueBookService.getActiveIssuesByMember(memberId);
        
        return ResponseEntity.ok(ApiResponse.success(issues, "Active issues retrieved"));
    }

    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<List<IssueBook>>> getOverdueBooks() {
        log.info("Fetching overdue books");
        
        List<IssueBook> overdueBooks = issueBookService.getOverdueBooks();
        
        return ResponseEntity.ok(ApiResponse.success(overdueBooks, "Overdue books retrieved"));
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<ApiResponse<IssueBook>> getIssueById(@PathVariable Integer issueId) {
        log.info("Fetching issue with ID: {}", issueId);
        
        validationUtil.validatePositiveId(issueId, "Issue ID");
        
        return issueBookService.findIssueById(issueId)
                .map(issue -> ResponseEntity.ok(ApiResponse.success(issue, "Issue found")))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getIssueStats() {
        log.info("Fetching issue statistics");
        
        long totalActive = issueBookService.getTotalActiveIssues();
        long totalOverdue = issueBookService.getTotalOverdueIssues();
        
        Map<String, Long> stats = Map.of(
                "activeIssues", totalActive,
                "overdueIssues", totalOverdue
        );
        
        return ResponseEntity.ok(ApiResponse.success(stats, "Issue statistics retrieved"));
    }

    public static class IssueBookRequest {
        private Integer memberId;
        private String bookId;
        private LocalDate returnDate;
        
        public Integer getMemberId() { return memberId; }
        public void setMemberId(Integer memberId) { this.memberId = memberId; }
        public String getBookId() { return bookId; }
        public void setBookId(String bookId) { this.bookId = bookId; }
        public LocalDate getReturnDate() { return returnDate; }
        public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    }

    public static class ReturnBookRequest {
        private LocalDate actualReturnDate;
        
        public LocalDate getActualReturnDate() { return actualReturnDate; }
        public void setActualReturnDate(LocalDate actualReturnDate) { this.actualReturnDate = actualReturnDate; }
    }
}
