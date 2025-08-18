package controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import model.Issuerecords;
import model.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.IssuerecordsRepo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/issuerecords")
@CrossOrigin(origins = "*")
public class IssuerecordsController {

    @Autowired
    private IssuerecordsRepo issuerecordsRepo;

    // DTO for book issue
    public static class IssueRequest {
        @NotNull(message = "Book ID is required")
        public Integer bookId;

        @NotNull(message = "Member ID is required")
        public Integer memberId;

        @NotNull(message = "Status is required")
        public String status;    

        @NotNull(message = "Issue date is required")
        public String issueDate; 

        public String returnDate; 
    }

    //  book return
    public static class ReturnRequest {
        @NotNull(message = "Book ID is required")
        public Integer bookId;

        @NotNull(message = "Member ID is required")
        public Integer memberId;
    }

    // Get all issue records
    @GetMapping
    public ResponseEntity<List<Issuerecords>> getAllIssueRecords() {
        return ResponseEntity.ok(issuerecordsRepo.getAllIssueRecords());
    }

    // Get specific issue record
    @GetMapping("/{bookId}/{memberId}")
    public ResponseEntity<?> getIssueRecord(@PathVariable int bookId, @PathVariable int memberId) {
        Issuerecords record = issuerecordsRepo.getIssueRecord(bookId, memberId);
        if (record != null) {
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Issue record not found for Book ID " + bookId + " and Member ID " + memberId);
        }
    }

    // Create new book issue
    @PostMapping
    public ResponseEntity<?> createBookIssue(@Valid @RequestBody IssueRequest request) {
        try {
            IssueStatus issueStatus = IssueStatus.getIssueStatus(request.status);
            LocalDate issueDate = LocalDate.parse(request.issueDate);
            LocalDate returnDate = request.returnDate != null ? LocalDate.parse(request.returnDate) : null;

            int rows = issuerecordsRepo.createBookIssue(request.bookId, request.memberId, issueStatus, issueDate, returnDate);

            if (rows > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Book issued successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to issue book");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid status or date format");
        }
    }

    // Return a book
    @PutMapping("/return")
    public ResponseEntity<?> returnBook(@Valid @RequestBody ReturnRequest request) {
        int rows = issuerecordsRepo.returnBook(request.bookId, request.memberId);
        if (rows > 0) {
            return ResponseEntity.ok("Book returned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue record not found for return");
        }
    }
}
