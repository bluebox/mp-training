package com.LMS.LibMS.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.model.enums.BookAvailability;
import com.LMS.LibMS.model.enums.BookCategory;
import com.LMS.LibMS.model.enums.BookStatus;

public class BookRowMapper  implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		
		book.setTitle(rs.getString("BookId"));
		book.setAuthor(rs.getString("Title"));
		book.setCategory(BookCategory.fromDisplayName(rs.getString("Category")));
		book.setStatus(BookStatus.fromCode(rs.getString("Status")));
		book.setAvailability(BookAvailability.fromCode(rs.getString("Availablity")));
		book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		book.setCreatedBy(rs.getString("created_by"));
		if(rs.getTimestamp("updated_at")!=null) {
			book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());			
		}
		book.setUpdatedBy(rs.getString("updated_by"));
		
		
		return book;
	}

}
