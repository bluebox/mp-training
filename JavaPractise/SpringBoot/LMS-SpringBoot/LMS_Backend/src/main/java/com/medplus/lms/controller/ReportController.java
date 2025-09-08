package com.medplus.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.MemberIssuedBookDto;
import com.medplus.lms.service.ReportService;
import com.medplus.lms.service.ReportServiceInterface;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportServiceInterface reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/overdue-books")
    public ResponseEntity<List<IssueRecordDto>> getOverdueBooks() {
        List<IssueRecordDto> overdueBooks = reportService.getOverdueBooks();
        return ResponseEntity.ok(overdueBooks);
    }

    @GetMapping("/books-per-category")
    public ResponseEntity<Map<String, Long>> getBooksCountPerCategory() {
        Map<String, Long> booksPerCategory = reportService.getBooksCountPerCategory();
        return ResponseEntity.ok(booksPerCategory);
    }

    @GetMapping("/members-active")
    public ResponseEntity<List<MemberIssuedBookDto>> getMembersWithActiveIssuedBooks() {
        List<MemberIssuedBookDto> activeMembers = reportService.getMembersWithActiveIssuedBooks();
        return ResponseEntity.ok(activeMembers);
    }
}
