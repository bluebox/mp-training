package com.LMS.LibMS.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.model.enums.BookAvailability;
import com.LMS.LibMS.model.enums.BookCategory;
import com.LMS.LibMS.model.enums.BookStatus;
import com.LMS.LibMS.service.interfaces.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/add")
	public void addBook(@Valid @RequestBody Book book) throws Exception  {
//			try {
				System.out.println(book);
				bookService.addBook(book);
//				return "Book added successfully";
//			} catch (Exception e) {
//				return e.getMessage();
//			}
	}

	@GetMapping("/getbooks")
	@ResponseBody
	public List<Book> displayBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/getbook/{bookId}")
	@ResponseBody
	public Book getBookById(@PathVariable("bookId") Integer bookId) {
		return bookService.findBookById(bookId);
	}
	
	@GetMapping("/dropdowns")
	@ResponseBody
	public Map<String, List<Map<String, String>>> getDropdowns() {
	    Map<String, List<Map<String, String>>> response = new HashMap<>();

	    List<Map<String, String>> categories = Arrays.stream(BookCategory.values())
	            .map(category -> Map.of("displayName", category.name(), "code", category.name()))
	            .collect(Collectors.toList());

	    List<Map<String, String>> statuses = Arrays.stream(BookStatus.values())
	            .map(status -> Map.of("displayName", status.name(), "code", status.name()))
	            .collect(Collectors.toList());

	    List<Map<String, String>> availabilities = Arrays.stream(BookAvailability.values())
	            .map(availability -> Map.of("displayName", availability.name(), "code", availability.name()))
	            .collect(Collectors.toList());

	    response.put("categories", categories);
	    response.put("statuses", statuses);
	    response.put("availabilities", availabilities);

	    return response;
	}


	@PutMapping("/update/{bookId}")
	public boolean updateBook(@PathVariable("bookId") Integer bookId,@Valid @RequestBody Book book) throws Exception  {
		book.setBookId(bookId);
			return bookService.updateBook(book);
	}

	@PostMapping("/changeAvailability")
	public boolean changeAvailability(@RequestBody Map<String, Integer> data) throws Exception {
		Integer bookId = data.get("bookId");
			return bookService.updateAvailabilitiesById(Arrays.asList(bookId));
	}
	
	@PostMapping("/updateAvailabilities")
	public boolean updateAvailabilities(@RequestBody Map<String, List<Integer>> data) throws Exception {
		List<Integer> ids = data.get("ids");

		return	bookService.updateAvailabilitiesById(ids);

	}
	
	@DeleteMapping("/delete/{bookId}")
	public boolean deleteBook(@PathVariable("bookId") Integer bookId) throws Exception {
			return bookService.makeBookInactiveById(bookId);
	}

	@PostMapping("/deleteBooks")
	public boolean deleteBooks(@RequestBody Map<String, List<Integer>> data) throws Exception {
		List<Integer> ids = data.get("ids");
		return	bookService.deleteBooksById(ids);

	}

}