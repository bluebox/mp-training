package com.libraryManagement.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.libraryManagement.dao.BookDao;
import com.libraryManagement.utility.DBConnection;
import com.libraryManagement.utility.pojos.Book;

public class BookDaoImpl implements BookDao {

	@Override
	public boolean addBook(Book book) {
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Book> getByBookId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyBook(Book book) throws ClassNotFoundException, IOException, SQLException {
		Connection conn=DBConnection.getConnection();
		String q="select * from Book where Title=? and Author=? and Category=?";
		PreparedStatement st=conn.prepareStatement(q);
		st.setString(1,book.getTitle());
		st.setString(2,book.getAuthor());
		st.setString(3,book.getCategory());
		int cnt=st.executeUpdate();
		return cnt>0;
	}

}
