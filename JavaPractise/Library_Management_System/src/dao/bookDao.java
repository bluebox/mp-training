package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.BookStatus;
import domain.AvailabilityStatus;
import util.DBUtil;

public class bookDao {
	
	
	 public void addBook(Book book) {
		 String sql = "insert into books(Title,Author,Category,Status,Availability) values (?, ?, ?, ?, ?)";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1,book.getTitle());
	            ps.setString(2,book.getAuthor());
	            ps.setString(3,book.getCategory());
	            ps.setString(4,book.getStatus().name());
	            ps.setString(5,book.getAvailability().name());
	            ps.executeUpdate();
	            System.out.println("Book added.");
	        } 
	        catch (Exception e) {
	            System.out.println("Error adding Book ");
	        }
		
	 }

	

	 public void updateBookDetails(int id, String title, String author, String category, BookStatus status) {
		// TODO Auto-generated method stub
		 String sql = "update books set Title=?, Author=?,  Category=? ,Status=? Where bookId=?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1,title);
	            ps.setString(2,author);
	            ps.setString(3,category);
	            ps.setString(4, status.name());
	            ps.setInt(5,id );
	            ps.executeUpdate();
	            System.out.println("Book updated.");
	        } 
	        catch (Exception e) {
	            System.out.println("Error updating Book");
	        }
		
	 }



	 public void updateBookAvailability(int id, AvailabilityStatus avail) {
		// TODO Auto-generated method stub
		 String sql = "update books set Availability=? Where BookId=?";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, avail.name());
	            ps.setInt(2,id );
	            ps.executeUpdate();
	            System.out.println("Book updated.");
	        } 
	        catch (Exception e) {
	            System.out.println("Error updating Book");
	        }
		
	 }



	 public List<Book> getAllBooks() {
		 List<Book> list = new ArrayList<>();
	        String sql = "select * from books";
	        try (Connection conn = DBUtil.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                list.add(
	                		new Book(
	                				rs.getInt(1),
	                				rs.getString(2),
	                				rs.getString(3), 
	                				rs.getString(4),
	                				BookStatus.valueOf(rs.getString(5).trim().toUpperCase()),
	                				AvailabilityStatus.valueOf(rs.getString(6).trim().toUpperCase())));
	            }
	        } 
	        catch (Exception e) {
	            System.out.println("Error fetching members");
	        }
	        return list;
	    }
	 
		

}
