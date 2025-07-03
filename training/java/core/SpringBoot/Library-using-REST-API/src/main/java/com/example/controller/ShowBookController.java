package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Books;
import com.example.service.BookService;

@Controller
public class ShowBookController {
	@Autowired
	private BookService bs;
	@RequestMapping(value={"","/","/main"})
	public String mainpage() {
		return "main.html";
	}
	@RequestMapping("/show")
	public String show(Model m) {
		m.addAttribute("l", bs.showBooks());
		return "show.html";
	}
	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("books", new Books());
		return "add.html";
	}
	@PostMapping("/addData")
	public String addData(@Valid @ModelAttribute("books") Books b,BindingResult br, Model m,@RequestParam(required = false) Long bookId,@RequestParam(required = false) String title,@RequestParam(required = false) String author,@RequestParam(required = false) String category) {
	    if(br.hasErrors()) {
	    	return "addData.html";
	    }
		String s = bs.add(new Books(bookId, title, author, category));
	    if ("Insertion is done".equals(s)) {
	        return show(m);
	    } else {
	        m.addAttribute("error", s);
	        return "add.html";
	    }
	}
	@RequestMapping("/update")
	public String update(Model m,@RequestParam(required = false) long bookId) {
		m.addAttribute("bookId",bookId);
		return "update.html";
	}
	@RequestMapping("/updatedata")
	public String updateData(Model m,@RequestParam(required = false) Long bookId,@RequestParam(required = false) String title,@RequestParam(required = false) String author,@RequestParam(required = false) String category,@RequestParam(required = false) char status,@RequestParam(required = false) char availability) {
		String s=bs.update(bookId, title, author, category, status, availability);
		if(s.equals("updated")) {
			System.out.println("updated");
			return show(m);
		}
		m.addAttribute("error", s);
		return "add.html";
	}
	@RequestMapping("/delete")
	public String delete(Model m,long bookId) {
		String s=bs.delete(bookId);
		if(s.equals("deleted")) {
			return show(m);
		}
		else {
			m.addAttribute("error", s);
			return "show.html";
		}
	}
	@RequestMapping("/updateStatus")
	public String updateStatus(Model m,long bookId) {
		String s=bs.changeStatus(bookId);
		if(s.equals("updated")) {
			return show(m);
		}
		else {
			m.addAttribute("error",s);
			return add(m);
		}
	}
}
