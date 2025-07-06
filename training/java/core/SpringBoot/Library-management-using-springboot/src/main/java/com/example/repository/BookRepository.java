package com.example.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.Books;

@Repository
public class BookRepository{
	private final JdbcTemplate jdbcTemplate;
	@Autowired
	public BookRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public String insert(Books b) {
		int x=jdbcTemplate.update("insert into books values(?,?,?,?,?,?)",b.getBookId(),b.getTitle(),b.getAuthor(),b.getCategory(),Character.toString(b.getStatus()),Character.toString(b.getAvailability()));
		if(x>0) {
			return "inserted";
		}
		else {
			return "not inserted";
		}
	}
	public List<Map<String, Object>> showAll() {
		return jdbcTemplate.queryForList("select * from books");
	}

	public String updateById(long bookId,String title,String author,String category,char status,char availability) {
		int x=jdbcTemplate.update("update books set title=?, author=?, category=?, status=?, availabilty=? where bookId=?",title,author,category,String.valueOf(status),String.valueOf(availability),bookId);
		if(x>0) {
			return "updated";
		}
		return "not updated";
	}
	public int deleteById(long bookId) {
		return jdbcTemplate.update("delete from books where bookId=?",bookId);
	}
	public int changeStatus(long bookId) {
		Map<String, Object> l = jdbcTemplate.queryForMap("select status,availabilty from books where bookId=?",bookId);
		return jdbcTemplate.update("update books set status=?,availability=? where bookId=?",(String.valueOf(l.get("Status")).charAt(0)=='A'?"I":"A"),(String.valueOf(l.get("Status")).charAt(0)=='A'?"I":"A"),bookId);
	}
}
