package com.library.Library.controller;

import com.library.Library.model.IssueRecord;
import com.library.Library.model.Member;
import com.library.Library.service.ReportsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class ReportsController {

    private final ReportsService reportService;

    public ReportsController(ReportsService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/overdue")
    public List<IssueRecord> getOverdueBooks() {
        return reportService.getOverdueBooks();
    }

    
    @GetMapping("/books-per-category")
    public Map<String, Long> getBooksPerCategory() {
        return reportService.getBooksPerCategory();
    }

   
    @GetMapping("/active-members")
    public List<Member> getActiveMembers() {
        return reportService.getActiveMembers();
    }
}
