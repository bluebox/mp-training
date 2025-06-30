package com.library.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.domain.Book;
import com.library.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book, RedirectAttributes ra) {
        boolean added = bookService.createBook(book);
        
        ra.addFlashAttribute("message", added ? "Book added successfully." : "Failed to add book.");
        return "redirect:/books/list";
    }

    @GetMapping("/list")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "view_books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "update_book";
    }
    
    @GetMapping("/search")
    public String showSearchPage() {
        return "search_book"; // name of Thymeleaf file (search_book.html)
    }

    // Handle form submission
    @PostMapping("/search")
    public String searchBookById(@RequestParam("bookId") int bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            return "redirect:/books/edit/" + bookId;
        } else {
            model.addAttribute("error", "Book not found with ID: " + bookId);
        }
        return "search_book";
    }
    

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, RedirectAttributes ra) {
        boolean updated = bookService.updateBook(book);
        ra.addFlashAttribute("message", updated ? "Book updated." : "Update failed.");
        return "redirect:/books/list";
    }
    
    @GetMapping("/category-count")
    public String getBooksCountPerCategory(Model model) {
        Map<String, Long> categoryCountMap = bookService.getBooksCountPerCategory();
        model.addAttribute("categoryCountMap", categoryCountMap);
        return "category_stats"; // name of the Thymeleaf template (category_stats.html)
    }
}

