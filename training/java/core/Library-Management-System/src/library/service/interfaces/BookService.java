package library.service.interfaces;

import java.util.List;
import java.util.Map;

import library.model.Book;

public interface BookService {
	void addBook(Book book, String createdBy);

	List<Book> findBooks(Map<String, Object> criteria);

	List<Book> getAllBooks();

	Book getBookById(int bookId);

	boolean updateBook(Book book, String updatedBy);

	boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy);

	boolean deleteBook(int bookId);

	Map<String, Long> getBooksCountByCategory();

	boolean[] deleteBooksBatch(List<Integer> bookIds);

}