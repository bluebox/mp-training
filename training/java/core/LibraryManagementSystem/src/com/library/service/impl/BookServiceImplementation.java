package com.library.service.impl;

import java.util.List;

import com.library.dao.impl.BookDaoImplementation;
import com.library.dao.interfaces.BookDao;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.service.interfaces.BookService;

public class BookServiceImplementation implements BookService {
	
	BookDaoImplementation bdi=new BookDaoImplementation();
	public void addBook(Book book) throws Exception {
		bdi.addBook(book);
	}
	public List<Book> getAllBooks() {
		return bdi.getAllBooks();
		
	}
	 public void updateBookDetails(Book updatedBook) throws Exception {
	        if (updatedBook == null || updatedBook.getBookId() <= 0) {
	            throw new IllegalArgumentException("Invalid Book ID provided.");
	        }

	       
	        Book existingBook = bdi.getBookById(updatedBook.getBookId());
	        if (existingBook == null) {
	            throw new BookNotFoundException("Book with ID " + updatedBook.getBookId() + " not found.");
	        }

	        
	        String title = updatedBook.getTitle() != null ? updatedBook.getTitle() : existingBook.getTitle();
	        String author = updatedBook.getAuthor() != null ? updatedBook.getAuthor() : existingBook.getAuthor();
	        String category = updatedBook.getCategory() != null ? updatedBook.getCategory() : existingBook.getCategory();
	        char status = updatedBook.getStatus() != '\u0000' ? updatedBook.getStatus() : existingBook.getStatus();

	        
	        Book finalBook = new Book();
	        finalBook.setBookId(updatedBook.getBookId());
	        finalBook.setTitle(title);
	        finalBook.setAuthor(author);
	        finalBook.setCategory(category);
	        finalBook.setStatus(status);
	        finalBook.setAvailability(existingBook.getAvailability());

	       
	        bdi.updateBookDetails(finalBook);
	    }
	 public void updateAvailability(int bookId, char availability) throws Exception {
		    
		    
		    if (availability != 'A' && availability != 'I') {
		        throw new IllegalArgumentException("Availability must be 'A' (Available) or 'I' (Issued).");
		    }

		    Book book = bdi.getBookById(bookId);
		    if (book == null) {
		        throw new BookNotFoundException("Book with ID " + bookId + " not found.");
		    }

		   
		    bdi.updateBookAvailability(bookId, availability);
		}

	 public List<Book> getBooksBySearch(String columnName, String value) throws Exception {
		    if (columnName == null || value == null || columnName.trim().isEmpty() || value.trim().isEmpty()) {
		        throw new IllegalArgumentException("Column name and value must not be empty.");
		    }
		    return bdi.getBooks(columnName, value);
		}
	 public boolean bookExists(int bookId) throws Exception {
		    return bdi.getBookById(bookId) != null;
		}
	 @Override
	    public Book getBookById(int id) throws Exception {
	        return bdi.getBookById(id);
	    }

	

}
