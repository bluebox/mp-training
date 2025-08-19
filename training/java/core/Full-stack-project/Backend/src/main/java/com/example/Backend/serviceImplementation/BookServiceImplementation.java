package com.example.Backend.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.daoImplementation.BookDaoImpl;
import com.example.Backend.domain.Book;

@Service
public class BookServiceImplementation {

	@Autowired
	private BookDaoImpl bookDaoImpl;

	public int addBook(Book book) {

		return bookDaoImpl.addBook(book);
	}

	public void updateBook(int id, Book book) {
		book.setBookId(id);
		bookDaoImpl.updateBook(book);
	}

	public void deleteBook(int bookId) {
		bookDaoImpl.deleteBook(bookId);
	}

	public Book getBookById(int id) {
		return bookDaoImpl.findById(id);
	}

	public List<Book> getAllBooks() {
		return bookDaoImpl.findAllBooks();
	}

}
