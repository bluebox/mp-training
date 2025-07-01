package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Books;

@Service
public class BookService {
	 @Autowired
	 private com.example.repository.BookRepository repo;
	 
	 public String add(Books b) {
	 	return repo.insert(b);
	 }

	 public ArrayList<Books> showBooks() {
		 ArrayList<Books> l = new ArrayList<Books>();
		 List<Map<String, Object>> m = repo.showAll();
		 for(Map<String, Object> i:m) {
			 l.add(new Books(Long.parseLong(String.valueOf(i.get("BookId"))), String.valueOf(i.get("Title")), String.valueOf(i.get("Author")), String.valueOf(i.get("Category"))));
		 }
		 return l;
	}

	public String update(long BookId, String title, String author, String category, char status, char availability) {
		return repo.updateById(BookId, title, author, category, status, availability);
	}
	public String delete(long bookId) {
		if (repo.deleteById(bookId) > 0) {
			return "deleted";
		}
		return "not deleted";
	}

	public String changeStatus(long bookId) {
		if (repo.changeStatus(bookId) > 0) {
			return "updated";
		}
		return "not updated";
	}

}