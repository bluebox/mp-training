package com.lms.springbootlms.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.service.ReportService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final ReportService reportService;

    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public Object getReport(@RequestParam(name = "reportType", required = false, defaultValue = "Overdue Books") String reportType) {
        try {
            switch (reportType) {
                case "Overdue Books":
                    return Map.of("reportType", reportType, "data", reportService.fetchOverdueBooks());
                case "Books by Category":
                    return Map.of("reportType", reportType, "data", reportService.fetchBookCountByCategory());
                case "Active Members":
                    return Map.of("reportType", reportType, "data", reportService.fetchMembersWithActiveIssues());
                default:
                    return Map.of("error", "Unknown report type: " + reportType);
            }
        } catch (ServiceException e) {
            return Map.of("error", e.getMessage());
        }
    }
}
