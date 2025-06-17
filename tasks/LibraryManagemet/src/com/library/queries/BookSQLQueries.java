package com.library.queries;

public class BookSQLQueries {
	protected String selectAllBooks="SELECT * FROM book";
	protected String changeBookAvaliability="UPDATE book SET availability = 'A' WHERE id = ?";
	

}
