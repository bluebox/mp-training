package com.library.app.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.model.Book;
import com.library.app.model.IssueRecord;
import com.library.app.model.Response;
import com.library.app.service.LibraryService;

import javax.validation.Valid;
@Controller
@RequestMapping("/api/issue")
@RestController
@CrossOrigin(origins="http://localhost:3000/")
//@CrossOrigin(origins="*")
public class IssueController {
	@Autowired
	private LibraryService libraryService;
	
	
//	@RequestMapping(value="/issueBook", method=RequestMethod.POST)
	@PostMapping("/issueBook")
	public ResponseEntity<Response> issueBookPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody IssueRecord issueRecord) {
		Response response = new Response();
	    try {
	        libraryService.issueBook(issueRecord.getBookId(), issueRecord.getMemberId());
	        response.setStatusCode("200");
			response.setStatusMsg("Book issued successfully!");
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.header("isBookIssued", "true")
					.body(response);
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    response.setStatusCode("400");
        response.setStatusMsg("Book is not Issued!");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
	}


	@PostMapping("/returnBook")
	public ResponseEntity<Response> returnBookPage(@RequestHeader("invocationFrom") String invocationFrom,@RequestBody IssueRecord issueRecord) {
		Response response = new Response();
	    try {
	        libraryService.returnBook(issueRecord.getIssueId());
	        response.setStatusCode("200");
			response.setStatusMsg("Book/Record returned successfully!");
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.header("isBookreturned", "true")
					.body(response);
	    } 
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    response.setStatusCode("400");
        response.setStatusMsg("Book is not Returned!");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
	}
	
	
	@GetMapping(value="/issueRecords")
	public List<IssueRecord> viewAllRecordsPage() throws Exception {
	        List<IssueRecord> issuedRecords = libraryService.viewIssuedRecords();
		    return issuedRecords;            
	}

}
