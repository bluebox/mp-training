package com.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.domain.IssueRecord;
import com.library.service.IssueRecordService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/issues")
public class IssueRecordController {
	
	@Autowired
    private IssueRecordService issueRecordService;

    @GetMapping("/list")
    public ResponseEntity<?> listIssuedBooks(@RequestParam(value = "active" , required = false) Boolean active) {
    	List<IssueRecord> issueRecords;
    	if(active!=null && active) {
    		issueRecords = issueRecordService.getActiveIssuedBooks();
    		
    		return ResponseEntity.ok(issueRecords);
    	}
        issueRecords = issueRecordService.getAllIssuedRecords(); 
       
        if(issueRecords!=null && !issueRecords.isEmpty()) {
    	   return ResponseEntity.ok(issueRecords);
        }
        Map<String , String> response= new HashMap<>();
        response.put("message", "no issued records found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@Valid @RequestBody IssueRecord issueRecord ) {
    	Map<String , String> response = new HashMap<>();
       if(issueRecordService.issueBook(issueRecord)) {
    	   response.put("status", "200");
    	   response.put("message", "book issued successfully");
    	   return ResponseEntity.ok(response);
    	   
       }
       
       response.put("status", "404");
       response.put("message", "book not issued");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@Valid @RequestBody IssueRecord issueRecord){
    	Map<String , String> response = new HashMap<>();
    	if(issueRecordService.returnBook(issueRecord)) {
    		response.put("status", "200");
    		response.put("message", "book returned successfully");
    		return ResponseEntity.ok(response);
    	}
    	response.put("status", "404");
        response.put("message", "book returned failed");
        return ResponseEntity.ok(response);
    	
    }
    
    @GetMapping("/overdue-books")
    public ResponseEntity<?> getOverdueBooks(){
    	
    	List<IssueRecord> issueRecords = issueRecordService.getIssuedBooksOverOneMonthOld();;
    	
       
        if(issueRecords!=null && !issueRecords.isEmpty()) {
    	   return ResponseEntity.ok(issueRecords);
        }
        Map<String , String> response= new HashMap<>();
        response.put("message", "no overdues records found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    	
    }

}
