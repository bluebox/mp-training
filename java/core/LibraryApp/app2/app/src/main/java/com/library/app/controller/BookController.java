package com.library.app.controller;

import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;

import com.library.app.model.Book;
import com.library.app.model.IssueRecord;
import com.library.app.service.LibraryService;

@Controller
public class BookController {
	Logger log=LoggerFactory.getLogger(BookController.class.getName());
	private LibraryService libraryService;
	@Autowired
	public BookController(LibraryService libraryService) {
		this.libraryService=libraryService;
	}
	
	@RequestMapping(value="/addBook",method=RequestMethod.GET)
	public String addBookPage(Model model) {
		model.addAttribute("book",new Book());
		return "addBook";
	}
	
	
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public String addBookPage(@Valid @ModelAttribute("book") Book book, Model model) {
	    try {
	        libraryService.addBook(book);
	        model.addAttribute("success", "Book added successfully.");
	        model.addAttribute("book", new Book());
	    } 
	    catch (Exception e) {
	        model.addAttribute("error", "Failed to add book: " + e.getMessage());
	    }
	    return "viewAllBooks";
	}
	
	@RequestMapping(value="/updateBook",method=RequestMethod.GET)
	public String updateBookPage(Model model) {
		model.addAttribute("book",new Book());
		return "updateBook";
	}
	
	@RequestMapping(value="/updateBook",method=RequestMethod.POST)
	public String updateBookPage(@Valid @ModelAttribute("book") Book book, Model model) {
	    try {
	        libraryService.updateBook(book);
	        model.addAttribute("success", "Book updated successfully.");
	        model.addAttribute("book", new Book());
	    } 
	    catch (Exception e) {
	        model.addAttribute("error", "Failed to update book: " + e.getMessage());
	    }
	    return "viewAllBooks";
	}
	
	
	@RequestMapping(value="/issueBook",method=RequestMethod.GET)
	public String issueBookPage(Model model) {
		model.addAttribute("issueRecord",new IssueRecord());
		return "issueBook";
	}
	
	@RequestMapping(value="/issueBook", method=RequestMethod.POST)
	public String issueBookPage(@Valid @ModelAttribute("issueRecord") IssueRecord issueRecord, Model model) {
	    try {
	        libraryService.issueBook(issueRecord.getBookId(), issueRecord.getMemberId());
	        model.addAttribute("success", "Book issued successfully.");
	        model.addAttribute("issueRecord", new IssueRecord());
	    } 
	    catch (Exception e) {
	        model.addAttribute("error", "Failed to issue book: " + e.getMessage());
	    }
	    return "issueBook";
	}


	
	@RequestMapping(value="/returnBook", method=RequestMethod.GET)
	public String returnBookPage(Model model) {
	    model.addAttribute("issueRecord", new IssueRecord());
	    return "returnBook";  
	}

	@RequestMapping(value="/returnBook", method=RequestMethod.POST)
	public String returnBookPage(@Valid @ModelAttribute("issueRecord") IssueRecord issueRecord, Model model) {
	    try {
	        libraryService.returnBook(issueRecord.getIssueId());
	        model.addAttribute("success", "Book returned successfully.");
	        model.addAttribute("issueRecord", new IssueRecord());
	    } catch (Exception e) {
	        model.addAttribute("error", "Failed to return book: " + e.getMessage());
	    }
	    return "returnBook";  
	}


	
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public String viewAllBooksPage(Model model) {
	    try {
	        List<Book> books = libraryService.viewAllBooks();  
	        log.info("All Books : "+books);
	        model.addAttribute("books", books);               
	        model.addAttribute("book", new Book());            
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "No books found!");
	    }
	    return "viewAllBooks";
	}
	
	@RequestMapping(value="/issuedRecords", method=RequestMethod.GET)
	public String issuedRecordsPage(Model model) {
	    try {
	        List<IssueRecord> issuedRecords = libraryService.viewIssuedRecords();  
	        model.addAttribute("issuedRecords",issuedRecords);               
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("error", "No records found!");
	    }
	    return "issuedRecords";
	}

	
}



