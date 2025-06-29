package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.library.dao.Book;
import com.library.exception.InvalidInputException;

public class BookService {
	public static String add(Book b) {
		try {
			validateBook(b);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			PreparedStatement ps = conn.prepareStatement("insert into books values(?,?,?,?,?,?)");
			ps.setLong(1, b.getBookId());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getCategory());
			ps.setString(5, String.valueOf(b.getStatus()));
			ps.setString(6, String.valueOf(b.getAvailability()));
			if(!ps.execute()) {
				return "Insertion is done";
			}
			else {
				return "Insertion failed";
			}
		} catch (InvalidInputException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet showBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
			Statement s=conn.createStatement();
			return s.executeQuery("select * from books");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static void updateBook(Book b) {
//		ps=DBConnection.getConnection().prepareStatement("update books set title=)
//	}
	
	private static void validateBook(Book book) throws InvalidInputException {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null.");
        }
        if (book.getTitle().isEmpty() || book.getAuthor().isEmpty() || book.getCategory().isEmpty()) {
            throw new InvalidInputException("Title, Author, and Category are required.");
        }
        if (book.getStatus() != 'A' && book.getStatus() != 'I') {
            throw new InvalidInputException("Status must be A or I.");
        }
    }
}
