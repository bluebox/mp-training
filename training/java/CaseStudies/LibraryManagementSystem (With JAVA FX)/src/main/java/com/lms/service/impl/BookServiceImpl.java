package com.lms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lms.Utils.ValidatorsUtil;
import com.lms.dao.BookDao;
import com.lms.dao.DataBookDao;
import com.lms.exceptions.DBConstrainsException;
import com.lms.model.Book;
import com.lms.service.BookService;

public class BookServiceImpl implements BookService{
	private BookDao bookDao;
	
	public BookServiceImpl() {
		this.bookDao = new DataBookDao();
	}
	

	@Override
	public void addNewBook(Book newBook) throws DBConstrainsException {
			ValidatorsUtil.validateBook(newBook);
			bookDao.addBook(newBook);	
	}

	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	@Override
	public void updateBookAvailability(int bookId,boolean isAvailable) {
		bookDao.updateBookAvailability(bookId, isAvailable);
	}
	
	@Override
	public void updateBook(Book book) throws DBConstrainsException {
			ValidatorsUtil.validateBook(book);
			bookDao.updateBook(book);
	}
	
	//Report 
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
