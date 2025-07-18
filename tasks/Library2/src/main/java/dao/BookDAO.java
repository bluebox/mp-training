package dao;

import model.Book;
import Exception.DatabaseException;
import java.util.List;

public interface BookDAO {
    Book getBookById(int bookId) throws DatabaseException;
    void addBook(Book book) throws DatabaseException;
    List<Book> getAllBooks() throws Exception;
    void updateBook(Book book) throws Exception;
    void updateBookAvailability(int bookId, Character availability) throws Exception;
}
