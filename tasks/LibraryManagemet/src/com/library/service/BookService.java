package com.library.service;

import java.util.List;

import com.library.dao.BookDAO;
import com.library.domain.Book;

public class BookService {
	private BookDAO bookdao=new BookDAO();
	
	public List<Book> viewAllBooks()
	{
		return bookdao.viewBooks();
			
	}

}
