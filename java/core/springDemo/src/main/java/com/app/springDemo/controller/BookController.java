package com.app.springDemo.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.app.springDemo.model.Book;
import com.app.springDemo.service.BookService;

import jakarta.validation.Valid;

@Controller
public class BookController {
	Logger log =LoggerFactory.getLogger(BookController.class.getName());
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService=bookService;
	}


    @RequestMapping("/addBook")
    public String displaybookPage(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    /*@RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/book");
    }*/

    @RequestMapping(value = "/saveBook",method = POST)
    public String saveMessage(@Valid @ModelAttribute("book") Book book, Errors errors){

        if(errors.hasErrors()){
            log.error("book form validation failed due to : " + errors.toString());
            return "addBook";
        }
        bookService.saveBookDetails(book);
        return "redirect:/addBook";
    }
}
