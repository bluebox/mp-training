package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.domain.Book;
import com.library.utilities.ConnectionMaker;

public class BookDAO {
	public List<Book> viewBooks() {
		List<Book> books = new ArrayList<>();
		try (Connection conn = ConnectionMaker.getConnection();) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBookId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setCategory(resultSet.getString("category"));
				book.setStatus(resultSet.getString("status").charAt(0));
				book.setAvailability(resultSet.getString("availability").charAt(0));

				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Used For Testing
//		for (Book book : books) {
//            System.out.println("ID: " + book.getBookId());
//            System.out.println("Title: " + book.getTitle());
//            System.out.println("Author: " + book.getAuthor());
//            System.out.println("Category: " + book.getCategory());
//            System.out.println("Status: " + (book.getStatus() == 'A' ? "Active" : "Inactive"));
//            System.out.println("Availability: " + (book.getAvailability() == 'A' ? "Available" : "Issued"));
//            System.out.println("--------------------------------------------------");
//        }
		//System.out.print(books);
		return books;

	}

}
