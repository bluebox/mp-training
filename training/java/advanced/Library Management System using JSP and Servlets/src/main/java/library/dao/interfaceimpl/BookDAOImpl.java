package library.dao.interfaceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

		} catch (SQLException e) {
			System.err.println("Database error while adding book: " + e.getMessage());
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

		boolean changesDetected = !existingBook.getTitle().equals(book.getTitle())
				|| !existingBook.getAuthor().equals(book.getAuthor())
				|| !existingBook.getCategory().equals(book.getCategory())
				|| !existingBook.getStatus().equals(book.getStatus());

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

			logBookChange(existingBook, connection);

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
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
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
				} catch (SQLException e) {
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

			logBookChange(existingBook, connection);

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
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
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
				} catch (SQLException e) {
					System.err
							.println("Error closing connection for availability update transaction: " + e.getMessage());
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

			logBookChange(existingBook, connection);

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
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
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
				} catch (SQLException e) {
					System.err.println("Error closing connection for delete transaction: " + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<Book> findBooks(Book criteria) throws LibraryException {
	    List<Book> books = new ArrayList<>();
	    StringBuilder sqlBuilder = new StringBuilder("SELECT BookId, Title, Author, Category, Status, Availablity, created_at, created_by, updated_at, updated_by FROM books WHERE 1=1");
	    List<Object> params = new ArrayList<>();

	    if (criteria.getBookId() != 0) {
	        sqlBuilder.append(" AND BookId = ?");
	        params.add(criteria.getBookId());
	    }
	    if (criteria.getTitle() != null && !criteria.getTitle().trim().isEmpty()) {
	        sqlBuilder.append(" AND Title LIKE ?");
	        params.add("%" + criteria.getTitle().trim() + "%");
	    }
	    if (criteria.getAuthor() != null && !criteria.getAuthor().trim().isEmpty()) {
	        sqlBuilder.append(" AND Author LIKE ?");
	        params.add("%" + criteria.getAuthor().trim() + "%");
	    }
	    if (criteria.getCategory() != null) {
	        sqlBuilder.append(" AND Category = ?");
	        params.add(criteria.getCategory().getDisplayName());
	    }
	    if (criteria.getStatus() != null) {
	        sqlBuilder.append(" AND Status = ?");
	        params.add(criteria.getStatus().getCode());
	    }
	    if (criteria.getAvailability() != null) {
	        sqlBuilder.append(" AND Availablity = ?");
	        params.add(criteria.getAvailability().getCode());
	    }

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlBuilder.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            preparedStatement.setObject(i + 1, params.get(i));
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

	                Book book = new Book(id, foundTitle, foundAuthor, foundCategory, foundStatus, foundAvailability,
	                        createdAt, createdBy, updatedAt, updatedBy);
	                books.add(book);
	            }
	            System.out.println("BookDAO: Finished processing results. Total books found: " + books.size());
	        }
	    } catch (SQLException e) {
	        System.err.println("Database error while searching for books: " + e.getMessage());
	        e.printStackTrace();
	        throw new LibraryException("Failed to search for books: " + e.getMessage(), e);
	    }
	    return books;
	}
	private void logBookChange(Book book, Connection connection) {
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

			preparedStatement.executeUpdate();
			System.out.println("Logged book change for ID " + book.getBookId());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private Book getBookByIdForInternalUse(int bookId) throws LibraryException {
		Book criteria = new Book();
		criteria.setBookId(bookId);
		List<Book> books = findBooks(criteria);
		return books.isEmpty() ? null : books.get(0);
	}

	@Override
	public int deleteBooksBatch(List<Integer> bookIds) throws LibraryException {
		String sql = "DELETE FROM books WHERE BookId = ?";
		Connection connection = null;
		int totalDeleted = 0;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				for (Integer bookId : bookIds) {
					Book existingBook = getBookByIdForInternalUse(bookId);
					if (existingBook != null) {
						logBookChange(existingBook, connection);
						preparedStatement.setInt(1, bookId);
						preparedStatement.addBatch();
					} else {
						System.out.println("Skipping batch delete for Book ID " + bookId + ": Not found.");
					}
				}

				int[] updateCounts = preparedStatement.executeBatch();

				for (int count : updateCounts) {
                    if (count > 0) { 
                        totalDeleted += count;
                    } else {
                         System.err.println("Batch delete failed for a book (0 rows affected).");
                    }
				}
				connection.commit();
                System.out.println("Batch delete operation completed. Total deleted: " + totalDeleted);
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
					System.err.println("Batch delete transaction rolled back: " + e.getMessage());
				} catch (SQLException rollbackEx) {
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
				} catch (SQLException e) {
					System.err.println("Error closing connection for batch delete transaction: " + e.getMessage());
				}
			}
		}
		return totalDeleted;
	}
	
	
	@Override
	public int updateBookAvailabilityBatch(List<Integer> bookIds,String updatedBy) throws LibraryException {
		String sql = "UPDATE books SET Availablity = ?, updated_by = ?, updated_at = CURRENT_TIMESTAMP WHERE BookId = ?";
		Connection connection = null;
		int totalUpdated = 0;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				for (Integer bookId : bookIds) {
					Book existingBook = getBookByIdForInternalUse(bookId);
					if (existingBook != null) {
						logBookChange(existingBook, connection);
						int param =1;
						preparedStatement.setString(param++, BookAvailability.fromCode(existingBook.getAvailability().getCode()) == BookAvailability.AVAILABLE 
								? BookAvailability.ISSUED.getCode(): BookAvailability.AVAILABLE.getCode());
						preparedStatement.setString(param++, updatedBy);
						preparedStatement.setInt(param++, bookId);
						preparedStatement.addBatch();
					} else {
						System.out.println("Skipping batch update availability for Book ID " + bookId + ": Not found.");
					}
				}

				int[] updateCounts = preparedStatement.executeBatch();

				for (int count : updateCounts) {
                    if (count > 0) {
                        totalUpdated += count;
                    } else {
                         System.err.println("Batch update failed for a book (0 rows affected).");
                    }
				}
				connection.commit();
				System.out.println("Batch update availability operation completed. Total updated: " + totalUpdated);
			}
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
					System.err.println("Batch update availability rolled back: " + e.getMessage());
				} catch (SQLException rollbackEx) {
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
				} catch (SQLException e) {
					System.err.println("Error closing connection for Batch update availability transaction: " + e.getMessage());
				}
			}
		}
		return totalUpdated;
	}
} 
