package com.example.library.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.daoImpl.BookDaoImpl;
import com.example.library.domain.Book;
import com.example.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDaoImpl bookDao;

	@Override
	public int addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public void updateBook(int id, Book book) {
		book.setBookId(id);
		bookDao.updateBook(book);
	}

	@Override
	public void updateAvailability(int bookId, String availability) {
		bookDao.updateAvailability(bookId, availability);
	}

	@Override
	public void deleteBook(int bookId) {
		bookDao.deleteBook(bookId);
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.findById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.findAllBooks();
	}

	@Override
	public List<Book> getBooksByMember(int memberId) {
		return bookDao.getBooksByMember(memberId);
	}

}
