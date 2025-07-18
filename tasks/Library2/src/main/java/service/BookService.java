package service;

import java.util.List;

import model.Book;
import model.BookCategoryCount;

public interface BookService {
	void addBook(Book book) throws Exception;

	List<Book> getAllBooks() throws Exception;

	void updateBook(Book book) throws Exception;

	void updateBookAvailability(Integer bookId, Character availability) throws Exception;

	List<BookCategoryCount> getBookCountPerCategory() throws Exception;
}
