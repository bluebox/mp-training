package com.lms.controller;

import com.lms.model.IssueBook;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.model.BookCategory;
import com.lms.service.IssueBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class IssueBookController {

    private final IssueBookService issueBookService;

    // Issue a book
    @PostMapping("/issue")
    public ResponseEntity<String> issueBook(@RequestBody IssueBook issue) throws Exception {
        System.out.println("Received issue request: MemberId=" + issue.getMemberId() + ", BookId=" + issue.getBookId());
        if (issue.getStatus() == '\0') {
            issue.setStatus('I'); 
        }
        boolean success = issueBookService.issueBook(issue);
        return success
                ? ResponseEntity.ok("Book issued successfully")
                : ResponseEntity.badRequest().body("Failed to issue book");
    }

    // Get member by mobile number
    @GetMapping("/member/{mobile}")
    public ResponseEntity<Member> getMember(@PathVariable String mobile) throws Exception {
        System.out.println("Fetching member with mobile: " + mobile);
        Member member = issueBookService.getMemberByMobile(mobile);
        if (member == null) {
            System.out.println("Member not found!");
            return ResponseEntity.notFound().build();
        }
        System.out.println("Found member: " + member.getName());
        return ResponseEntity.ok(member);
    }

    // Get available books by category
    @GetMapping("/available/{category}")
    public ResponseEntity<List<Book>> getAvailableBooks(@PathVariable String category) throws Exception {
        BookCategory bookCategory;
        try {
            bookCategory = BookCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category: " + category);
            return ResponseEntity.badRequest().body(null);
        }

        List<Book> books = issueBookService.getAvailableBooksByCategory(bookCategory);
        return ResponseEntity.ok(books);
    }

    // Get active issues of a member
    @GetMapping("/active/{memberId}")
    public ResponseEntity<List<IssueBook>> getActiveIssues(@PathVariable int memberId) throws Exception {
        List<IssueBook> issues = issueBookService.getActiveIssuesByMember(memberId);
        return ResponseEntity.ok(issues);
    }
}
