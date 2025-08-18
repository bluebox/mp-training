package com.SpringBoot_LMS.SpringBoot_LMS.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookrepository;
	
	public List<Book> getBooks() throws IOException, SQLException{
		List<Book> books=bookrepository.getBooks();
		return books;
	}
	
	public Book getbookbyId(int id) throws SQLException {
		Book book=bookrepository.getBookbyId(id);
		return book;
	}
	
	public int AddBook(Book book) throws SQLException {
		int value=bookrepository.AddBook(book);
		return value;
	}
	
	
	public Book updateBook(Book book) throws SQLException {
		Book value=bookrepository.updateBookDetails(book);
		return value;
	}
	
	
}
