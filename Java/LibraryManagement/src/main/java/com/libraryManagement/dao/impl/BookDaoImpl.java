package com.libraryManagement.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.libraryManagement.dao.BookDao;
import com.libraryManagement.utility.DBConnection;
import com.libraryManagement.utility.DBQueries;
import com.libraryManagement.utility.pojos.Book;

public class BookDaoImpl implements BookDao {
	@Override
	public boolean addBook(Book book) {
		try (Connection conn = DBConnection.getConnection()) {
			String addbook = DBQueries.addbook;
			PreparedStatement st = conn.prepareStatement(addbook);
			st.setString(1, book.getTitle());
			st.setString(2, book.getAuthor());
			st.setString(3, book.getCategory());
			st.setString(4, String.valueOf(book.getStatus()));
			st.setString(5, String.valueOf(book.getAvailability()));
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		try (Connection conn = DBConnection.getConnection()) {
			String updatebook=DBQueries.updatebook;
			PreparedStatement st = conn.prepareStatement(updatebook);
			st.setInt(1, book.getBookId());
			st.setString(2, book.getTitle());
			st.setString(3, book.getAuthor());
			st.setString(4, book.getCategory());
			st.setString(5, String.valueOf(book.getStatus()));
			st.setString(6, String.valueOf(book.getAvailability()));
			st.setInt(7, book.getBookId());
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean verifyBook(Book book) throws ClassNotFoundException, IOException, SQLException {
		Connection conn = DBConnection.getConnection();
		String q = DBQueries.verifybook;
		PreparedStatement st = conn.prepareStatement(q);
		st.setString(1, book.getTitle());
		st.setString(2, book.getAuthor());
		st.setString(3, book.getCategory());
		int cnt = st.executeUpdate();
		return cnt > 0;
	}

	@Override
	public boolean addBookLog(Book book) {
		try (Connection conn = DBConnection.getConnection()) {
			String addToBookLog = DBQueries.addToBookLog;
			PreparedStatement st = conn.prepareStatement(addToBookLog);
			st.setInt(1, book.getBookId());
			st.setString(2, book.getTitle());
			st.setString(3, book.getAuthor());
			st.setString(4, book.getCategory());
			st.setString(5, String.valueOf(book.getStatus()));
			st.setString(6, String.valueOf(book.getAvailability()));
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateBookAvailability(Book book, char ch) {
		try (Connection conn = DBConnection.getConnection()) {
			String updatebookavailability = DBQueries.updatebookavailability;
			PreparedStatement st = conn.prepareStatement(updatebookavailability);
			st.setString(1, String.valueOf(ch));
			st.setInt(2, book.getBookId());
			int cnt = st.executeUpdate();
			return cnt > 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> ans = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
			Statement st = conn.createStatement();
			String allBooks = DBQueries.allBooks;
			ResultSet rs = st.executeQuery(allBooks);
			ResultSetMetaData ms = rs.getMetaData();
			int column = ms.getColumnCount();
			boolean temp = true;
			while (rs.next()) {
				if (temp) {
					temp = false;
					continue;
				}
				Book book = new Book();
				for (int i = 1; i <= column; i++) {
					if (i == 1)
						book.setBookId(rs.getInt(i));
					else if (i == 2)
						book.setTitle(rs.getString(i));
					else if (i == 3)
						book.setAuthor(rs.getString(i));
					else if (i == 4)
						book.setCategory(rs.getString(i));
					else if (i == 5)
						book.setStatus(rs.getString(i).charAt(0));
					else {
						book.setAvailability(rs.getString(i).charAt(0));
					}
				}
				ans.add(book);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ans;
	}
}
