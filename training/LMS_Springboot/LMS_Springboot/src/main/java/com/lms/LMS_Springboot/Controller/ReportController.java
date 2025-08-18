package com.lms.LMS_Springboot.Controller;

import com.lms.LMS_Springboot.Model.IssueRecord;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 1. Overdue books (default 14 days)
    @GetMapping("/overdue")
    public List<IssueRecord> getOverdueBooks(@RequestParam(defaultValue = "14") int days) {
        return reportService.getOverdueBooks(days);
    }

    // 2. Count of books per category
    @GetMapping("/book-count")
    public Map<String, Long> getBookCountPerCategory() {
        return reportService.getBookCountPerCategory();
    }

    // 3. Members with active issued books
    @GetMapping("/active-members")
    public List<Member> getMembersWithActiveIssuedBooks() {
        return reportService.getMembersWithActiveIssuedBooks();
    }
}
