package com.library.app.rowMappers;

import java.sql.*;

import org.springframework.jdbc.core.RowMapper;

import com.library.app.model.Availability;
import com.library.app.model.Book;
import com.library.app.model.Status;

public class BookRowMapper implements RowMapper<Book>{
	 	@Override
	    public Book mapRow(ResultSet rs, int rowNumber) throws SQLException {
	        Book book = new Book();
	        book.setBookId(rs.getInt("BookId"));
	        book.setTitle(rs.getString("Title"));
			book.setAuthor(rs.getString("Author"));
			book.setCategory(rs.getString("Category"));
			String statusCharacter = rs.getString("Status");
			if ("A".equalsIgnoreCase(statusCharacter)) {
			    book.setStatus(Status.ACTIVE);
			} 
			else {
			    book.setStatus(Status.INACTIVE);
			}
			
			String availabilityCharacter = rs.getString("Availability");
			
			if ("A".equalsIgnoreCase(availabilityCharacter)) {
			    book.setAvailability(Availability.AVAILABLE);
			} 
			else {
			    book.setAvailability(Availability.ISSUED);
			}
	        return book;
	    }
}
