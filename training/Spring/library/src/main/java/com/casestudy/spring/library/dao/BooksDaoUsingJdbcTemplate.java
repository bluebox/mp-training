package com.casestudy.spring.library.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.casestudy.spring.library.beans.Book;
import com.casestudy.spring.library.dao.models.BooksDaoModel;
import com.casestudy.spring.library.rowmapper.BookRowMapper;

@Repository
public class BooksDaoUsingJdbcTemplate implements BooksDaoModel{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public BooksDaoUsingJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookAvailability(int bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> viewAllBooks() {
		String sql = "SELECT bookId,title,author,category,status,availability from Books";
		List<Book> books = jdbcTemplate.query(sql, new BookRowMapper());
		return books;
	}

	@Override
	public boolean CanBeIssued(int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book searchBook(int tempId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findBook(int tempId) {
		// TODO Auto-generated method stub
		return false;
	}

}
