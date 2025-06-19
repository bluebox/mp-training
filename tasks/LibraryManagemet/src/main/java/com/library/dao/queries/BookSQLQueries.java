package com.library.dao.queries;

public class BookSQLQueries {
	protected String insertBookQuery = "INSERT INTO book(title, author, category, status, availability) VALUES(?, ?, ?, ?, ?)";

	protected String selectAllBooks = "SELECT * FROM book";
	protected String changeBookAvaliability = "UPDATE book SET availability = 'A' WHERE id = ?";
	protected String isBookExists = "SELECT * FROM book WHERE id = ?";
	protected String insertIntoBookLogs = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) "
			+ "SELECT id, title, author, category, status, availability FROM book WHERE id = ?";
	protected String updateBook = "UPDATE book SET title = ?, author = ?, category = ?, status = ? WHERE id = ?";
	protected String checkBookAvailability = "SELECT status, availability FROM book WHERE id = ?";

	protected String updateBookAvailability = "UPDATE book SET availability = 'I' WHERE id = ?";

}
