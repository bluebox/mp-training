package com.library.queryLoader;

public class getSqlQuery {
	
	 public static final String getAllBooks = "SELECT BookId, Title, Author, Category, Status, Availability FROM books";
	 public static final String insertIntoBook = "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
	 public static final String updateMember = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
}
