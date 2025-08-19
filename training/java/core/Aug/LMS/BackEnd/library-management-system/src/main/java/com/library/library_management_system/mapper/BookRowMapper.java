package com.library.library_management_system.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.utils.BookAvailability;
import com.library.library_management_system.utils.BookCategory;
import com.library.library_management_system.utils.BookStatus;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

		Book book = new Book();
		book.setId(rs.getInt("book_id"));
		book.setAuthor(rs.getString("author"));
		book.setTitle(rs.getString("title"));
		book.setCategory(BookCategory.fromDisplayName(rs.getString("category")));
		book.setStatus(BookStatus.fromDbName(rs.getString("status")));
		book.setAvailability(BookAvailability.fromDbName(rs.getString("availability")));

		return book;
	}

}
