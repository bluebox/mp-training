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
import java.util.stream.Collectors;

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

			preparedStatement.executeUpdate();

		} catch (Exception e) {
			throw new LibraryException("Failed to add book: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean updateBook(Book book, String updatedBy) throws LibraryException {
		Book existingBook = getBookById(book.getBookId());
		if (existingBook == null) {
			return false;
		}
		
		boolean changesDetected = existingBook.hashCode()!=book.hashCode();
		

		if (!changesDetected) {
			
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
					connection.commit();
					return true;
				} else {
					connection.rollback();
					return false;
				}
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rolle) {
					rolle.printStackTrace();
				}
			}
			throw new LibraryException("Failed to update book: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
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
			e.printStackTrace();
			throw new LibraryException("Failed to search for books: " + e.getMessage(), e);
		}
		return books;
	}

	@Override
	public boolean deleteBooks(List<Integer> bookIds) throws LibraryException {
		
		String StringBookIds = bookIds.stream()
                .map(String::valueOf) 
                .collect(Collectors.joining(", "));
		
		String sql = "DELETE FROM books WHERE BookId IN ("+StringBookIds +")";
		
		
		boolean results = false;
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);

			try (Statement Statement = connection.createStatement()) {
				for (int i = 0; i < bookIds.size(); i++) {
					int bookId = bookIds.get(i);
					Book existingBook = getBookById(bookId);
					if (existingBook != null) {

						boolean loged = logBookChange(existingBook, connection);

						if (!loged) {
							throw new LibraryException("Failed to log book: ");
						}
						
					} else {
						results = false;
					}
				}
								

				int res = Statement.executeUpdate(sql);
				
				if(res>0) {
					results =true;
				}

				connection.commit();
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rolle) {
					rolle.printStackTrace();
				}
			}
			throw new LibraryException("Failed to  delete books: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return results;
	}

	public boolean updateBookAvailability(List<Integer> bookIds, String updatedBy) throws LibraryException {
		
		String StringBookIds = bookIds.stream()
                .map(String::valueOf) 
                .collect(Collectors.joining(", "));
		
		String sql = "UPDATE books SET Availablity = ?, updated_by = ?, updated_at = CURRENT_TIMESTAMP WHERE BookId IN ("+StringBookIds+")";
		
		boolean results = false;
		Connection connection = null;
		try {
			connection = DBConnection.getConnection();
			connection.setAutoCommit(false);
			
			Book existingBook = null;
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				for (int i = 0; i < bookIds.size(); i++) {
					int bookId = bookIds.get(i);
					existingBook = getBookById(bookId);
					if (existingBook != null) {

						boolean loged = logBookChange(existingBook, connection);

						if (!loged) {
							throw new LibraryException("Failed to log book: ");
						}

					} else {
						results = false;
					}
				}
				
				int param = 1;
				preparedStatement.setString(param++,
						BookAvailability.fromCode(
								existingBook.getAvailability().getCode()) == BookAvailability.AVAILABLE
										? BookAvailability.ISSUED.getCode()
										: BookAvailability.AVAILABLE.getCode());
				preparedStatement.setString(param++, updatedBy);


				int updateCounts = preparedStatement.executeUpdate();

				if(updateCounts>0) {
					return true;
				}
				
				connection.commit();
			}
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception rolle) {
					rolle.printStackTrace();
				}
			}
			throw new LibraryException("Failed to update availability books: " + e.getMessage(), e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return results;
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

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Book getBookById(int bookId) throws LibraryException {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put("bookId", bookId);
		List<Book> books = findBooks(criteria);
		return books.isEmpty() ? null : books.get(0);
	}

}

