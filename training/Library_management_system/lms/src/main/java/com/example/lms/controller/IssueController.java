package com.example.lms.controller;

import com.example.lms.model.IssueRecord;
import com.example.lms.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestParam Long bookId,
                                            @RequestParam Long memberId) {
        String result = issueService.issueBook(bookId, memberId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long issueId) {
        String result = issueService.returnBook(issueId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueRecord> getIssueById(@PathVariable Long id) {
        IssueRecord record = issueService.getIssueById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }

    @GetMapping
    public List<IssueRecord> getAllIssues() {
        return issueService.getAllIssues();
    }
}
