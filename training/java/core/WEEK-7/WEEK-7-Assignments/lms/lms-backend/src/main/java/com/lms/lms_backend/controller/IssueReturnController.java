package com.lms.lms_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lms_backend.model.Book;
import com.lms.lms_backend.model.IssueRecord;
import com.lms.lms_backend.model.Member;
import com.lms.lms_backend.service.BookService;
import com.lms.lms_backend.service.IssueReturnService;
import com.lms.lms_backend.service.MemberService;
import com.lms.lms_backend.utilities.SuccessResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/issues")
public class IssueReturnController {

    private final IssueReturnService issueReturnService;
    private final MemberService memberService;
    private final BookService bookService;

    public IssueReturnController(IssueReturnService issueReturnService,
                                 MemberService memberService,
                                 BookService bookService) {
        this.issueReturnService = issueReturnService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @PostMapping("/issue")
    public ResponseEntity<Object> issueBook(@Valid @RequestBody IssueRecord issue) {
        issue.setIssueDate(LocalDate.now());
        issueReturnService.issueBook(issue);
        return SuccessResponse.build(HttpStatus.OK,"Book issued successfully");
        
    }

    @PostMapping("/return/{issueId}")
    public ResponseEntity<Object> returnBook(@PathVariable int issueId,
                                             @Valid @RequestBody IssueRecord issue) {
        issue.setIssueId(issueId);
        issueReturnService.returnBook(issue);
        return SuccessResponse.build(HttpStatus.OK,"Book returned successfully");
    }

    @GetMapping
    public ResponseEntity<List<IssueRecord>> getAllIssues() {
        return ResponseEntity.ok(issueReturnService.getAllIssues());
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
