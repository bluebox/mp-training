package com.vardhan.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.model.Member;
import com.vardhan.main.service.ReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/overdue-books")
    public ResponseEntity<ApiResponse<List<IssueBook>>> getOverdueBooks() {
        log.info("Generating overdue books report");
        
        List<IssueBook> overdueBooks = reportService.getOverdueBooks();
        
        return ResponseEntity.ok(ApiResponse.success(overdueBooks, 
                "Overdue books report generated successfully"));
    }

    @GetMapping("/books-by-category")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getBooksByCategory() {
        log.info("Generating books by category report");
        
        Map<String, Long> booksByCategory = reportService.getBookCountByCategory();
        
        return ResponseEntity.ok(ApiResponse.success(booksByCategory, 
                "Books by category report generated successfully"));
    }

    @GetMapping("/active-members")
    public ResponseEntity<ApiResponse<List<Member>>> getActiveMembers() {
        log.info("Generating active members report");
        
        List<Member> activeMembers = reportService.getMembersWithActiveIssues();
        
        return ResponseEntity.ok(ApiResponse.success(activeMembers, 
                "Active members report generated successfully"));
    }

    @GetMapping("/members-with-overdue")
    public ResponseEntity<ApiResponse<List<Member>>> getMembersWithOverdue() {
        log.info("Generating members with overdue books report");
        
        List<Member> membersWithOverdue = reportService.getMembersWithOverdueBooks();
        
        return ResponseEntity.ok(ApiResponse.success(membersWithOverdue, 
                "Members with overdue books report generated successfully"));
    }

    @GetMapping("/most-issued-books")
    public ResponseEntity<ApiResponse<List<Book>>> getMostIssuedBooks(
            @RequestParam(defaultValue = "10") int limit) {
        log.info("Generating most issued books report with limit: {}", limit);
        
        List<Book> mostIssuedBooks = reportService.getMostIssuedBooks(limit);
        
        return ResponseEntity.ok(ApiResponse.success(mostIssuedBooks, 
                "Most issued books report generated successfully"));
    }

    @GetMapping("/member-issue-count")
    public ResponseEntity<ApiResponse<Map<Integer, Long>>> getMemberIssueCount() {
        log.info("Generating member issue count report");
        
        Map<Integer, Long> memberIssueCount = reportService.getMemberIssueCount();
        
        return ResponseEntity.ok(ApiResponse.success(memberIssueCount, 
                "Member issue count report generated successfully"));
    }

    @GetMapping("/issues-by-date")
    public ResponseEntity<ApiResponse<List<IssueBook>>> getIssuesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("Generating issues report from {} to {}", startDate, endDate);
        
        if (endDate.isBefore(startDate)) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid date range", "End date must be after start date"));
        }
        
        List<IssueBook> issues = reportService.getIssuesInDateRange(startDate, endDate);
        
        return ResponseEntity.ok(ApiResponse.success(issues, 
                "Issues by date range report generated successfully"));
    }

    @GetMapping("/returns-by-date")
    public ResponseEntity<ApiResponse<List<IssueBook>>> getReturnsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        log.info("Generating returns report from {} to {}", startDate, endDate);
        
        if (endDate.isBefore(startDate)) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("Invalid date range", "End date must be after start date"));
        }
        
        List<IssueBook> returns = reportService.getReturnsInDateRange(startDate, endDate);
        
        return ResponseEntity.ok(ApiResponse.success(returns, 
                "Returns by date range report generated successfully"));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDashboardStats() {
        log.info("Generating dashboard statistics");
        
        Map<String, Object> dashboardStats = reportService.getDashboardStatistics();
        
        return ResponseEntity.ok(ApiResponse.success(dashboardStats, 
                "Dashboard statistics generated successfully"));
    }

    @GetMapping("/utilization")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUtilizationReport() {
        log.info("Generating library utilization report");
        
        Map<String, Object> utilization = reportService.getLibraryUtilizationReport();
        
        return ResponseEntity.ok(ApiResponse.success(utilization, 
                "Library utilization report generated successfully"));
    }

    @GetMapping("/available-books-by-category")
    public ResponseEntity<ApiResponse<List<Book>>> getAvailableBooksByCategory(
            @RequestParam String category) {
        log.info("Generating available books report for category: {}", category);
        
        List<Book> availableBooks = reportService.getAvailableBooksByCategory(category);
        
        return ResponseEntity.ok(ApiResponse.success(availableBooks, 
                "Available books by category report generated successfully"));
    }
}
