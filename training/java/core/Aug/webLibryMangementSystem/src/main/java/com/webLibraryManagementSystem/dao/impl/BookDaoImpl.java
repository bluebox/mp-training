package com.webLibraryManagementSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.dao.BookDao;
import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.BookCategory;
import com.webLibraryManagementSystem.utilities.BookStatus;
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.SQLQueries;
import com.webLibraryManagementSystemexceptions.InvalidException;

public class BookDaoImpl implements BookDao {

	@Override
	public void addBook(Book book) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_INSERT);) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory().toString());
			stmt.setString(4, String.valueOf(book.getStatus().toString().charAt(0)));
			stmt.setString(5, String.valueOf(book.getAvailability().toString().charAt(0)));

			int rows = stmt.executeUpdate();

			if (rows < 0) {
				throw new InvalidException("Book not added to server");
			}

		} catch (SQLException e) {
			throw new InvalidException("Error in Server");
		}

	}

	@Override
	public void updateBook(Book book, Book oldBook) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_UPDATE);) {

			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getCategory().toString());
			stmt.setString(4, String.valueOf(book.getStatus().toString().charAt(0)));
			stmt.setString(5, String.valueOf(book.getAvailability().toString().charAt(0)));
			stmt.setInt(6, book.getBookId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated < 0) {
				throw new InvalidException("Book not added to server");
			}
			bookLog(oldBook);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new InvalidException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public void updateBookAvalability(Book book, BookAvailability avalability) throws InvalidException {
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_UPDATE_AVAILABILITY);) {

			stmt.setString(1, String.valueOf(avalability.toString().charAt(0)));
			stmt.setInt(2, book.getBookId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated <= 0) {

				throw new InvalidException("Book Avalability not updated.");
			}
			bookLog(book);

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			throw new InvalidException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public List<Book> getAllBooks() throws InvalidException {
		List<Book> books = new ArrayList<>();

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_SELECT_ALL);) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = rs.getString("category");
				BookStatus status = rs.getString("status").equals("A") ? BookStatus.ACTIVE : BookStatus.INACTIVE;
				BookAvailability availability = rs.getString("availability").equalsIgnoreCase("A")
						? BookAvailability.AVAILABLE
						: BookAvailability.ISSUED;
				Book book = new Book(bookId, title, author, BookCategory.valueOf(category.toUpperCase()), status,
						availability);
				books.add(book);
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving all members: " + e.getMessage());
		}

		return books;
	}

	@Override
	public boolean getBookByTitleAndAuthor(String title, String author) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_SELECT_BY_TITLE_AUTHOR);) {

			stmt.setString(1, title.trim().toLowerCase());
			stmt.setString(2, author.trim().toLowerCase());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return true;
			}

			return false;

		} catch (SQLException e) {
			System.out.println("here " + e.getMessage());
			throw new InvalidException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public Book getBookById(int id) throws InvalidException {
		Book book = null;

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_SELECT_BY_ID);) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String category = rs.getString("category");
				BookStatus status = rs.getString("status").equals("A") ? BookStatus.ACTIVE : BookStatus.INACTIVE;
				BookAvailability availability = rs.getString("availability").equalsIgnoreCase("A")
						? BookAvailability.AVAILABLE
						: BookAvailability.ISSUED;
				book = new Book(bookId, title, author, BookCategory.valueOf(category.toUpperCase()), status,
						availability);

			}

			return book;

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public void deleteBook(Book book) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOK_DELETE);) {

			stmt.setInt(1, book.getBookId());

			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted <= 0) {

				throw new InvalidException("No Book found with Title: " + book.getTitle());
			}

			bookLog(book);

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public void bookLog(Book book) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.BOOKS_LOG_INSERT);) {

			stmt.setInt(1, book.getBookId());
			stmt.setString(2, book.getTitle());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getCategory().toString());
			stmt.setString(5, String.valueOf(book.getStatus().toString().charAt(0)));
			stmt.setString(6, String.valueOf(book.getAvailability().toString().charAt(0)));
			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("log added ");
			}

		} catch (SQLException e) {

			throw new InvalidException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public Map<String, Integer> getBookCountByCategory() throws InvalidException {

		Map<String, Integer> books = new HashMap<>();

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_BOOK_BY_CATEGORY);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String category = rs.getString("category");
				int count = rs.getInt("book_count");
				books.put(category, count);

			}

		} catch (

		SQLException e) {

			System.out.println(e.getMessage());
		}
		return books;

	}

	@Override
	public List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws InvalidException {
		List<CustomActiveIssuedBooks> activeBooks = new ArrayList<>();
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_ACTIVE_ISSUED_BOOKS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				CustomActiveIssuedBooks customActiveIssuedBooks = new CustomActiveIssuedBooks(memberId, memberName,
						bookId, bookTitle, issuedDate);

				activeBooks.add(customActiveIssuedBooks);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return activeBooks;
	}

	@Override
	public List<CustomActiveIssuedBooks> getOverDueBooks() throws InvalidException {
		List<CustomActiveIssuedBooks> overDueBooks = new ArrayList<>();
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_OVER_DUE_BOOKS)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				CustomActiveIssuedBooks customOverDueBooks = new CustomActiveIssuedBooks(memberId, memberName, bookId,
						bookName, issuedDate);

				overDueBooks.add(customOverDueBooks);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return overDueBooks;
	}

}
