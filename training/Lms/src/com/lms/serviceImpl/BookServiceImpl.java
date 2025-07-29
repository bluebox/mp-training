

package com.lms.serviceImpl;

import java.util.List;

import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.BookCategory;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.util.Validator;
public class BookServiceImpl implements BookService {

    
	public void addBook(String title, String author, BookCategory category, Character status, Character availability) throws InvalidInputException {
	    Validator.serviceValidateBookName(title);
	    Validator.serviceValidateAuthor(author);

	    boolean exists = BookDao.getInstance().isTitleExists(title);
	    if (exists) {
	        throw new InvalidInputException("Book with this title already exists.");
	    }

	    BookDao.getInstance().addBook(title, author, category, status, availability);
	}
	
	public Boolean updateBook(String bookId, String title, String author, BookCategory category, Character status, Character availability) throws InvalidInputException {
		Validator.serviceValidateBookId(bookId);
		Validator.serviceValidateBookName(title);
		Validator.serviceValidateAuthor(author);
		Book updatedBookTitle= getBookById(bookId);
		if (!updatedBookTitle.getBookTitle().equals(title)) {
		    boolean exists = BookDao.getInstance().isTitleExists(title);
		    if (exists) {
		        throw new InvalidInputException("Book with this title already exists.");
		    }

		}
		
	    //boolean exists = BookDao.getInstance().isTitleExists(title);
//	    if (exists) {
//	        throw new InvalidInputException("Book with this title already exists.");
//	    }

		
		Boolean updatedBook = BookDao.getInstance().updateBook(bookId, title, author, category, status, availability);
		return updatedBook;
	}
	public Book getBookById(String bookId) throws InvalidInputException {

		Book currentBook = null;
        for (Book book : BookDao.getInstance().getBooks()) {
            if (book.getBookId().equals(bookId)) {
                currentBook = book;
                break;
            }
        }
		
		if (currentBook == null) {
			throw new InvalidInputException("Book not found with ID: " + bookId);
		}
		
		return currentBook;
	}
	
	public List<Book> getAllBooks() {
		return BookDao.getInstance().getBooks();
	}
	
    
}
