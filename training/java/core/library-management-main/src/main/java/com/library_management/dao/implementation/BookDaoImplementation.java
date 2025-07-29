package com.library_management.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library_management.dao.BookDaoInterface;
import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.exceptions.DatabaseException;
import com.library_management.utilities.DBConnection;

public class BookDaoImplementation implements BookDaoInterface {

	@Override
	public int addBook(Book book) throws DatabaseException {
		Connection conn=DBConnection.getConn();
		int id=-1;
		try(PreparedStatement psInsert=conn.prepareStatement("insert into books(title,author,category) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);) {
			
			psInsert.setString(1, book.getTitle());
			psInsert.setString(2, book.getAuthor());
			psInsert.setString(3, book.getCategory().getStringValue());
			psInsert.executeUpdate();
			ResultSet rs=psInsert.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		return id;
	}

	@Override
	public void updateBookDetails(Book oldBook,Book newBook) throws DatabaseException {
		Connection conn=DBConnection.getConn();
		String updateBooksQuery="update books set title=?, author=?, category=?, status=? where book_id=?";
		String insertBooksLogQuery="insert into books_log(book_id,title,author,category,status,availability) values(?,?,?,?,?,?)";
		try(PreparedStatement psInsertLog=conn.prepareStatement(insertBooksLogQuery);
				PreparedStatement psUpdate=conn.prepareStatement(updateBooksQuery);) {
			conn.setAutoCommit(false);
			
			psInsertLog.setInt(1, oldBook.getBookId());
			psInsertLog.setString(2, oldBook.getTitle());
			psInsertLog.setString(3, oldBook.getAuthor());
			psInsertLog.setString(4, oldBook.getCategory().getStringValue());
			psInsertLog.setString(5, oldBook.getStatus().getStringValue());
			psInsertLog.setString(6, oldBook.getAvailability().getStringValue());
			psInsertLog.executeUpdate();
			
			psUpdate.setString(1, newBook.getTitle());
			psUpdate.setString(2, newBook.getAuthor());
			psUpdate.setString(3, newBook.getCategory().getStringValue());
			psUpdate.setString(4, newBook.getStatus().getStringValue());
			psUpdate.setInt(5, oldBook.getBookId());
			psUpdate.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch (SQLException ex) {
				throw new DatabaseException("Update Details Rollback Failed...");
			}
			throw new DatabaseException("Error Occurred while updating data...");
		}
		
	}

	@Override
	public void updateBookAvailability(Book oldBook, String availability) throws DatabaseException {
		
		Connection conn=DBConnection.getConn();
		String updateAvailabilityQuery="update books set availability=? where book_id=?";
		String insertBookLog="insert into books_log(book_id,title,author,category,status,availability) values(?,?,?,?,?,?)";
		try(PreparedStatement psUpdate=conn.prepareStatement(updateAvailabilityQuery);
			PreparedStatement psInsertLog=conn.prepareStatement(insertBookLog);) {
			conn.setAutoCommit(false);
			psInsertLog.setInt(1, oldBook.getBookId());
			psInsertLog.setString(2, oldBook.getTitle());
			psInsertLog.setString(3, oldBook.getAuthor());
			psInsertLog.setString(4, oldBook.getCategory().getStringValue());
			psInsertLog.setString(5, oldBook.getStatus().getStringValue());
			psInsertLog.setString(6, oldBook.getAvailability().getStringValue());
			psInsertLog.executeUpdate();
			
			psUpdate.setString(1, availability);
			psUpdate.setInt(2, oldBook.getBookId());
			psUpdate.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		}catch(SQLException e) {
			try {
				conn.rollback();
			}catch (SQLException ex) {
				throw new DatabaseException("Update Availability Rollback Failed...");
			}
			throw new DatabaseException("Failed To Update Availability...");
		}
	}

	@Override
	public void deleteBook(Book oldBook) throws DatabaseException {
		Connection conn=DBConnection.getConn();
		String deleteQuery="delete from books where book_id=?";
		String insertBookLog="insert into books_log(book_id,title,author,category,status,availability) values(?,?,?,?,?,?)";
		try(PreparedStatement psDelete=conn.prepareStatement(deleteQuery);
			PreparedStatement psInsertLog=conn.prepareStatement(insertBookLog);) {
			conn.setAutoCommit(false);
			psInsertLog.setInt(1, oldBook.getBookId());
			psInsertLog.setString(2, oldBook.getTitle());
			psInsertLog.setString(3, oldBook.getAuthor());
			psInsertLog.setString(4, oldBook.getCategory().getStringValue());
			psInsertLog.setString(5, oldBook.getStatus().getStringValue());
			psInsertLog.setString(6, oldBook.getAvailability().getStringValue());
			psInsertLog.executeUpdate();
			
			psDelete.setInt(1, oldBook.getBookId());
			psDelete.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		}catch(SQLException e) {
			try {
				conn.rollback();
			}catch (SQLException ex) {
				throw new DatabaseException("Book Deletion Rollback Failed...");
			}
			throw new DatabaseException("Failed To Delete Book...");
		}
		
	}

	@Override
	public List<Book> selectAllBooks() throws DatabaseException {
		List<Book> books=new ArrayList<>();
		Statement statement=DBConnection.getStatement();
		String selectQuery="select * from books";
		try {
			ResultSet result=statement.executeQuery(selectQuery);
			while(result.next()) {
				int id=result.getInt(1);
				String title=result.getString(2);
				String author=result.getString(3);
				BookCategory category=BookCategory.getEnumConstant(result.getString(4));
				BookStatus status=BookStatus.getEnumConstant(result.getString(5));
				BookAvailability availability=BookAvailability.getEnumConstant(result.getString(6));
				
				Book currentBook=new Book(id,title,author,category,status,availability);
				books.add(currentBook);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		return books;
	}

	@Override
	public Book selectBookById(int id) throws DatabaseException {
		Book currentBook=null;
		Connection conn=DBConnection.getConn();
		String selectOneQuery="select * from books where book_id=?";
		try {
			PreparedStatement psSelectOne=conn.prepareStatement(selectOneQuery);
			psSelectOne.setInt(1, id);
			psSelectOne.execute();
			ResultSet resultSet=psSelectOne.getResultSet();
			if(resultSet.next()) {
				int bookId=resultSet.getInt(1);
				String title=resultSet.getString(2);
				String author=resultSet.getString(3);
				BookCategory category=BookCategory.getEnumConstant(resultSet.getString(4));
				BookStatus status=BookStatus.getEnumConstant(resultSet.getString(5));
				BookAvailability availability=BookAvailability.getEnumConstant(resultSet.getString(6));
				
				currentBook=new Book(bookId,title,author,category,status,availability);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		return currentBook;
	}

	@Override
	public List<Book> selectAllMemberBooks(int memberId) throws DatabaseException {
		
		Connection conn=DBConnection.getConn();
		List<Book> memberBooks=new ArrayList<Book>();
		try {
			
			String selectQuery="select b.* from books b join issue_records i on b.book_id=i.book_id join members m on i.member_id=m.member_id where i.member_id=? and i.status='I'";
			PreparedStatement psSelect=conn.prepareStatement(selectQuery);
			psSelect.setInt(1, memberId);
			ResultSet result=psSelect.executeQuery();
			while(result.next()) {
				int id=result.getInt(1);
				String title=result.getString(2);
				String author=result.getString(3);
				BookCategory category=BookCategory.getEnumConstant(result.getString(4));
				BookStatus status=BookStatus.getEnumConstant(result.getString(5));
				BookAvailability availability=BookAvailability.getEnumConstant(result.getString(6));
				
				Book currentBook=new Book(id,title,author,category,status,availability);
				memberBooks.add(currentBook);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		return memberBooks;
	}
	
	

}
