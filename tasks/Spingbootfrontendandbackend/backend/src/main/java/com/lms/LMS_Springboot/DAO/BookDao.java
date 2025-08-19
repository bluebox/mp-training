package com.lms.LMS_Springboot.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.LMS_Springboot.Model.*;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;


@Scope("prototype")
@Repository
public class BookDao {
	@Autowired
    private JdbcTemplate template;
	

	
	public boolean addBooks(Book book ){
		String sql="insert into books(title,author,category,status,availability) values (?,?,?,?,?)";
		int added=template.update(sql,book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus().name().substring(0, 1),book.getAvailability().name().substring(0, 1));
		if(added>0) {
			return true;
		}
		return false;
	}
	public boolean updateBookDetails(int id,Book book) {
		String querylog="insert into books_log(bookid,title,author,category,status,availability) values (?,?,?,?,?,?)";
		String query="update books set category=?,status=? where bookid=?";

		
	    int updatequerylog=template.update(querylog,id,book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus().name().substring(0,1),book.getAvailability().name().substring(0, 1));
	    if(updatequerylog==0) {
	    	return false;
	    }
	    int updatequery=template.update(query,book.getCategory(),book.getStatus().name().substring(0, 1),id);
	    if(updatequery==0) {
	    	return false;
	    }
	    return true;
	}
	public Book getbookid(int bookid) {
		String query = "select bookId, Title, Author, Category, Status, Availability from books where bookId=?";
		 Book book1=template.queryForObject(query, new BookRowMapper(),bookid);
		return book1;
		}
	public static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookid(rs.getInt("bookid"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("Category"));
            book.setStatus(Status.getstatus(rs.getString("status")));
            book.setAvailability(Availability.getstatus(rs.getString("availability")));
            return book;
        }
    }
	public boolean Updatebookavailability(int bookid,Availability availability) {
		
		
		Book book = getbookid(bookid);
		System.out.println(book);
		String querylog="insert into books_log(title,author,category,status,availability) values (?,?,?,?,?)";

		String query="update books set Availability=? where bookid=?";
		int updatequerylog=template.update(querylog,book.getTitle(),book.getAuthor(),book.getCategory(),book.getStatus(),book.getAvailability().name().substring(0, 1));
		if(updatequerylog==0) {
			return false;
		}
		int updatequery=template.update(query,availability,bookid);
		if(updatequery==0) {
			return false;
		}
		return true;
}
	public List<Book> viewallbooks() {
		String query="select bookid,title,author,category,status,availability from books";
		List<Book> books=template.query(query, new BookRowMapper1() );
		return books;
		
	}
	public static final class BookRowMapper1 implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookid(rs.getInt("bookid"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setStatus(Status.getstatus(rs.getString("status")));
            book.setAvailability(Availability.getstatus(rs.getString("availability")));
            return book;
        }
    }
}

