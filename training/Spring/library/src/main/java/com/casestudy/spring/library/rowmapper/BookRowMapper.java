package com.casestudy.spring.library.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.casestudy.spring.library.beans.Availability;
import com.casestudy.spring.library.beans.Book;
import com.casestudy.spring.library.beans.Status;

public class BookRowMapper implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setBookId(rs.getInt("bookId"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setCategory(rs.getString("category"));
		book.setStatus(Status.fromCode(rs.getString("status")));
		book.setAvailable(Availability.fromCode(rs.getString("availability")));
		return book;
	}

}
