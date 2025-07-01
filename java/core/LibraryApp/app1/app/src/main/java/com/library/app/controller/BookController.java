package com.library.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

	@RequestMapping("/addBook")
	public String addBookPage() {
		return "addBook";
	}
	
	@RequestMapping("/updateBook")
	public String updateBookPage() {
		return "updateBook";
	}
	
	@RequestMapping("/issueBook")
	public String issueBookPage() {
		return "issueBook";
	}
	
	@RequestMapping("/returnBook")
	public String returnBookPage() {
		return "returnBook";
	}
	
	@RequestMapping("/books")
	public String viewAllBooksPage() {
		return "viewAllBooks";
	}
	
	@RequestMapping("/issuedRecords")
	public String issuedRecordsPage() {
		return "issuedRecords";
	}
}
