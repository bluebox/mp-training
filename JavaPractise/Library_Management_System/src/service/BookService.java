package service;

import java.util.List;

import dao.bookDao;
import domain.Book;
import domain.BookStatus;
import exceptions.DatabaseException;
import domain.AvailabilityStatus;

public class BookService {
	bookDao bd;
	
	public BookService(bookDao bd) {
		this.bd = bd;
	}
	public void addBook(Book book) throws DatabaseException {
		bd.addBook(book);
	}
	public void updateBookDetails(int id, String title, String author, String category, BookStatus status) throws DatabaseException  {	
		bd.updateBookDetails(id,title,author,category,status);
		
	}
	public void updateBookAvailability(int id, AvailabilityStatus status) throws DatabaseException {
		bd.updateBookAvailability(id,status);
		
	}
	public  List<Book> getAllBooks() throws DatabaseException {
		return bd.getAllBooks();
	}
	public boolean bookExists(int id) throws DatabaseException {
	    return bd.bookExists(id);
	}

}
