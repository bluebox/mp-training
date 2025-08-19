package dev.tulasidhar.lms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import dev.tulasidhar.lms.Utils.ValidatorsUtil;
import dev.tulasidhar.lms.DAO.BookDao;
import dev.tulasidhar.lms.Exceptions.NoRowsEffectedException;
import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.model.Book;
import dev.tulasidhar.lms.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;


	@Override
	public int addNewBook(Book newBook) throws ValidationException, NoRowsEffectedException{
		try {
			ValidatorsUtil.validateBook(newBook);
			int rowsEffected = bookDao.addBook(newBook);
			if(rowsEffected == 0)
				throw new NoRowsEffectedException("Couldn't complete the task , please try again");
			return rowsEffected;
		}catch(DataAccessException e){
			return 1;
		} 	
	}

	
	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	@Override
	public int updateBookAvailability(int bookId,boolean isAvailable) throws DataAccessException{
		return bookDao.updateBookAvailability(bookId, isAvailable);
	}
	
	
	@Override
	public int updateBook(Book book) throws ValidationException,DataAccessException {
			ValidatorsUtil.validateBook(book);
			return bookDao.updateBook(book);
	}
	
	//Report 
	public Map<String,Long> getBooksByCategory() throws DataAccessException{
		Map<String,Long> categoryMap = bookDao.getAllBooks().stream()
										.collect(Collectors.groupingBy((b)->b.getBook_Category(),Collectors.counting()));
		
		return categoryMap;
	}

	@Override
	public Book getBookById(int id) throws DataAccessException{
		return bookDao.getBookById(id);
	}
}
