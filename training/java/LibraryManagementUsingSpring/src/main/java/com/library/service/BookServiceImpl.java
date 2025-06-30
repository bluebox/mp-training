package com.library.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dao.BooksDao;
import com.library.domain.Book;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksDao booksDao;

    public boolean createBook(Book book) {
        return booksDao.createBook(book);
    }

    public boolean updateBook(Book book) {
        return booksDao.updateBook(book);
    }

    public List<Book> getAllBooks() {
        return booksDao.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return booksDao.getBookById(bookId);
    }

    public void toggleAvailability(int bookId) {
        booksDao.toggleAvailability(bookId);
    }
    
    public Map<String, Long> getBooksCountPerCategory() {
		List<Book> books = booksDao.getAllBooks();
		Map<String, Long> categoryCountMap = books.stream()
				.collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
		return categoryCountMap;

	}
}
