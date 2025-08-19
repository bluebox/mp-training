package com.example.Backend.daoImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Backend.constants.BookAvailability;
import com.example.Backend.constants.BookCategory;
import com.example.Backend.constants.BookStatus;
import com.example.Backend.dao.BookDao;
import com.example.Backend.domain.Book;
import com.example.Backend.exceptions.DatabaseException;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
		Book book = new Book();
		book.setBookId(rs.getInt("book_id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setCategory(BookCategory.getEnumConstant(rs.getString("category")));
		book.setStatus(BookStatus.getEnumConstant(rs.getString("status")));
		book.setAvailability(BookAvailability.getEnumConstant(rs.getString("availability")));

		return book;
	};

	@Override
	public int addBook(Book book) {
		String sql = "insert into books(title,author,category,status,availability) values(?,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory().getStringValue(),
				book.getStatus().getStringValue(), book.getAvailability().getStringValue());
		return rows;
	}

	@Override
	public int updateBook(Book book) throws DatabaseException {

		booklog(book.getBookId());
		String sql = "update books set title=?, author=?, category=? where book_id=?";
		int rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory().getStringValue(),
				book.getBookId());

		return rows;

	}

	@Override
	public int updateAvailability(int bookId, String availability) {
		String sql = "update books set availability=? where book_id=?";
		int rows = jdbcTemplate.update(sql, availability, bookId);
		return rows;
	}

	@Override
	public int deleteBook(int id) {
		String sql = "update books set status=? where book_id=?";
		int rows = jdbcTemplate.update(sql, BookStatus.INACTIVE.getStringValue(), id);
		System.out.println("Book deleted (status updated), rows affected: " + rows);
		return rows;
	}

	@Override
	public List<Book> findAllBooks() {
		String sql = "select book_id,title,author,category,availability,status from books where status='A'";
		return jdbcTemplate.query(sql, bookRowMapper);
	}

	@Override
	public Book findById(int id) {
		String sql = "select book_id,title,author,category,availability,status from books where book_id=? and status='A'";
		return jdbcTemplate.queryForObject(sql, bookRowMapper, id);
	}

	@Override
	public int booklog(int bookId) {
		String sql = "insert into books_log(book_id,title,author,category,availability,status) select book_id,title,author,category,availability,status from books where book_id=?";
		return jdbcTemplate.update(sql, bookId);
	}
}
