package com.vardhan.main.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.main.service.ReturnBookService;
import com.vardhan.main.util.ValidationUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/returns")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ReturnBookController {

    private final ReturnBookService returnBookService;
    private final ValidationUtil validationUtil;

    @GetMapping("/member/{mobile}")
    public ResponseEntity<ApiResponse<MemberReturnInfo>> getMemberByMobile(@PathVariable String mobile) {
        log.info("Fetching member info for mobile: {}", mobile);
        
        validationUtil.validateMobileFormat(mobile);
        
        var memberNameOpt = returnBookService.getMemberNameByMobile(mobile);
        if (memberNameOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        List<String> issuedBooks = returnBookService.getIssuedBooksByMobile(mobile);
        List<String> activeIssuesWithDates = returnBookService.getActiveIssuesWithDueDates(mobile);
        
        MemberReturnInfo info = new MemberReturnInfo(
                memberNameOpt.get(),
                mobile,
                issuedBooks,
                activeIssuesWithDates,
                issuedBooks.size()
        );
        
        return ResponseEntity.ok(ApiResponse.success(info, "Member info retrieved"));
    }

    @GetMapping("/books/{mobile}")
    public ResponseEntity<ApiResponse<List<String>>> getIssuedBooksByMobile(@PathVariable String mobile) {
        log.info("Fetching issued books for mobile: {}", mobile);
        
        validationUtil.validateMobileFormat(mobile);
        
        if (!returnBookService.isMemberExists(mobile)) {
            return ResponseEntity.notFound().build();
        }
        
        List<String> books = returnBookService.getIssuedBooksByMobile(mobile);
        
        return ResponseEntity.ok(ApiResponse.success(books, "Issued books retrieved"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> returnBook(@RequestBody ReturnBookRequest request) {
        log.info("Processing book return for mobile: {}, book: {}", 
                request.getMobile(), request.getBookTitle());
        
        validationUtil.validateReturnRequest(request.getMobile(), request.getBookTitle());
        validationUtil.validateReturnStatus(request.getStatus());
        
        boolean success = returnBookService.returnBookByMobileAndTitle(
                request.getMobile(), 
                request.getBookTitle(), 
                request.getStatus()
        );
        
        if (success) {
            log.info("Successfully returned book: {} for member: {}", 
                    request.getBookTitle(), request.getMobile());
            return ResponseEntity.ok(ApiResponse.success(null, "Book returned successfully"));
        } else {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Book return failed", "Please try again"));
        }
    }

    @GetMapping("/stats/{mobile}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getReturnStats(@PathVariable String mobile) {
        log.info("Fetching return stats for mobile: {}", mobile);
        
        validationUtil.validateMobileFormat(mobile);
        
        if (!returnBookService.isMemberExists(mobile)) {
            return ResponseEntity.notFound().build();
        }
        
        int activeIssues = returnBookService.getActiveIssueCountByMobile(mobile);
        List<String> overdueBooks = returnBookService.getOverdueBooksByMobile(mobile);
        
        Map<String, Object> stats = Map.of(
                "activeIssues", activeIssues,
                "overdueBooks", overdueBooks.size(),
                "overdueBookTitles", overdueBooks
        );
        
        return ResponseEntity.ok(ApiResponse.success(stats, "Return stats retrieved"));
    }

    public static class MemberReturnInfo {
        private String memberName;
        private String mobile;
        private List<String> issuedBooks;
        private List<String> activeIssuesWithDates;
        private int totalActiveIssues;
        
        public MemberReturnInfo(String memberName, String mobile, List<String> issuedBooks, 
                               List<String> activeIssuesWithDates, int totalActiveIssues) {
            this.memberName = memberName;
            this.mobile = mobile;
            this.issuedBooks = issuedBooks;
            this.activeIssuesWithDates = activeIssuesWithDates;
            this.totalActiveIssues = totalActiveIssues;
        }
        
        public String getMemberName() { return memberName; }
        public String getMobile() { return mobile; }
        public List<String> getIssuedBooks() { return issuedBooks; }
        public List<String> getActiveIssuesWithDates() { return activeIssuesWithDates; }
        public int getTotalActiveIssues() { return totalActiveIssues; }
    }
    
    public static class ReturnBookRequest {
        private String mobile;
        private String bookTitle;
        private String status;
        
        public String getMobile() { return mobile; }
        public void setMobile(String mobile) { this.mobile = mobile; }
        public String getBookTitle() { return bookTitle; }
        public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
