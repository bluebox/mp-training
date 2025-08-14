package com.LMS.LibMS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.service.interfaces.BookService;

@Controller
public class BookController {
	
	public final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}
	
	@GetMapping("/books")
	@ResponseBody
	public List<Book> displayBooks(){
		return bookService.findBooks();
	}

}
