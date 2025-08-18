package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;

import com.SpringBoot_LMS.SpringBoot_LMS.service.BookService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path="/Book")
public class BookController {
   
	@Autowired
	private BookService service;
	
	@GetMapping("/viewBooks")
	public ResponseEntity<List<Book>> viewBooks() throws IOException, SQLException {
		    List<Book> books=service.getBooks();
		    if(books==null) {
		    	return ResponseEntity.badRequest().body(books);
		    }
	        return ResponseEntity.ok().body(books);
	}
	
	@GetMapping("/getbook/{id}")
	public ResponseEntity<String> viewBook(@PathVariable int id) throws IOException, SQLException {
         Book book=service.getbookbyId(id);
         System.out.println(book.toString());
         if(book !=null) {
        	 return ResponseEntity.ok().body(book.toString());
         }
         return ResponseEntity.badRequest().body("Error while fetching the book");
}
	
	
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public ResponseEntity<String> addBook(@Valid @RequestBody Book book) throws SQLException{
		System.out.println(book);
		int value=service.AddBook(book);
		if(value>0) {
			return ResponseEntity.ok().body("book created and added to DB successfully");	
		}
		return ResponseEntity.badRequest().body("Error while adding the book");
	}
	
	@RequestMapping(value="/updateBook",method=RequestMethod.POST)
	public ResponseEntity<String> updateBook(@Valid @RequestBody Book book) throws SQLException{
		System.out.println(book);
		Book value=service.updateBook(book);
		if(value!=null) {
			return ResponseEntity.ok().body("book updated to DB successfully"+value);	
		}
		return ResponseEntity.badRequest().body("Error while updating the book");
	}
	
	
	
	
}
