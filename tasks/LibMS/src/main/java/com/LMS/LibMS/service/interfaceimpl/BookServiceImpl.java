package com.LMS.LibMS.service.interfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.repository.BookRepository;
import com.LMS.LibMS.service.interfaces.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> findBooks(){
		return bookRepository.findBooks();
	}
	
	

}
