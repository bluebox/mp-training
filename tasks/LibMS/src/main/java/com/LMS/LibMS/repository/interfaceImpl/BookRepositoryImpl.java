package com.LMS.LibMS.repository.interfaceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.repository.interfaces.BookRepository;
import com.LMS.LibMS.rowmapper.BookRowMapper;

@Repository
public class BookRepositoryImpl implements BookRepository{
	
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public BookRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate=namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Book> findBooks(){
		
		String sqlString = "SELECT * FROM books";
		
		return namedParameterJdbcTemplate.query(sqlString, new BookRowMapper());
	}
	

}
