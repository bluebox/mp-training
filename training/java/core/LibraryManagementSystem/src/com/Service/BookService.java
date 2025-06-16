package com.Service;

import com.Dao.BookDao;
import com.Exception.DatabaseException;
import com.Exception.InvalidInputException;
import com.Models.Book;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class BookService {
    private final BookDao bookDAO = new BookDao();

    public void addBook(Book book) throws InvalidInputException, DatabaseException, SQLException {
        validateBook(book);
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws InvalidInputException, DatabaseException, SQLException {
        if (book.getBookId() <= 0) throw new InvalidInputException("Invalid book ID");
        validateBook(book);
        bookDAO.updateBook(book);
    }

    public void updateAvailability(int bookId, char availability) throws DatabaseException, SQLException {
        bookDAO.updateAvailability(bookId, availability);
    }

    public List<Book> getAllBooks() throws DatabaseException, SQLException {
        return bookDAO.getAllBooks();
    }

    private void validateBook(Book book) throws InvalidInputException {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty())
            throw new InvalidInputException("Book title cannot be empty");
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty())
            throw new InvalidInputException("Book author cannot be empty");
        if (book.getCategory() == null || book.getCategory().trim().isEmpty())
            throw new InvalidInputException("Book category cannot be empty");
    }

	public void countBooksPerCategory() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String,Integer> hm=bookDAO.countBooksPerCategor();
		for(String s:hm.keySet()) {
			System.out.println(s+" -> "+hm.get(s));
		}
	}
}

