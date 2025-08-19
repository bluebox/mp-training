package dev.tulasidhar.lms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tulasidhar.lms.Utils.ValidatorsUtil;
import dev.tulasidhar.lms.DAO.BookDao;
import dev.tulasidhar.lms.Exceptions.DBConstrainsException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;
	
//	public BookServiceImpl() {
//		this.bookDao = new DataBookDao();
//	}
	

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
