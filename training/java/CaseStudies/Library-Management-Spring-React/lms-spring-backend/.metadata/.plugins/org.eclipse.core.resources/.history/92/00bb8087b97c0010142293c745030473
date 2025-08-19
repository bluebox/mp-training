package dev.tulasidhar.lms.DAO.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.tulasidhar.lms.DAO.BookDao;
import dev.tulasidhar.lms.DAO.mappers.BookRowMapper;
import dev.tulasidhar.lms.model.Book;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DataBookDao implements BookDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public int addBook(Book newBook) throws DataAccessException{
		int rowsEffected = jdbcTemplate.update(
				"INSERT INTO books(Title,Author,Category,BookStatus,Availability) VALUES (?,?,?,?,?)",
				newBook.getBook_Title(), newBook.getBook_Author(), newBook.getBook_Category(),
				String.valueOf(newBook.getBook_Status()), String.valueOf(newBook.getBook_Availability()));

		return rowsEffected;
	}


	@Override
	public List<Book> getAllBooks() throws DataAccessException {
		String query = "SELECT BookId,Title,Author,Category,BookStatus,Availability FROM books;";
		List<Book> books = jdbcTemplate.query(query, new BookRowMapper());

		return books;
	}

	@Override
	public int updateBook(Book book) throws DataAccessException {
		String query = "UPDATE books SET Title=?, Author=?, Category=?, BookStatus=? WHERE BookId=?";
		int rowsEffected = jdbcTemplate.update(query,book.getBook_Title(),
												book.getBook_Author(),
												book.getBook_Category(),
												String.valueOf(book.getBook_Status()),
												book.getBook_Id());
		
		return rowsEffected;
	}
	
	
	public int addBookLogs(Book book) throws DataAccessException {
		String query = "INSERT INTO books_log(BookId,Title,Author,Category,Status,Availability) VALUES (?,?,?,?,?,?);";
		
		int rowsEffected = jdbcTemplate.update(query,
							book.getBook_Id(),
							book.getBook_Title(),
							book.getBook_Author(),
							book.getBook_Category(),
							String.valueOf(book.getBook_Status()),
							String.valueOf(book.getBook_Availability()));
		
		return rowsEffected;
	}

	@Override
	public int updateBookAvailability(int bookId, boolean isAvailable) throws DataAccessException{
		String query = "UPDATE books SET Availability=? WHERE BookId=?";
		
		int rowsAffected = jdbcTemplate.update(query,
							isAvailable ? "A" : "I",
							bookId);
		return rowsAffected;	
		
	}

	@Override
	public Book getBookById(int bookId) throws DataAccessException{
		String query = "SELECT BookId,Title,Author,Category,BookStatus,Availability FROM books WHERE BookId=?";
		try {
			Book book = jdbcTemplate.queryForObject(query,new Object[]{bookId},new BookRowMapper());
			return book;
		}
		catch(DataAccessException e) {
			throw new RuntimeException("The book doesn't exist anymore : "+ e.getMessage());
		}
	}

}
