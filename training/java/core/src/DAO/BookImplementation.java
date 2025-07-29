package DAO;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;

public class BookImplementation implements BookInterface {
	private static final String url="jdbc:mysql://127.0.0.1:3306/librarymanagementsystem";
	private static  final String username="root";
	private static final String password="kavi@2";
//    BookImplementation book=new BookImplementation();
	
	public static  Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			System.out.println("connection established with LibraryManagementSystemDB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@Override
	public int AddBook(Book book) throws SQLException {
		
		String query="insert into librarymanagementsystem.books (Title,Author,Category,status,Availablity) values(?,?,?,?,?) ";
		//String logquery="insert into librarymanagementsystem.bookslog (BookId,Title,Author,Category,status,Availability) values(?,?,?,?,?,?) ";
		
		Connection connection=getConnection();
		try(
			PreparedStatement preparestatement=connection.prepareStatement(query);){
			    preparestatement.setString(1, book.getTitle()); 
	            preparestatement.setString(2,book.getAuthor() ); 
	            preparestatement.setString(3,book.getCategory()); 
	            preparestatement.setString(4,""+book.getStatus().getType());
	            preparestatement.setString(5,""+book.getAvailability().getType());
			    System.out.println("Added new Book to Book Table ");
			    int b=preparestatement.executeUpdate();
	            
	            connection.commit();
	            return b;
		}catch(Exception e) {
			connection.rollback();
			System.out.println("Failed to add new Book to Book Table ");
			throw new SQLException("the error occured with the message "+e.getMessage());
		}
	}

	@Override
	public Book updateBookDetails(Book book) throws SQLException {
		String query="update librarymanagementsystem.books set Category=?,status=? where BookId=? ";
		String logquery="insert into librarymanagementsystem.bookslog (BookId,Title,Author,Category,status,Availablity) values(?,?,?,?,?,?) ";
		Book returnbook=null;
		Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(query);
				PreparedStatement preparestatementlog=connection.prepareStatement(logquery);){
			    if(getBookbyId(book.getBookId()) != null) {
	            preparestatementlog.setInt(1,book.getBookId());
			    preparestatementlog.setString(2, book.getTitle()); 
	            preparestatementlog.setString(3,book.getAuthor() ); 
	            preparestatementlog.setString(4,book.getCategory()); 
	            preparestatementlog.setString(5,""+book.getStatus().getType());
	            preparestatementlog.setString(6, ""+getBookbyId(book.getBookId()).getAvailability().getType());
	            
	            preparestatement.setString(1,book.getCategory()); 
	            preparestatement.setString(2,""+book.getStatus().getType());
	            preparestatement.setInt(3,book.getBookId());
	           
	            if((preparestatement.executeUpdate()==1) && preparestatementlog.executeUpdate()==1) {
			    System.out.println("updated "+book.getBookId()+" Book to Book Table ");	
			    returnbook=getBookbyId(book.getBookId());
			    connection.commit();
	            }
			    }			  
		}catch(Exception e) {
			connection.rollback();
			System.out.println("Failed to update Book in Book Table ");
			throw new SQLException("the error occured with the message "+e.getMessage());
		}
		return returnbook;
	}

	@Override
	public Book updateAvailability(int id,BookAvailability availability) throws SQLException{
		String query="update librarymanagementsystem.books set Availablity=? where BookId=? ";
		String logquery="insert into librarymanagementsystem.bookslog (BookId,Title,Author,Category,status,Availablity) values(?,?,?,?,?,?) ";
		Book returnbook=null;
		Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(query);
				PreparedStatement preparestatementlog=connection.prepareStatement(logquery);){
			  Book book=getBookbyId(id);
			    if(book!= null) {
	            preparestatementlog.setInt(1,book.getBookId());
			    preparestatementlog.setString(2, book.getTitle()); 
	            preparestatementlog.setString(3,book.getAuthor() ); 
	            preparestatementlog.setString(4,book.getCategory()); 
	            preparestatementlog.setString(5,""+book.getStatus().getType());
	            preparestatementlog.setString(6, ""+book.getAvailability().getType());
	            
	            
	            preparestatement.setString(1,""+availability.getType());
	            preparestatement.setInt(2,book.getBookId());
	           
	            if((preparestatement.executeUpdate()==1) && preparestatementlog.executeUpdate()==1) {
			    System.out.println("updated "+book.getBookId()+" Book to Book Table ");
			    returnbook=getBookbyId(book.getBookId());
			    connection.commit();
	            }
			    }			   
		}catch(Exception e) {
			connection.rollback();
			System.out.println("Failed to update Book in Book Table ");
			throw new SQLException("the error occured with the message "+e.getMessage());
		}
		return returnbook;
		
	}

	@Override
	public List<Book> getBooks() throws SQLException {
	    String getString ="select BookId,Title,Author,Category,Status,Availablity from librarymanagementsystem.Books ";
	    Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(getString);){
			ResultSet rs=preparestatement.executeQuery();
			List<Book> books=new ArrayList<>();
			while(rs.next()) {
			  books.add(new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),BookStatus.getStatus(rs.getString("status").charAt(0)),BookAvailability.getAvailability(rs.getString("Availablity").charAt(0))));
			}
			return books;
		}catch(Exception e) {
			throw new SQLException("the error occured with the message "+e.getMessage());
		}
		
	}

	@Override
	public Book getBookbyId(int BookId) throws SQLException {
		String Query="Select BookId,Title,Author,Category,Status,Availablity from librarymanagementsystem.Books where BookId=?";
		Connection connection=getConnection();
		try(PreparedStatement preparestatement=connection.prepareStatement(Query);){
			preparestatement.setInt(1, BookId);
			ResultSet rs=preparestatement.executeQuery();
			Book book=null;
			if(rs.next()) {
			 book= new Book(rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getString("Category"),BookStatus.getStatus(rs.getString("status").charAt(0)),BookAvailability.getAvailability(rs.getString("Availablity").charAt(0)));
			}
			return book;
		}catch(Exception e) {
			throw new SQLException("the error occured with the message "+e.getMessage());
		}
		
	}
	public static void main(String[] args) throws SQLException {
		BookImplementation b=new BookImplementation();
		System.out.print(b.getBooks());
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
