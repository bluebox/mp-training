package library.service.interfaces;

import java.util.List;
import library.model.Book;

public interface BookService {
	void addBook(Book book, String createdBy);

	List<Book> findBooks(Book criteria);

	Book getBookById(int bookId);

	boolean updateBook(Book book, String updatedBy);

	boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy);

	boolean deleteBook(int bookId);

	int deleteBooksBatch(List<Integer> bookIds);

	int updateBookAvailabilityBatch(List<Integer> bookIds, String updatedBy);
}