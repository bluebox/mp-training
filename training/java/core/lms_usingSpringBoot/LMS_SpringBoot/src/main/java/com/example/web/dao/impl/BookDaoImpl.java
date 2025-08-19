package com.example.web.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.web.dao.BookDao;
import com.example.web.model.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	private JdbcTemplate template;
	private RowMapper<Book> mapper;
	
	public JdbcTemplate getTemplate() {
		return template;
	}
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	@Transactional
	public int addBook(Book newBook) {
		String query="INSERT INTO books(Title,Author,Category,BookStatus,Availability) VALUES (?,?,?,?,?)";
		int rows_Effected = template.update(query,
				newBook.getBook_Title(),
				newBook.getBook_Author(),
				newBook.getBook_Category(),
				String.valueOf(newBook.getBook_Status()),
				String.valueOf(newBook.getBook_Availability()));
		try {
			if(rows_Effected==0) {
				throw new SQLException("SQL ERROR: NOTHING INSERTED");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rows_Effected;
	}


	@Override
	public List<Book> getAllBooks() {
		String query="SELECT BookId,Title,Author,Category,BookStatus,Availability FROM books;";
		mapper = (rs, rowNum) -> {
			Book book = new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),rs.getString("BookStatus").charAt(0),rs.getString("Availability").charAt(0));
			return book;
		};
		List<Book> books= template.query(query,mapper);
		try {
			if(books == null) {
				throw new SQLException("Error In Fetching Books");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return books;
	}


	@Override
	public void updateBook(Book book) {
	    String query = "UPDATE books SET Title=?, Author=?, Category=?, BookStatus=? WHERE BookId=?";
	    int rowsAffected = template.update(query,book.getBook_Title(),book.getBook_Author(),book.getBook_Category(),String.valueOf(book.getBook_Status()),book.getBook_Id());
	    try {
	    	if(rowsAffected == 0) {
				throw new SQLException("SQL ERROR: Error In Updating Book");
			}
	    }
	    catch (SQLException e) {
	        System.out.println("Error updating book: " + e.getMessage());
	    }
	}
	
	public void addBookLogs(Book book) {
		String query="INSERT INTO books_log(BookId,Title,Author,Category,Status,Availability) VALUES (?,?,?,?,?,?);";	
		int rowsAffected = template.update(query,book.getBook_Id(),book.getBook_Title(),book.getBook_Author(),book.getBook_Category(),String.valueOf(book.getBook_Status()), String.valueOf(book.getBook_Availability()));
		try {
			if (rowsAffected == 0) {
	            throw new SQLException("Add book log failed, No rows affected.");
	        }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void updateBookAvailability(int bookId, boolean isavailable) {
		 String query = "UPDATE books SET Availability=? WHERE BookId=?";
		 String updatedAvailability;
		 if(isavailable==true) {
			 updatedAvailability=String.valueOf("A");
	     }
	     else {
	    	 updatedAvailability=String.valueOf("I");
         }
		 int rowsAffected = template.update(query,updatedAvailability,bookId);
		 try {
			 if (rowsAffected == 0) {
				 throw new SQLException("no rows affected.");
		     }
	     } catch (SQLException e) {
	    	 System.out.println("Error in upadting availability. "+ e.getMessage());
	     }
	}

	@Override
	public Book getBookById(int bookId) {
		Book resultedbook=null;
		String query="SELECT BookId,Title,Author,Category,BookStatus,Availability FROM books WHERE BookId=?";
		mapper = (rs, rowNum) -> {
			Book book = new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),rs.getString("BookStatus").charAt(0),rs.getString("Availability").charAt(0));
			return book;
		};
		resultedbook = template.queryForObject(query,mapper,bookId);
		try {
			 if (resultedbook == null) {
				 throw new SQLException("Book Not Exists");
		     }
	    }
		catch (SQLException e) {
	    	 System.out.println(e.getMessage());
	    }
		return resultedbook;
	}
}
