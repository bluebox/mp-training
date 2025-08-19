package com.example.web.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.annotations.ValidateBooks;
import com.example.web.dao.BookDao;
import com.example.web.model.Book;
import com.example.web.service.BookService;
@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;

	@Override
	@ValidateBooks
	public void addNewBook(Book newBook){
			bookDao.addBook(newBook);	
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	@Override
	@ValidateBooks
	public void updateBookAvailability(int bookId,boolean isAvailable) {
		bookDao.updateBookAvailability(bookId, isAvailable);
	}
	
	@Override
	@ValidateBooks
	public void updateBook(Book book){
			bookDao.updateBook(book);
	}
	
	public Map<String,Long> getBooksByCategory() {
		Map<String,Long> categoryMap = bookDao.getAllBooks().stream()
										.collect(Collectors.groupingBy((b)->b.getBook_Category(),Collectors.counting()));
		return categoryMap;
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.getBookById(id);
	}
}
