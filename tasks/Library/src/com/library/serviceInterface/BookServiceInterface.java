package com.library.serviceInterface;

import java.io.IOException;
import java.util.List;

import com.library.domain.Book;

public interface BookServiceInterface {
	
		public abstract boolean addBook(Book book);
	    
	    public abstract boolean updateBookAvailability(Book book, char status) throws IOException;

	    public abstract List<Book> getAllBooks();
	    
	    public abstract void returnBook(int bookId) throws Exception;
	    
	    public abstract void updateBook(Book book);

}
