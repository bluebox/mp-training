package com.example.library.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.library.constants.BookAvailability;
import com.example.library.constants.BookCategory;
import com.example.library.constants.BookStatus;
import com.example.library.dao.BookDao;
import com.example.library.domain.Book;

@Repository
public class BookDaoImpl implements BookDao {


	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
	    Book book = new Book();
	    book.setBookId(rs.getInt("book_id"));
	    book.setTitle(rs.getString("title"));  
	    book.setAuthor(rs.getString("author"));

	    String categoryVal = rs.getString("category");
	    if (categoryVal != null) {
	        book.setCategory(
	            BookCategory.getEnumConstant(categoryVal)
	        );
	    }

	    String statusVal = rs.getString("status");
	    book.setStatus(
	        BookStatus.getEnumConstant(statusVal)
	    );

	    String availabilityVal = rs.getString("availability");
	    book.setAvailability(
	        BookAvailability.getEnumConstant(availabilityVal)
	    );

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
	public int updateBook(Book book) {
		booklog(book.getBookId());
		String sql = "update books set title=?, author=?, category=? where book_id=?";
		int rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory().getStringValue(),
				book.getBookId());
		System.out.println("Rows updated: " + rows);
		return rows;
	}

	@Override
	public int updateAvailability(int bookId, String availability) {
		String sql = "update books set availability=? where book_id=?";
		int rows = jdbcTemplate.update(sql, availability, bookId);
		System.out.println("Availability updated, rows affected: " + rows);
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
	    String sql = "SELECT * FROM books WHERE status='A' AND availability='A'";
	    return jdbcTemplate.query(sql, bookRowMapper);
	}


	@Override
	public Book findById(int id) {
		String sql = "select * from books where book_id=? and status='A'";
		return jdbcTemplate.queryForObject(sql, bookRowMapper, id);
	}

	@Override
	public List<Book> getBooksByMember(int memberId) throws DataAccessException {
		String sql = "select b.* from books b join issue_records i on b.book_id=i.book_id where i.member_id=? and i.status='I' and b.status='A'";
		return jdbcTemplate.query(sql, bookRowMapper, memberId);
	}
	@Override
	public int booklog(int bookId) {
		String sql = "insert into books_log(book_id,title,author,category,availability,status) select book_id,title,author,category,availability,status from books where book_id=?";
		return jdbcTemplate.update(sql, bookId);
	}

	public List<Book> getBooksByMember() {
		return null;
	}
	
}
