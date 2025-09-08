package com.medplus.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.management.RuntimeErrorException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.domain.AvailabilityStatus;
import com.medplus.lms.domain.Book;
import com.medplus.lms.domain.Category;
import com.medplus.lms.domain.IssueStatus;
import com.medplus.lms.domain.Status;
import com.medplus.lms.exceptions.ManagementException;
@Repository
public class BookRepository implements BookRepositoryInterface{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public void addBook(Book book) {
        String sql = "INSERT INTO books (Title, Author, Category, Status, Availability, created_by,updated_by,created_at,updated_at) " +
                     "VALUES (:title, :author, :category, :status, :availability, :created_by,:updated_by,NOW(),NOW())";

        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("author", book.getAuthor());
        params.put("category", book.getCategory().getCode());
        params.put("status", book.getStatus().getCode());
        params.put("availability", book.getAvailability().getCode());
        params.put("created_by", book.getCreatedBy());
        params.put("updated_by", book.getCreatedBy());
        
        try {
        	jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	throw new ManagementException("Failed to add book from dao layer");
        }
    }

    public List<Book> getAllBooks() {
        String sql = "select book_id, title, author, category, status, availability,created_at, updated_at, created_by, updated_by from books";
        try {
        	return jdbcTemplate.query(sql, new BookRowMapper());
        }
        catch(Exception e) {
        	throw new ManagementException("Failed in get all books dao");
        }
    }
    public Book findBookById(int bookId) {
    	String sql = "select book_id, title, author, category, status, availability,created_at, updated_at, created_by, updated_by from books where book_id = :bookId";
        Map<String, Integer> params = new HashMap<>();
        params.put("bookId", bookId);
        List<Book> list;
        try {
        	list= jdbcTemplate.query(sql, params,new BookRowMapper());
        }
        catch(Exception e) {
        	throw new ManagementException("Failed in finding the book dao");
        }
        return list.isEmpty() ? null : list.get(0);
    }
    public Book findBookByTitleAndAuthorIgnoreCase(String title, String author) {
    	String sql = "select book_id, title, author, category, status, availability,created_at, updated_at, created_by, updated_by from books where title = :title and author = :author";
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("author", author);
        List<Book> list;
        try {
        	list= jdbcTemplate.query(sql, params,new BookRowMapper());
        }
        catch(Exception e) {
        	throw new ManagementException("Failed in finding the book using Title and Author in  dao");
        }
        return list.isEmpty() ? null : list.get(0);
    }

    public static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
           // book.setCategory(Category.valueOf(rs.getString("category")));
            book.setCategory(Category.fromCode(rs.getString("category")));
            book.setStatus(Status.fromCode(rs.getString("status")));
            book.setAvailability(AvailabilityStatus.fromCode(rs.getString("availability")));
            book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            book.setCreatedBy(rs.getString("created_by"));
            book.setUpdatedBy(rs.getString("updated_by"));
            return book;
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateBook(Book book) {
        String logSql = "INSERT INTO books_log (book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at,logged_time,logged_by) " +
                        "SELECT book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at, NOW(),:loggedBy FROM books WHERE book_id=:id";
        
        String sql = "update books set title = :title, author = :author, category = :category, status = :status, " +
                "availability = :availability, updated_at = NOW(), updated_by = :updatedBy where book_id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("loggedBy",book.getUpdatedBy());
        params.put("id", book.getBookId());
        
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("id", book.getBookId());
        updateParams.put("title", book.getTitle());
        updateParams.put("author", book.getAuthor());
        updateParams.put("category",book.getCategory().getCode());
        updateParams.put("status",book.getStatus().getCode());
        updateParams.put("availability",book.getAvailability().getCode());
        updateParams.put("updatedBy", book.getUpdatedBy());

        try {
            jdbcTemplate.update(logSql, params);
           // if(true) throw new RuntimeException("Hi i am exception ");
            jdbcTemplate.update(sql, updateParams);

        } catch (Exception e) {
        	e.printStackTrace();
            throw new ManagementException("Failed to update book from dao layer"+e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBookAvailability(Book book) {
    	String logSql = "INSERT INTO books_log (book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at,logged_time,logged_by) " +
                "SELECT book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at, NOW(),:loggedBy FROM books WHERE book_id=:id";
        
    	String sql = "UPDATE books SET Availability = :availability WHERE book_id = :BookId";
    	
    	Map<String, Object> logParams = new HashMap<>();
        logParams.put("loggedBy",book.getUpdatedBy());
        logParams.put("id", book.getBookId());
        
        
        Map<String, Object> params = new HashMap<>();
        params.put("availability",book.getAvailability().getCode());
        params.put("BookId",book.getBookId());
        try {
        	jdbcTemplate.update(logSql,logParams);
            jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	throw new ManagementException("Failed to update book Availability from dao layer");
        }
    }
    public boolean isBookIssued(int bookId) {
        String sql = "select count(issueId) from issue_record where bookId = :bookId and status = :status";
        Map<String, Object> params = new HashMap<>();
        params.put("bookId", bookId);
        params.put("status", IssueStatus.ISSUED.getCode());
        Integer count;
        try {
        	count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        }
        catch(Exception e) {
        	throw new ManagementException("Failed to check whether book is issued from dao layer");
        }
         
        return count != null && count > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBookStatus(Book book) {
    	
    	String logSql = "INSERT INTO books_log (book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at,logged_time,logged_by) " +
                "SELECT book_id, title, author, category, status, availability, created_by, updated_by,created_at,updated_at, NOW(),:loggedBy FROM books WHERE book_id=:id";
        
        String sql = "update books set status = :status where book_id = :bookId";
        
        Map<String, Object> logParams = new HashMap<>();
        logParams.put("loggedBy",book.getUpdatedBy());
        logParams.put("id", book.getBookId());

        Map<String, Object> params = new HashMap<>();
        params.put("status", book.getStatus().getCode());
        params.put("bookId", book.getBookId());
        try {
        	jdbcTemplate.update(logSql,logParams);

            jdbcTemplate.update(sql, params);
        }
        catch(Exception e) {
        	throw new ManagementException("Failed to update book Availability from dao layer");
        }
    }

}
