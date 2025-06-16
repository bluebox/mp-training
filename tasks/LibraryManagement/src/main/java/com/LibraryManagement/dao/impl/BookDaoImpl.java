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
			PreparedStatement pst=con.prepareStatement(DBQueries.INSERT_TO_BOOKS);
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
		if(book.getAuthor()==null || book.getTitle()==null || book.getTitle()==null) {
			throw new InvalidBookException("Incomplete book data for verification.");
		}
		try(Connection con=DBConnection.connectDB();){
			PreparedStatement pst=con.prepareStatement(DBQueries.GET_BOOK);
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setString(3,book.getCategory());
			ResultSet rs=pst.executeQuery();
			if(!rs.next()) return true;
			if(rs.getString(1).equals(book.getTitle()) && rs.getString(2).equals(book.getAuthor()) && rs.getString(3).equals(book.getCategory())) {
				return false;
			}
		}catch(Exception e) {
			  throw new DataBaseException("Error in DataBase", e);
		}
		return false;
	}
	

	@Override
	public ArrayList<Book> viewAllBooks() throws Exception {
		ArrayList<Book> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.GET_ALL_BOOKS);
		while(rs.next()) {
			Reader ch1=rs.getCharacterStream(5);
			Reader ch2=rs.getCharacterStream(6);
			Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),(char) (ch1.read()),(char)(ch2.read()));
			arr.add(book);
		}
		return arr;
	}

	private boolean addBookToLog(int bookId){
		try(Connection con=DBConnection.connectDB();) {
			
			PreparedStatement pst=con.prepareStatement(DBQueries.GET_BOOK_WITH_ID);
			pst.setInt(1, bookId);
			ResultSet rs=pst.executeQuery();
			rs.next();
			Reader ch1=rs.getCharacterStream(5);
			Reader ch2=rs.getCharacterStream(6);
			
			Book book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),(char) (ch1.read()),(char)(ch2.read()));
			pst=con.prepareStatement(DBQueries.INSERT_TO_BOOKS_LOG);
			pst.setInt(1, book.getBookId());
			pst.setString(2,book.getTitle());
			pst.setString(3, book.getAuthor());
			pst.setString(4,book.getCategory());
			pst.setString(5,String.valueOf(book.getStatus()));
			pst.setString(6, String.valueOf(book.getAvailability()));
			pst.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	@Override
	public boolean updateBook(int bookId, Character availability) throws Exception {
		Connection con=DBConnection.connectDB();
		addBookToLog(bookId);
		PreparedStatement pst=con.prepareStatement(DBQueries.UPDATE_BOOK);
		pst.setString(1, String.valueOf(availability));
		pst.setInt(2, bookId);
		int cnt=pst.executeUpdate();
		return cnt>0;
	}

	@Override
	public ArrayList<Book> viewAllBooksLogs() throws Exception {
		ArrayList<Book> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.GET_ALL_BOOKS_LOG);
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
		PreparedStatement pst=con.prepareStatement(DBQueries.GET_BOOK_WITH_ID);
		pst.setInt(1, bookId);
		ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}

}
