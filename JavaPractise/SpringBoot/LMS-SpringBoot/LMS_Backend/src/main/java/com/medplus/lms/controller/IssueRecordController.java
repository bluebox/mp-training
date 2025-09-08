package com.medplus.lms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.service.IssueRecordService;
import com.medplus.lms.service.IssueRecordServiceInterface;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/issues")
public class IssueRecordController {

    private final IssueRecordServiceInterface service;

    public IssueRecordController(IssueRecordService service) {
        this.service = service;
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestBody IssueRecordDto request)  {
        service.issueBook(request.getBookId(), request.getMemberId(), request.getIssuedBy());
        return ResponseEntity.ok("Book issued successfully");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody IssueRecordDto request)  {
    	service.returnBook(request.getBookId(), request.getMemberId(), request.getReturnedTo());
        return ResponseEntity.ok("Book returned successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<IssueRecordDto>> getAllIssues() {
        return ResponseEntity.ok(service.getAllIssues());
    }
}
