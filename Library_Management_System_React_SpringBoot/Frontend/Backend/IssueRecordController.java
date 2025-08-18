package com.library.Library.controller;

import com.library.Library.model.IssueRecord;
import com.library.Library.service.IssueRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issuerecords")
@CrossOrigin(origins = "http://localhost:3000") 
public class IssueRecordController {

    @Autowired
    private IssueRecordService service;

    @GetMapping
    public List<IssueRecord> getAll() {
        return service.getAll();
    }

    @PostMapping("/issue")
    public IssueRecord issueBook(@RequestBody IssueRecord record) {
        return service.issueBook(record);
    }

    @PutMapping("/return/{id}")
    public IssueRecord returnBook(@PathVariable Integer id) {
        return service.returnBook(id);
    }
}
