package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;

public interface BookInterface {
      List<Book> getBooks() throws IOException, SQLException;
	  int AddBook(Book book) throws SQLException;
	  Book updateBookDetails(Book book) throws SQLException;
	  Book getBookbyId(int BookId) throws SQLException;
	  Book updateAvailability(int id, BookAvailability availability) throws SQLException;
}
