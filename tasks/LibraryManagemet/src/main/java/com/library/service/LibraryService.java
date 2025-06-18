package com.library.service;

import java.sql.SQLException;
import java.util.List;

import com.library.domain.Book;
import com.library.domain.IssueRecord;
import com.library.domain.Member;

public interface LibraryService {

	public boolean addMember(Member member);

	public List<Book> fetchAllBooks();

	public boolean returnBook(int bookId, int memberId) throws SQLException;

	public boolean isBookIssuedToMember(int bookId, int memberId) throws SQLException;



	public boolean addBook(Book book);

}
