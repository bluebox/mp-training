package com.library.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.library.app.model.Book;
import com.library.app.service.LibraryService;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
//	Logger log=LoggerFactory.getLogger(BookController.class.getName());
	private LibraryService libraryService;
	@Autowired
	public BookController(LibraryService libraryService) {
		this.libraryService=libraryService;
	}
	
//	@RequestMapping(value="/addBook",method=RequestMethod.GET)
	@GetMapping(value="/addBook")
	public String showAddBookPage(Model model) {
		model.addAttribute("book",new Book());
		return "addBook";
	}
	
	
//	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	@PostMapping(value="/addBook")
	public String addBookPage(@Valid @ModelAttribute("book") Book book,Model model, Errors errors) {
	

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
	

	
//	@RequestMapping(value="/updateBook",method=RequestMethod.GET)
	@GetMapping(value="/updateBook")
	public String ShowUpdateBookPage(Model model) {
		model.addAttribute("book",new Book());
		return "updateBook";
	}
	
//	@RequestMapping(value="/updateBook",method=RequestMethod.POST)
	@PostMapping(value="/updateBook")
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


//	@RequestMapping(value="/books", method=RequestMethod.GET)
	@GetMapping(value="/books")
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
	
}



