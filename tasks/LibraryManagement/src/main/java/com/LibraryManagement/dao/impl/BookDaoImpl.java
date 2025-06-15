package com.LibraryManagement.dao.impl;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.LibraryManagement.dao.BookDao;
import com.LibraryManagement.exceptions.DataBaseException;
import com.LibraryManagement.exceptions.InvalidBookException;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.DBQueries;
import com.LibraryManagement.utilites.pojos.Book;

public class BookDaoImpl implements BookDao{

	@Override
	public boolean addBook(Book book) throws SQLException, DataBaseException {
		Connection con = null;
		try{
			con=DBConnection.connectDB();
			con.setAutoCommit(false);
			PreparedStatement pst=con.prepareStatement(DBQueries.insertToBooks);
			pst.setString(1,book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setString(3,book.getCategory());
			int countAffectedRows=pst.executeUpdate();
			if(countAffectedRows==0) {
				 con.rollback();
				 System.out.println("Book Not Added");
				 return false;
			}
			con.commit();
			return true;
		}catch(Exception e){
			con.rollback();
			throw new DataBaseException("Error with DataBase", e);
		}finally {
			con.setAutoCommit(true);
		}
	}

	@Override
	public boolean verifyBook(Book book) throws InvalidBookException, DataBaseException{
		if(book.getAuthor()==null || book.getAvailability()==null || book.getTitle()==null) {
			throw new InvalidBookException("Incomplete book data for verification.");
		}
		try(Connection con=DBConnection.connectDB();){
			PreparedStatement pst=con.prepareStatement(DBQueries.getBook);
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setString(3,book.getCategory());
			ResultSet rs=pst.executeQuery();
			if(!rs.next()) return true;
			if(rs.getString(1).equals(book.getTitle()) && rs.getString(2).equals(book.getAuthor()) && rs.getString(3).equals(book.getCategory())) {
				System.out.println("Already Exists");
				return false;
			}
		}catch(Exception e) {
			  throw new DataBaseException("Error in DataBase", e);
		}
		return true;
	}
	

	@Override
	public ArrayList<Book> viewAllBooks() throws Exception {
		ArrayList<Book> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllBooks);
		while(rs.next()) {
			Reader ch1=rs.getCharacterStream(5);
			Reader ch2=rs.getCharacterStream(6);
			Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),(char) (ch1.read()),(char)(ch2.read()));
			arr.add(book);
		}
		return arr;
	}

	public boolean addBookToLog(int bookId){
		try(Connection con=DBConnection.connectDB();) {
			con.setAutoCommit(false);
			PreparedStatement pst=con.prepareStatement(DBQueries.getBookWithId);
			pst.setInt(1, bookId);
			ResultSet rs=pst.executeQuery();
			
			 if (!rs.next()) {
                 System.out.println("No Log Book Data");
                 con.rollback();
                 return false;
             }
			 else rs.next();
			
			Reader ch1=rs.getCharacterStream(5);
			Reader ch2=rs.getCharacterStream(6);
			
			Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),(char) (ch1.read()),(char)(ch2.read()));
			pst=con.prepareStatement(DBQueries.insertToBooksLog);
			pst.setInt(1, book.getBookId());
			pst.setString(2,book.getTitle());
			pst.setString(3, book.getAuthor());
			pst.setString(4,book.getCategory());
			pst.setString(5,String.valueOf(book.getStatus()));
			pst.setString(6, String.valueOf(book.getAvailability()));
			int countAffectedRows=pst.executeUpdate();
			if(countAffectedRows==0) {
				con.rollback();
				return false;
			}
			con.commit();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	@Override
	public boolean updateBook(int bookId, Character availability) throws Exception {
		Connection con=DBConnection.connectDB();
		con.setAutoCommit(false);
		boolean logBook=addBookToLog(bookId);
		if(!logBook) {
			con.rollback();
			return false;
		}
		PreparedStatement pst=con.prepareStatement(DBQueries.updateBook);
		pst.setString(1, String.valueOf(availability));
		pst.setInt(2, bookId);
		
		int rowsAffected=pst.executeUpdate();
		
		if(rowsAffected==0) {
			con.rollback();
			throw new InvalidBookException("No book record found to update");
		}
		con.commit();
		return true;
	}

	@Override
	public ArrayList<Book> viewAllBooksLogs() throws Exception {
		ArrayList<Book> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllBooksLog);
		while(rs.next()) {
			Reader ch1=rs.getCharacterStream(5);
			Reader ch2=rs.getCharacterStream(6);
			Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),(char) (ch1.read()),(char)(ch2.read()));
			arr.add(book);
		}
		return arr;
	}

	@Override
	public boolean verifyBook(int bookId, Character availability) throws Exception {
		Connection con=DBConnection.connectDB();
		PreparedStatement pst=con.prepareStatement(DBQueries.getBookWithId);
		pst.setInt(1, bookId);
		ResultSet rs=pst.executeQuery();
		if(rs.next() && !rs.getString(6).equals(String.valueOf(availability))) {
			return true;
		}
		System.out.println("Update is Same");
		return false;
	}

}
