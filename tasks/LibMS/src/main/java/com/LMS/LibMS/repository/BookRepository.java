package com.LMS.LibMS.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.rowmapper.BookRowMapper;

@Repository
public class BookRepository {
	
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	public List<Book> findBooks(){
		
		String sqlString = "SELECT * FROM books";
		
		return namedParameterJdbcTemplate.query(sqlString, new BookRowMapper());
	}
	

}
