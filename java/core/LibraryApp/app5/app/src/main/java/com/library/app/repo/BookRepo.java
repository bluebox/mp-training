package com.library.app.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.library.app.model.*;
import com.library.app.rowMappers.BookRowMapper;

@Repository
@Transactional
public class BookRepo {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookRepo(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addBook(Book book) throws DataAccessException {
		String bookSql = "insert into books (Title, Author, Category, Status, Availability) values (?, ?, ?, ?, ?)";
		String bookLogSql = "insert into book_log (Title, Author, Category, Status, Availability,OperationType) values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(bookSql, book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus().getval(),
				book.getAvailability().getval());
		System.out.println("Book added Successfully!");
		return jdbcTemplate.update(bookLogSql, book.getTitle(), book.getAuthor(), book.getCategory(),
				book.getStatus().getval(), book.getAvailability().getval(), "Insert");

	}

	public int updateBookDetails(Book book) throws Exception{
		String bookUpdateSql = "update books set Title=?, Author=?, Category=?, Status=?,Availability=? where BookId=?";
		String bookLogUpdateSql = "insert into book_log (BookId,Title, Author, Category, Status, Availability,OperationType) values (?, ?, ?, ?, ?, ?, ?)";
		int rowsChanged=jdbcTemplate.update(bookUpdateSql, book.getTitle(), book.getAuthor(), book.getCategory(),
				book.getStatus().getval(), book.getAvailability().getval(), book.getBookId());
		
		jdbcTemplate.update(bookLogUpdateSql, book.getBookId(), book.getTitle(), book.getAuthor(), 
				book.getCategory(),book.getStatus().getval(), book.getAvailability().getval(), "Update");
		if (rowsChanged == 0) {
		    throw new Exception("No book found with this BookId : " + book.getBookId());
		}
		return rowsChanged;
	}

	public int updateAvailability(int bookId, char availability) throws Exception{
		String updateAvailSql = "update books set Availability=? where BookId=?";
		int rowsChanged=jdbcTemplate.update(updateAvailSql, String.valueOf(availability), bookId);
		if (rowsChanged == 0) {
		    throw new Exception("No book found with BookId : " + bookId);
		}
		return rowsChanged;

	}
	
	public  List<Book> getAllBooks() throws DataAccessException{
		String sql="select * from books";
		return jdbcTemplate.query(sql,new BookRowMapper());
		
	}
	public Book getBookById(int bookId) throws Exception{
		String sql="select * from books where BookId=?";
		Book book=null;
		try {
		book=jdbcTemplate.queryForObject(sql, new BookRowMapper(), bookId);
		}
		catch(Exception e) {
		    throw new Exception("No book found with this BookId : " + bookId);
		}
		return book;
	}
}
