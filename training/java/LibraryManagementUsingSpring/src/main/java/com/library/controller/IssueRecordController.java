package com.library.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.IssueRecord;
import com.library.service.IssueRecordService;

@Controller
@RequestMapping("/issues")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issueRecordService;

    @GetMapping("/list")
    public String listIssuedBooks(Model model) {
        List<IssueRecord> issuedBooks = issueRecordService.getAllIssuedRecords();
        model.addAttribute("issuedBooks", issuedBooks);
        return "view_issued_books";  // Thymeleaf view name (create corresponding)
    }

    @GetMapping("/issueForm")
    public String showIssueForm(Model model) {
        model.addAttribute("issueRecord", new IssueRecord());
        return "issue_book_form";  // Thymeleaf form for issuing book
    }

    @PostMapping("/issue")
    public String issueBook(@ModelAttribute IssueRecord issueRecord, Model model) {
        boolean success = issueRecordService.issueBook(issueRecord);
        model.addAttribute("message", success ? "Book issued successfully." : "Failed to issue book.");
        return "redirect:/issues/list";
    }

    @GetMapping("/returnForm")
    public String showReturnForm(Model model) {
        model.addAttribute("issueRecord", new IssueRecord());
        return "return_book_form";  // Thymeleaf form for returning book
    }

    @PostMapping("/return")
    public String returnBook(@ModelAttribute IssueRecord issueRecord, Model model) {
        boolean success = issueRecordService.returnBook(issueRecord);
        model.addAttribute("message", success ? "Book returned successfully." : "Failed to return book.");
        return "redirect:/issues/list";
    }
    
    @GetMapping("/getActiveIssuedBooks")
    public String getActiveIssuedBooks(Model model) {
    	List<IssueRecord> books = issueRecordService.getActiveIssuedBooks();
    	model.addAttribute("books", books);
    	return "active_issued_books";
    }
    
    @GetMapping("/overdue-books")
    public String getIssuedBooksOverOneMonthOld(Model model) {
        List<IssueRecord> overdueBooks = issueRecordService.getIssuedBooksOverOneMonthOld();
        model.addAttribute("overdueBooks", overdueBooks);
        return "overdue_books";
    }
}
