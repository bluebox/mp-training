package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.library.dto.ApiResponse;
import com.example.library.model.Book;
import com.example.library.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("")
	public String viewBooks(Model model) throws Exception {
		model.addAttribute("books", bookService.getAllBooks());
		return "books/viewBooks";
	}
	
	@GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Book>>> viewBooks() throws Exception {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse<>(true, "Books fetched successfully", books));
    }

	@GetMapping("/add")
	public String addBookForm(Model model) {
		model.addAttribute("book", new Book(null, null));
		return "books/addBook";
	}

	@PostMapping("/add")
	public String addBook(@ModelAttribute Book book, Model model) throws Exception {
		bookService.addBook(book);
		return "redirect:/books";
	}

	@GetMapping("/update")
	public String updateBookForm( Model model) throws Exception {

		model.addAttribute("book", new Book(null, null));

		return "books/updateBook";
	}

	@PostMapping("/update")
	public String updateBook(@ModelAttribute Book book, Model model) throws Exception {
		bookService.updateBook(book);
		return "redirect:/books";
	}

	@GetMapping("/category-count")
	public String bookCategoryCount(Model model) throws Exception {
		model.addAttribute("categoryCounts", bookService.getBookCountPerCategory());
		return "books/bookCategoryCount";
	}
}