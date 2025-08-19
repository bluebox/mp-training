package dev.tulasidhar.lms.DAO;


import java.util.List;
import dev.tulasidhar.lms.model.Book;

public interface BookDao {
	
	//TODO: return affected rows here insted of book
	Book addBook(Book newBook);
	List<Book> getAllBooks();
	
	//TODO: Same here return affected rows
	void updateBook(Book book);
	void updateBookAvailability(int bookId,boolean isavalable);
	public void addBookLogs(Book book);
	Book getBookById(int bookId);
}
