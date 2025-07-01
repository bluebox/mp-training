package com.app.springDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.springDemo.controller.BookController;
import com.app.springDemo.model.Book;

@Service
public class BookService {
	Logger log =LoggerFactory.getLogger(BookController.class.getName());

	   public boolean saveBookDetails(Book book){
	        boolean isSaved = true;
	        log.info(book.toString());
	        return isSaved;
	   }
}
