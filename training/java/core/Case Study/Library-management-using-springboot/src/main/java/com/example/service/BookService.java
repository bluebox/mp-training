package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.model.Books;
import com.library.exception.InvalidInputException;

@Service
public class BookService {
	public String add(Books b) {
		try {
			validateBook(b);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice",
					"Vbhanu@123");
			PreparedStatement ps = conn.prepareStatement("insert into books values(?,?,?,?,?,?)");
			ps.setLong(1, b.getBookId());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getCategory());
			ps.setString(5, String.valueOf(b.getStatus()));
			ps.setString(6, String.valueOf(b.getAvailability()));
			if (!ps.execute()) {
				return "Insertion is done";
			} else {
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

	public ArrayList<Books> showBooks() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice",
					"Vbhanu@123");
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery("select * from books");
			ArrayList<Books> l = new ArrayList<Books>();
			while (res.next()) {
				l.add(new Books(res.getLong(1), res.getString(2), res.getString(3), res.getString(4)));
				System.out.println(res.getLong(1) + " " + res.getString(6));
			}
			System.out.println(l);
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void validateBook(Books book) throws InvalidInputException {
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

	public String update(long BookId, String title, String author, String category, char status, char availability) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice",
					"Vbhanu@123");
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery("select * from books where bookId=" + BookId);
			if (res.next()) {
				PreparedStatement ps = conn.prepareStatement(
						"UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE bookId=?");
				ps.setString(1, title);
				ps.setString(2, author);
				ps.setString(3, category);
				ps.setString(4, String.valueOf(status));
				ps.setString(5, String.valueOf(availability));
				ps.setLong(6, BookId);
				int rows = ps.executeUpdate();
				if (rows > 0) {
					return "updated";
				} else {
					return "not updated";
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not updated";
	}

	public String delete(long bookId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice",
					"Vbhanu@123");
			PreparedStatement ps = conn.prepareStatement("delete from books where bookId=?");
			ps.setLong(1, bookId);
			if (ps.executeUpdate() > 0) {
				return "deleted";
			}
			return "not deleted";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "not deleted";
	}

	public String changeStatus(long bookId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice",
					"Vbhanu@123");
			PreparedStatement selectStmt = conn.prepareStatement("SELECT status FROM books WHERE bookId = ?");
			selectStmt.setLong(1, bookId);
			ResultSet res = selectStmt.executeQuery();
			if (res.next()) {
				char status = res.getString("status").charAt(0);
				char nstatus = status == 'A' ? 'I' : 'A';
				PreparedStatement updateStmt = conn.prepareStatement("UPDATE books SET status = ? WHERE bookId = ?");
				updateStmt.setString(1, String.valueOf(nstatus));
				updateStmt.setLong(2, bookId);

				int rowsAffected = updateStmt.executeUpdate();
				if (rowsAffected > 0) {
					return "updated";
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "not updated";
	}

}