package com.lms.LMS_Springboot.Controller;

import com.lms.LMS_Springboot.Model.IssueRecord;
import com.lms.LMS_Springboot.Service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueRecordController {

    @Autowired
    private IssueRecordService issueRecordService;

    @GetMapping
    public List<IssueRecord> getAllRecords() {
        return issueRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public IssueRecord getRecordById(@PathVariable int id) {
        return issueRecordService.getRecordById(id);
    }

    @PostMapping
    public String issueBook(@RequestBody IssueRecord record) {
        record.setStatus(com.lms.LMS_Springboot.Model.checking_enum.Status_issue.ISSUED);
        record.setIssueDate(LocalDate.now());
        int rows = issueRecordService.issueBook(record);

        if (rows == -1) {
            return "Book is already issued to another member!";
        }
        return rows > 0 ? "Book issued successfully" : "Failed to issue book";
    }

    @PutMapping("/return/{id}")
    public String returnBook(@PathVariable int id) {
        int rows = issueRecordService.returnBook(id, LocalDate.now());
        return rows > 0 ? "Book returned successfully" : "Failed to return book";
    }
}
