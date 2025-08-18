package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.BookAvailability;
import com.SpringBoot_LMS.SpringBoot_LMS.model.BookStatus;

@Component
public class BookRowMapperImplementation implements RowMapper<Book> {
	
//	@Autowired
//	private Book book;
	
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book=new Book();
	    book.setBookId(rs.getInt("BookId"));
	    book.setTitle(rs.getString("Title"));
	    book.setAuthor(rs.getString("Author"));
	    book.setCategory(rs.getString("Category"));
	    book.setAvailability(BookAvailability.getAvailability(rs.getString("Availablity")));
	    book.setStatus(BookStatus.getStatus(rs.getString("Status")));
	    System.err.println("book ID "+rs.getInt("BookId"));
		return book;
	}

}
