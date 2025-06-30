package com.casestudy.spring.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.casestudy.spring.library.beans.Book;
import com.casestudy.spring.library.impl.Implementation;

import jakarta.validation.Valid;

@Controller
public class BookController {
	@Autowired
	Implementation impl;

	@GetMapping("/AddBook")
	public String showAddBookForm(Model model) {
		model.addAttribute("book", new Book());
		return "AddBook";
	}

	@PostMapping("/SaveBook")
	public String AddBookToDb(@Valid @ModelAttribute Book book,Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("book", book);
			return "AddBook";
		}
		boolean flag = impl.addBook(book);
		if (flag == true) {
			model.addAttribute("message", "Book added successfully!");
			model.addAttribute("targetUrl", "/AddBook");
			model.addAttribute("buttonLabel", "Go to AddBook");
			return "Response";
		} else {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}

	}

	@GetMapping("/UpdateBook")
	public String showEmptyUpdateForm(Model model) {
		return "SearchBook";
	}

	@GetMapping("/UpdateBookPopulate")
	public String showUpdateForm(@RequestParam("id") int id, Model model) {
		Book book = impl.getBookById(id);
		if (book != null) {
			model.addAttribute("book", book);
			return "UpdateBook"; 
		} else {
			model.addAttribute("message", "Book not found!");
			model.addAttribute("targetUrl", "/UpdateBook");
			model.addAttribute("buttonLabel", "Try Again");
			return "Response";
		}
	}

	@PostMapping("/UpdateSaveBook")
	public String updateBook(@Valid @ModelAttribute("book") Book book,Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("book", book);
			return "UpdateBook";
		}
		boolean flag = impl.updateBookService(book);
		if (flag == true) {
			model.addAttribute("message", "Book Updated successfully!");
			model.addAttribute("targetUrl", "/Books");
			model.addAttribute("buttonLabel", "Go to Books");
			return "Response";
		} else {
			model.addAttribute("message", "Error Occured!");
			model.addAttribute("targetUrl", "/MainMenu");
			model.addAttribute("buttonLabel", "Go to Main Menu");
			return "Response";
		}
	}

	@GetMapping("/ViewAllBooks")
	public String showAllBooks(Model model) {
		model.addAttribute("books", impl.viewAllBooksService());
		return "ShowAllBooks";
	}

}
