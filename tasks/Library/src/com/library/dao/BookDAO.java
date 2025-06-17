package com.library.dao;

import com.library.DaoInterface.BookDAOInterface;
import com.library.domain.Book;
import com.library.sqlQueryLoader.sqlQueryStore;
import com.library.util.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends sqlQueryStore implements BookDAOInterface{

	public List<Book> getAllBooks(Connection conn) {

		List<Book> books = new ArrayList<>();
		String sql = getAllBooks;

		try (
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				
				Book book = new Book(rs.getInt("BookId"), rs.getString("Title"), rs.getString("Author"),
						rs.getString("Category"), rs.getString("Status").charAt(0),
						rs.getString("Availability").charAt(0));

				books.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return books;
	}

	public boolean addBook(Connection conn,Book book) {
		String sql = insertIntoBook;

		try ( PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory());
			stmt.setString(4, String.valueOf(book.getStatus()));
			stmt.setString(5, String.valueOf(book.getAvailability()));

			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isAvailable(Connection conn, int bookId) throws Exception {
		String query = getBookAvailability;

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String availability = rs.getString("Availability");
				return "A".equalsIgnoreCase(availability);
			}

			throw new Exception("book doesn't exist");
		}
	}
	
	
	
	public Book getBookById(Connection conn,int bookId,char status) throws Exception
	{
		

		if (status != 'A' && status != 'I') {
			throw new IllegalArgumentException("Availability status must be 'A' (Available) or 'I' (Issued).");
		}

		String fetchSQL = getBookById;
		ResultSet resultData=null;
		try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSQL)) {
			fetchStmt.setInt(1, bookId);
			resultData = fetchStmt.executeQuery();
			if (resultData.next()) {

				Book book = new Book(resultData.getInt("BookId"), resultData.getString("Title"),
						resultData.getString("Author"), resultData.getString("Category"),
						resultData.getString("Status").charAt(0), resultData.getString("Availability").charAt(0));
			
				return book;
			}
		}
		catch (Exception e) {
			 throw new Exception("error while retriving book by id");
		}
			return null;
		
	}

	
	
	
	
	public boolean insertIntoBookLog(Connection conn,Book book) throws Exception
	{
		if (book !=null) {
			
			String logSQL = insertIntoBookLog;
			try (PreparedStatement logStmt = conn.prepareStatement(logSQL)) {
				logStmt.setInt(1, book.getBookId());
				logStmt.setString(2, book.getTitle());
				logStmt.setString(3, book.getAuthor());
				logStmt.setString(4, book.getCategory());
				logStmt.setString(5, String.valueOf(book.getStatus()));
				logStmt.setString(6, String.valueOf( book.getAvailability()));
				return logStmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return false;
	}
	public void updateAvailability(Connection conn, int bookId, char status) throws SQLException {

				String updateSQL = updateBookAvailabilityById;
				try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
					updateStmt.setString(1, String.valueOf(status));
					updateStmt.setInt(2, bookId);
					updateStmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}

			
		}
	}
