package com.lms.LMS_Springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMS_Springboot.DAO.BookDAO;
import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController 
@RequestMapping("/Book")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@CrossOrigin

@ResponseBody
public class BookController {
	
	@Autowired 
	private BookService bookservice;
	@RequestMapping(value="/addbook",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addbookcontroller(@Valid @RequestBody Book book) {
		
		
		System.out.println("book"+book);
		if(bookservice.addbook(book)!=0) {
			return ResponseEntity.ok("Book added");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("book not added");
		}
		
		
	}
	
	@RequestMapping(value="/updatebook",method=RequestMethod.POST)
	@ResponseBody

	public ResponseEntity<String> updatebookcontroller(@Valid @RequestBody Book book) {
		
		System.out.println("book"+book);
		if(bookservice.updatebook(book)!=0) {
			return ResponseEntity.ok("Book updated");
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("book not updated");
		}
		
		
		
	}
	@RequestMapping(value="/viewbook",method=RequestMethod.GET)
	@ResponseBody

	public ResponseEntity<List<Book>> viewallbookcontroller() {
		return  ResponseEntity.ok(bookservice.viewbooks());
		//return ""+bookdao.viewallBooks();
	}
	
	
	
	
	@RequestMapping(value="/viewbook/{id}",method=RequestMethod.GET)
	@ResponseBody

	public ResponseEntity<String> viewbookwithidcontroller(@Valid @PathVariable int id) {
		
		
		Book book=bookservice.bookwithid(id);
		if(book!=null) {
			return ResponseEntity.ok().body(book.toString());
		}
		else {
			//return new ResponseEntity<>("book not added",HttpStatus.BAD_REQUEST);
		
		return ResponseEntity.badRequest().body("book not fetched");
		}
		//return ""+bookdao.viewallBooks();
	}
	
	
	@GetMapping("/checktoken")
	public CsrfToken getchecktoken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
		
		
	}
	

}
