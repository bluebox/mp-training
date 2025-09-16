package com.LMS.LibMS.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.service.interfaces.BookService;
import com.LMS.LibMS.service.interfaces.IssueService;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    private final IssueService issueService;
    private final BookService bookService;

    @Autowired
    public ReportsController(IssueService issueService, BookService bookService) {
        this.issueService = issueService;
        this.bookService = bookService;
    }

    @GetMapping("/overdueBooks")
    @ResponseBody
    public List<IssueRecord> getOverdueBooks(@RequestParam(defaultValue = "14") int dueDays) {
        return issueService.getOverdueBooks(dueDays);
    }

    @GetMapping("/booksByCategory")
    @ResponseBody
    public Map<String, Long> getBooksByCategory() {
        return bookService.getAllBooks().stream()
                .collect(Collectors.groupingBy(
                        book -> book.getCategory().getDisplayName(),
                        Collectors.counting()
                ));
    }

    @GetMapping("/membersWithActiveBooks")
    @ResponseBody
    public List<Member> getMembersWithActiveBooks() {
        return issueService.getMembersWithActiveBooks();
    }
}