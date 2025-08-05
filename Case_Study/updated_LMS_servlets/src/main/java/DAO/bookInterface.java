package DAO;

import java.util.List;

import domain.Book;


public interface bookInterface {
	boolean addBooks(Book book) throws Exception;
	boolean updateBookDetails(int id,Book book) throws Exception;
	List<Book> viewallbooks() throws Exception;
	
//	boolean issueBook(int bookId, int memberId) throws Exception;
//	boolean returnBook(int bookId, int memberId) throws Exception;
//	List<Issue_records> printAllIssueRecords() throws Exception;
	
	

}
