package com.library.dao;

import java.sql.*;
import java.util.*;
import com.library.controller.Book;
import com.library.util.AlertMsg;
import com.library.util.DBConnect;

public class BookDao {
    private Connection getConnection() throws Exception {
        return DBConnect.getConnection();
    }

    public void addBook(Book book) {
        String s = "insert into Books (Title, Author, Category, BookStatus, Availability) values (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(s)) {
        	conn.setAutoCommit(false);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, String.valueOf(book.getStatus()));
            ps.setString(5, String.valueOf(book.getAvailability()));
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }
//    data to BooksLog
//    public void addToBooksLog(Book book) throws Exception {
//		// TODO Auto-generated method stub
//    	String s="insert into BooksLog(BookId,Title,Author,Category,BookStatus,Availability) values(?,?,?,?,?,?)";
//    	try(Connection conn=getConnection();
//    			PreparedStatement ps=conn.prepareStatement(s)){
//    		ps.setInt(1, book.getBookId());
//    		ps.setString(2, book.getTitle());
//            ps.setString(3, book.getAuthor());
//            ps.setString(4, book.getCategory());
//            ps.setString(5, String.valueOf(book.getStatus()));
//            ps.setString(6, String.valueOf(book.getAvailability()));
//            ps.executeUpdate();
//    	}catch(Exception e) {
//    		AlertMsg.showError(e.getMessage());
//    	}
//		
//	}

	public void updateBook(Book book) {
        String sql = "update Books set Title=?, Author=?, Category=?, BookStatus=? where BookId=?";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(sql)) {
        	conn.setAutoCommit(false);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setString(4, String.valueOf(book.getStatus()));
            ps.setInt(5, book.getBookId());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }

    public void updateAvailability(int bookId, char availability) {
        String sql = "update Books set Availability=? where BookId=?";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(availability));
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "select * from Books";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(
                    rs.getInt("BookId"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getString("Category"),
                    rs.getString("BookStatus").charAt(0),
                    rs.getString("Availability").charAt(0)
                ));
            }
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
        return list;
    }
    public Book getBookById(int bookId) {
        String sql = "select * from Books where BookId=?";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("BookId"),
                    rs.getString("Title"),
                    rs.getString("Author"),
                    rs.getString("Category"),
                    rs.getString("BookStatus").charAt(0),
                    rs.getString("Availability").charAt(0)
                );
            }
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
        return null;
    }

	public void updateAvailabilityAndStatus(int bookId, char availability, char status) {
		// TODO Auto-generated method stub
		String s="update Books set Availability=?,BookStatus=? where BookId=?";
		try(Connection conn=getConnection();
				PreparedStatement ps=conn.prepareStatement(s);){
			ps.setString(1, String.valueOf(availability));
			ps.setString(2,String.valueOf(status));
			ps.setInt(3,bookId);
			ps.executeUpdate();
		}catch(Exception e) {
			AlertMsg.showError(e.getMessage());
		}
		
	}
}
    