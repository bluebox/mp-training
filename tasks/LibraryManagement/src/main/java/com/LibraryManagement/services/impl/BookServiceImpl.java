package com.LibraryManagement.services.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.LibraryManagement.dao.BookDao;
import com.LibraryManagement.dao.impl.BookDaoImpl;
import com.LibraryManagement.services.BookService;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.pojos.Book;

public class BookServiceImpl implements BookService{
	private BookDao bd=new BookDaoImpl();
	
	@Override
	public boolean addBookService(Book book) throws Exception {
		Connection con=DBConnection.connectDB();
		con.setAutoCommit(false);
		if(bd.verifyBook(book)) {
			if(bd.addBook(book)) {
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
			con.rollback();
			con.setAutoCommit(true);
			return false;
		}

		return false;
	}

	@Override
	public ArrayList<Book> viewAllBooksService() throws Exception {
		return bd.viewAllBooks();
	}

	@Override
	public ArrayList<Book> viewAllBooksLogService() throws Exception {
		return bd.viewAllBooksLogs();
	}


	@Override
	public boolean updateBookService(int bookId, Character availability) throws Exception {
		Connection con=DBConnection.connectDB();
		try {
			con.setAutoCommit(false);
			if(bd.verifyBook(bookId,availability)) {
				if(bd.updateBook(bookId, availability)) {
					con.commit();
					return true;
				}
				else {
					return false;
				}
			}
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
		finally {
			con.setAutoCommit(true);
		}
		return false;
	}

	

}
