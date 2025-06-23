package com.library.services;


import com.library.domain.Book;
import com.library.serviceInterface.BookServiceInterface;
import com.library.dao.BookDAO;

import java.io.IOException;
import java.util.List;

public class BookService implements BookServiceInterface{
    private BookDAO bookDAO = new BookDAO();

    @Override
    public boolean addBook(Book book) {

        return bookDAO.addBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

	@Override
	public boolean updateBookAvailability(Book book, char status) throws IOException {
		
		return false;
	}

	@Override
	public void updateBook(Book book) {
		
		
	}


}