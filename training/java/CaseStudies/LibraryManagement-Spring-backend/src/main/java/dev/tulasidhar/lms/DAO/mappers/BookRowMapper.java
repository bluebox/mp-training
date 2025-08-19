package dev.tulasidhar.lms.DAO.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.tulasidhar.lms.model.Book;

public class BookRowMapper implements RowMapper<Book>{
	
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setBook_Id(rs.getInt("BookId"));
		book.setBook_Author(rs.getString("Author"));
		book.setBook_Title(rs.getString("Title"));
		book.setBook_Category(rs.getString("Category"));
		book.setBook_Status(rs.getString("BookStatus").charAt(0));
		book.setBook_Availability(rs.getString("Availability").charAt(0));
		return book;
	}
}
