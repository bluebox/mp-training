package service;

import java.util.List;

import dao.bookDao;
import domain.Book;
import domain.BookStatus;
import domain.AvailabilityStatus;

public class BookService {
	bookDao bd;
	
	public BookService(bookDao bd) {
		this.bd = bd;
	}
	public void addBook(Book book) {
		bd.addBook(book);
	}
	public void updateBookDetails(int id, String title, String author, String category, BookStatus status) {	
		bd.updateBookDetails(id,title,author,category,status);
		
	}
	public void updateBookAvailability(int id, AvailabilityStatus status) {
		bd.updateBookAvailability(id,status);
		
	}
	public  List<Book> getAllBooks() {
		return bd.getAllBooks();
		
	}
}
