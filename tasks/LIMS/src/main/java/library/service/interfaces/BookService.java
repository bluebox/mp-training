package library.service.interfaces;

import java.util.List;
import java.util.Map;

import library.model.Book;

public interface BookService {
	
	void addBook(Book book, String createdBy);

	List<Book> findBooks(Map<String, Object> criteria);

	boolean updateBook(Book book, String updatedBy);
	
	boolean updateBookAvailability(List<Integer>bookIds,String updatedBy);

	boolean deleteBooks(List<Integer> bookIds);
	
}