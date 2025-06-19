package com.library.dao;

import java.sql.Connection;
import java.util.List;

import com.library.domain.IssueRecord;

public interface IssueBookDAO {
	public void issueBook(Connection conn, int bookId, int memberId);
	public List<IssueRecord> getAllIssuedBooks(Connection conn);
	public boolean returnBook(int bookId, int memberId, Connection conn);
	public boolean logReturn(int bookId, int memberId, Connection conn);
	public boolean isBookIssuedToMember(int bookId, int memberId, Connection conn);
}