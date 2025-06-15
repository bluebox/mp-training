package com.LibraryManagement.services.impl;

import java.util.ArrayList;

import com.LibraryManagement.dao.impl.BookDaoImpl;
import com.LibraryManagement.services.BookService;
import com.LibraryManagement.utilites.pojos.Book;

public class BookServiceImpl implements BookService{
	private BookDaoImpl bd=new BookDaoImpl();
	@Override
	public boolean addBookService(Book book) throws Exception {
		if(bd.verifyBook(book)) {
			bd.addBook(book);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Book> viewAllBooksService() throws Exception {
		return bd.viewAllBooks();
	}

	@Override
	public ArrayList<Book> viewAllBooksLogService() throws Exception {
		return bd.viewAllBooksLogs();
	}


	@Override
	public boolean updateBookService(int bookId, Character availability) {
		try {
			if(bd.verifyBook(bookId,availability)) {
				bd.updateBook(bookId, availability);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
