package com.lms.service;



import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.BookCategory;

public interface BookService {
	public void addBook(String title, String author, BookCategory category, Character status, Character availability)throws InvalidInputException;

	
}