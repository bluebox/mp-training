package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.BookAvailability;

@Repository
public class BookRepository implements BookRepositoryInterface{

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	 @Autowired
	private BookRowMapperImplementation rowmapper;
	 
	@Override
	public List<Book> getBooks() throws IOException, SQLException {
		  String query ="select BookId,Title,Author,Category,Status,Availablity from Books ";
		  List<Book> books=jdbcTemplate.query(query,rowmapper);
		  if(books == null) {
			  return null;
		  }
		  return books;
	}

	@Override
	public int AddBook(Book book) throws SQLException {
		String query="insert into books (Title,Author,Category,status,Availablity) values(?,?,?,?,?) ";
		int value=0;
		value=jdbcTemplate.update(query,book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
		if(value>0) {
			return value;
		}
		return 0;
	}

	@Override
	public Book updateBookDetails(Book book) throws SQLException {
		String query="update books set Category=?,status=? where BookId=? ";
		String logquery="insert into bookslog (BookId,Title,Author,Category,status,Availablity) values(?,?,?,?,?,?) ";
		int logvalue=jdbcTemplate.update(logquery,book.getBookId(),book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
		int value=jdbcTemplate.update(query,book.getCategory(),book.getStatus().getType(),book.getBookId());
		if(logvalue>0 && value>0) {
			return getBookbyId(book.getBookId());
		}
		return null;
	}

	@Override
	public Book getBookbyId(int BookId) throws SQLException {
		String query="Select BookId,Title,Author,Category,Status,Availablity from Books where BookId=?";
		Book book=jdbcTemplate.queryForObject(query,rowmapper,BookId);
		if(book == null) {
			return null;
		}
		return book;
	}

	@Override
	public Book updateAvailability(int id, BookAvailability availability) throws SQLException {
		String query="update books set Availablity=? where BookId=? ";
		String logquery="insert into bookslog (BookId,Title,Author,Category,status,Availablity) values(?,?,?,?,?,?) ";
		Book book=getBookbyId(id);
		int logvalue=jdbcTemplate.update(logquery,book.getBookId(),book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
		int value=jdbcTemplate.update(query,book.getAvailability().getType(),book.getBookId());
		if(logvalue>0 && value>0) {
			return getBookbyId(book.getBookId());
		}
		return null;
	}

}
