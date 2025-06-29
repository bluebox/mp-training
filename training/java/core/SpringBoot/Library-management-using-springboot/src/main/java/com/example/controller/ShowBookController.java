package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Books;
import com.example.service.BookService;

@Controller
public class ShowBookController {
	@Autowired
	private BookService bs;
	@RequestMapping("/show")
	public String show(Model m) {
		m.addAttribute("l", bs.showBooks());
		return "show.html";
	}
	@RequestMapping("/add")
	public String add() {
		return "add.html";
	}
	@RequestMapping("/addData")
	public String addData(Model m,@RequestParam(required = false) Long bookId,@RequestParam(required = false) String title,@RequestParam(required = false) String author,@RequestParam(required = false) String category) {
	    long bid = 0;
	    if (bookId != null) {
	        bid = bookId;
	    }
	    String s = bs.add(new Books(bid, title, author, category));
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
		if(bs.update(bookId, title, author, category, status, availability).equals("updated")) {
			System.out.println("updated");
			return show(m);
		}
		return "add.html";
	}
	@RequestMapping("/delete")
	public String delete(Model m,long bookId) {
		if(bs.delete(bookId).equals("deleted")) {
			return show(m);
		}
		else {
			return "show.html";
		}
	}
	@RequestMapping("/updateStatus")
	public String updateStatus(Model m,long bookId) {
		if(bs.changeStatus(bookId).equals("updated")) {
			return show(m);
		}
		else {
			return add();
		}
	}
}
