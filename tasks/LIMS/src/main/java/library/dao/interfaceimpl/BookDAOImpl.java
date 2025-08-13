package library.dao.interfaceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.dao.interfaces.BookDAO;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.util.DBConnection;

public class BookDAOImpl implements BookDAO {

	@Override
	public void addBook(Book book, String createdBy) throws LibraryException {
		String sql = "INSERT INTO books (Title, Author, Category, Status, Availablity, created_by) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			int paramIndex = 1;
			preparedStatement.setString(paramIndex++, book.getTitle());
			preparedStatement.setString(paramIndex++, book.getAuthor());
			preparedStatement.setString(paramIndex++, book.getCategory().getDisplayName());
			preparedStatement.setString(paramIndex++, book.getStatus().getCode());
			preparedStatement.setString(paramIndex++, book.getAvailability().getCode());
			preparedStatement.setString(paramIndex++, createdBy);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Book '" + book.getTitle() + "' added successfully to the database.");
			} else {
				System.err.println("Failed to add book '" + book.getTitle() + "' to the database. No rows affected.");
			}

		} catch (Exception e) {
			System.err.println("Error occured while adding book: " + e.getMessage());
			throw new LibraryException("Failed to add book: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean updateBook(Book book, String updatedBy) throws LibraryException {
		Book existingBook = getBookByIdForInternalUse(book.getBookId());
		if (existingBook == null) {
			System.out.println("Book with ID " + book.getBookId() + " not found for update.");
			return false;
		}

//		// change
//		boolean changesDetected = !existingBook.getTitle().equals(book.getTitle())
//				|| !existingBook.getAuthor().equals(book.getAuthor())
//				|| !existingBook.getCategory().equals(book.getCategory())
//				|| !existingBook.getStatus().equals(book.getStatus());
		
		boolean changesDetected = existingBook.hashCode()!=book.hashCode();
		

		if (!changesDetected) {
			System.out.println(
					"Book with ID " + book.getBookId() + ": No changes detected. Skipping update and logging.");
			return false;
		}

		String sql = "UPDATE books SET Title = ?, Author = ?, Category = ?, Status = ?, updated_by = ?, updated_at = CURRENT_TIMESTAMP WHERE BookId = ?";
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			boolean loged = logBookChange(existingBook, connection);

			if (!loged) {
				throw new LibraryException("Failed to log book: ");
			}

			// TEST CASE FOR ROLLBACK
			// if (book.getBookId() == 99999) {
			// System.out.println("TESTING ROLLBACK: FORCING SQL EXCEPTION FOR BOOK ID " +
			// book.getBookId());
			// throw new Exception("TEST_ROLLBACK_ERROR: Simulated database failure.");
			// }

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				int paramIndex = 1;
				preparedStatement.setString(paramIndex++, book.getTitle());
				preparedStatement.setString(paramIndex++, book.getAuthor());
				preparedStatement.setString(paramIndex++, book.getCategory().getDisplayName());
				preparedStatement.setString(paramIndex++, book.getStatus().getCode());
				preparedStatement.setString(paramIndex++, updatedBy);
				preparedStatement.setInt(paramIndex++, book.getBookId());

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Book with ID " + book.getBookId() + " updated successfully.");
					connection.commit();
					return true;
				} else {
					connection.rollback();
					System.out.println("Book with ID " + book.getBookId() + " not found or no changes made by DB.");
					return false;
				}
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rollbackEx) {
					System.err.println("Error during rollback after update error: " + rollbackEx.getMessage());
				}
			}
			System.err
					.println("Database error while updating book with ID " + book.getBookId() + ": " + e.getMessage());
			throw new LibraryException("Failed to update book: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					System.err.println("Error closing connection for update transaction: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public boolean updateBookAvailability(int bookId, String newAvailabilityCode, String updatedBy)
			throws LibraryException {
		Book existingBook = getBookByIdForInternalUse(bookId);
		if (existingBook == null) {
			System.out.println("Book with ID " + bookId + " not found for availability update.");
			return false;
		}

		if (existingBook.getAvailability().getCode().equals(newAvailabilityCode)) {
			System.out.println("Book ID " + bookId + ": Availability already " + newAvailabilityCode
					+ ". Skipping update and logging.");
			return false;
		}

		String sql = "UPDATE books SET Availablity = ?, updated_by = ?, updated_at = CURRENT_TIMESTAMP WHERE BookId = ?";
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			boolean loged = logBookChange(existingBook, connection);

			if (!loged) {
				throw new LibraryException("Failed to log book: ");
			}

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				int paramIndex = 1;
				preparedStatement.setString(paramIndex++, newAvailabilityCode);
				preparedStatement.setString(paramIndex++, updatedBy);
				preparedStatement.setInt(paramIndex++, bookId);

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Book ID " + bookId + " availability updated to '" + newAvailabilityCode
							+ "' successfully.");
					connection.commit();
					return true;
				} else {
					connection.rollback();
					System.out.println("No book found with ID " + bookId + " for availability update.");
					return false;
				}
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rollbackEx) {
					System.err.println(
							"Error during rollback after availability update error: " + rollbackEx.getMessage());
				}
			}
			System.err.println("Database error while updating book availability: " + e.getMessage());
			throw new LibraryException("Failed to update book availability: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					System.err.println("Error closing connection for availability update transaction: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public boolean deleteBook(int bookId) throws LibraryException {
		Book existingBook = getBookByIdForInternalUse(bookId);
		if (existingBook == null) {
			System.out.println("No book found with ID " + bookId + " for deletion.");
			return false;
		}

		String sql = "DELETE FROM books WHERE BookId = ?";
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			boolean loged = logBookChange(existingBook, connection);

			if (!loged) {
				throw new LibraryException("Failed to log book");
			}

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				int paramIndex = 1;
				preparedStatement.setInt(paramIndex++, bookId);

				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Book with ID " + bookId + " deleted successfully from the database.");
					connection.commit();
					return true;
				} else {
					connection.rollback();
					System.out.println("No book found with ID " + bookId + " for deletion.");
					return false;
				}
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rollbackEx) {
					System.err.println("Error during rollback after delete error: " + rollbackEx.getMessage());
				}
			}
			System.err.println("Database error while deleting book: " + e.getMessage());
			throw new LibraryException("Failed to delete book: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					System.err.println("Error closing connection for delete transaction: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<Book> findBooks(Map<String, Object> criteria) throws LibraryException {

		List<Book> books = new ArrayList<>();

		String sql;

		if (criteria == null) {
			sql = "SELECT BookId, Title, Author, Category, Status, Availablity, created_at, created_by, updated_at, updated_by FROM books";
		} else {

			sql = "SELECT BookId, Title, Author, Category, Status, Availablity, created_at, created_by, updated_at, updated_by FROM books WHERE "
					+ "(? IS NULL OR BookId = ?) AND " + "(? IS NULL OR Title LIKE ?) AND "
					+ "(? IS NULL OR Author LIKE ?) AND " + "(? IS NULL OR Category = ?) AND "
					+ "(? IS NULL OR Status = ?) AND " + "(? IS NULL OR Availablity = ?)";
		}

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			int paramIndex = 1;

			if (criteria != null) {
				Integer bookId = (Integer) criteria.getOrDefault("bookId", null);
				preparedStatement.setObject(paramIndex++, bookId);
				preparedStatement.setObject(paramIndex++, bookId);

				String title = (String) criteria.getOrDefault("title", null);
				preparedStatement.setObject(paramIndex++, title);
				preparedStatement.setObject(paramIndex++, title != null ? "%" + title.trim() + "%" : null);

				String author = (String) criteria.getOrDefault("author", null);
				preparedStatement.setObject(paramIndex++, author);
				preparedStatement.setObject(paramIndex++, author != null ? "%" + author.trim() + "%" : null);

				String category = (String) criteria.getOrDefault("category", null);
				preparedStatement.setObject(paramIndex++, category);
				preparedStatement.setObject(paramIndex++, category);

				String status = (String) criteria.getOrDefault("status", null);
				preparedStatement.setObject(paramIndex++, status);
				preparedStatement.setObject(paramIndex++, status);

				String availability = (String) criteria.getOrDefault("availability", null);
				preparedStatement.setObject(paramIndex++, availability);
				preparedStatement.setObject(paramIndex++, availability);
			}

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("BookId");
					String foundTitle = resultSet.getString("Title");
					String foundAuthor = resultSet.getString("Author");
					String foundCategoryStr = resultSet.getString("Category");
					String foundStatusStr = resultSet.getString("Status");
					String foundAvailabilityStr = resultSet.getString("Availablity");

					LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
					String createdBy = resultSet.getString("created_by");
					Timestamp updatedAtTimestamp = resultSet.getTimestamp("updated_at");
					LocalDateTime updatedAt = (updatedAtTimestamp != null) ? updatedAtTimestamp.toLocalDateTime()
							: null;
					String updatedBy = resultSet.getString("updated_by");

					BookCategory foundCategory = BookCategory.fromDisplayName(foundCategoryStr);
					BookStatus foundStatus = BookStatus.fromCode(foundStatusStr);
					BookAvailability foundAvailability = BookAvailability.fromCode(foundAvailabilityStr);

//					Book book = new Book(id, foundTitle, foundAuthor, foundCategory, foundStatus, foundAvailability,
//							createdAt, createdBy, updatedAt, updatedBy);
//					
					Book book =new Book();
					book.setBookId(id);
					book.setTitle(foundTitle);
					book.setAuthor(foundAuthor);
					book.setCategory(foundCategory);
					book.setStatus(foundStatus);
					book.setAvailability(foundAvailability);
					book.setCreatedAt(createdAt);
					book.setCreatedBy(createdBy);
					book.setUpdatedAt(updatedAt);
					book.setUpdatedBy(updatedBy);
					
					books.add(book);
				}
			}
		} catch (Exception e) {
			System.err.println("Database error while searching for books: " + e.getMessage());
			e.printStackTrace();
			throw new LibraryException("Failed to search for books: " + e.getMessage(), e);
		}
		return books;
	}

	private boolean logBookChange(Book book, Connection connection) {
		String sql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availablity, "
				+ "original_created_at, original_created_by, original_updated_at, original_updated_by,LogDate) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			int paramIndex = 1;
			preparedStatement.setInt(paramIndex++, book.getBookId());
			preparedStatement.setString(paramIndex++, book.getTitle());
			preparedStatement.setString(paramIndex++, book.getAuthor());
			preparedStatement.setString(paramIndex++, book.getCategory().getDisplayName());
			preparedStatement.setString(paramIndex++, book.getStatus().getCode());
			preparedStatement.setString(paramIndex++, book.getAvailability().getCode());

			preparedStatement.setTimestamp(paramIndex++,
					book.getCreatedAt() != null ? Timestamp.valueOf(book.getCreatedAt()) : null);

			preparedStatement.setString(paramIndex++, book.getCreatedBy());

			preparedStatement.setTimestamp(paramIndex++,
					book.getUpdatedAt() != null ? Timestamp.valueOf(book.getUpdatedAt()) : null);
			preparedStatement.setString(paramIndex++, book.getUpdatedBy());

			preparedStatement.setTimestamp(paramIndex++, Timestamp.valueOf(LocalDateTime.now()));

			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				return true;
			}
			System.out.println("Logged book change for ID " + book.getBookId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	private Book getBookByIdForInternalUse(int bookId) throws LibraryException {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("bookId", bookId);
		List<Book> books = findBooks(criteria);
		return books.isEmpty() ? null : books.get(0);
	}

	@Override
	public boolean deleteBooksBatch(List<Integer> bookIds) throws LibraryException {
		String sql = "DELETE FROM books WHERE BookId = ?";
		boolean results = false;
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				for (int i = 0; i < bookIds.size(); i++) {
					int bookId = bookIds.get(i);
					Book existingBook = getBookByIdForInternalUse(bookId);
					if (existingBook != null) {

						boolean loged = logBookChange(existingBook, connection);

						if (!loged) {
							throw new LibraryException("Failed to log book: ");
						}

						preparedStatement.setInt(1, bookId);
						preparedStatement.addBatch();
					} else {
						System.out.println("Skipping batch delete for Book ID " + bookId + ": Not found.");
						results = false;
					}
				}

				int[] updateCounts = preparedStatement.executeBatch();

				for (int i = 0; i < updateCounts.length; i++) {
					if (updateCounts[i] == Statement.EXECUTE_FAILED) {
						results = false;
						System.err.println(
								"Batch delete failed for book at index " + i + " (ID: " + bookIds.get(i) + ")");
					} else {
						results = true;
						System.out.println("Batch delete successful for book ID " + bookIds.get(i));
					}
				}
				connection.commit();
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
					System.err.println("Batch delete transaction rolled back: " + e.getMessage());
				} catch (Exception rollbackEx) {
					System.err.println("Error during rollback after batch delete error: " + rollbackEx.getMessage());
				}
			}
			System.err.println("Database error during batch delete books: " + e.getMessage());
			throw new LibraryException("Failed to batch delete books: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					System.err.println("Error closing connection for batch delete transaction: " + e.getMessage());
				}
			}
		}
		return results;
	}

	public boolean updateBookAvailabilityBatch(List<Integer> bookIds, String updatedBy) throws LibraryException {
		String sql = "UPDATE books SET Availablity = ?, updated_by = ?, updated_at = CURRENT_TIMESTAMP WHERE BookId = ?";
		boolean results = false;
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				for (int i = 0; i < bookIds.size(); i++) {
					int bookId = bookIds.get(i);
					Book existingBook = getBookByIdForInternalUse(bookId);
					if (existingBook != null) {

//						logBookChange(existingBook, connection);
						boolean loged = logBookChange(existingBook, connection);

						if (!loged) {
							throw new LibraryException("Failed to log book: ");
						}

						int param = 1;
						preparedStatement.setString(param++,
								BookAvailability.fromCode(
										existingBook.getAvailability().getCode()) == BookAvailability.AVAILABLE
												? BookAvailability.ISSUED.getCode()
												: BookAvailability.AVAILABLE.getCode());
						preparedStatement.setString(param++, updatedBy);
						preparedStatement.setInt(param++, bookId);
						preparedStatement.addBatch();
					} else {
						System.out.println("Skipping batch update availability for Book ID " + bookId + ": Not found.");
						results = false;
					}
				}

				// check

				int[] updateCounts = preparedStatement.executeBatch();

				for (int i = 0; i < updateCounts.length; i++) {
					if (updateCounts[i] == Statement.EXECUTE_FAILED) {
						results = false;
						System.err.println("Batch update availability failed for book at index " + i + " (ID: "
								+ bookIds.get(i) + ")");
					} else {
						results = true;
						System.out.println("Batch update availability successful for book ID " + bookIds.get(i));
					}
				}
				connection.commit();
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
					System.err.println("Batch update availability rolled back: " + e.getMessage());
				} catch (Exception rollbackEx) {
					System.err.println("Error during rollback after Batch update availability error: " + rollbackEx.getMessage());
				}
			}
			System.err.println("Database error during Batch update availability books: " + e.getMessage());
			throw new LibraryException("Failed to Batch update availability books: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					System.err.println("Error closing connection for Batch update availability transaction: " + e.getMessage());
				}
			}
		}
		return results;
	}

}