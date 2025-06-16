package com.library.service;

public class SQLQuery {
	protected String insertMember="INSERT INTO member(name, email, mobile, gender, address) VALUES(?,?,?,?,?)";
	protected String selectAllBooks="SELECT * FROM book";
	protected String isBookIssued="SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I' AND ReturnDate IS NULL";
	protected String changeBookAvaliability="UPDATE book SET availability = 'A' WHERE id = ?";
	protected String updateReturnBook = "UPDATE issue_records SET ReturnDate = NOW(), Status = 'R' WHERE BookId = ? AND MemberId = ? AND ReturnDate IS NULL";
	protected String returnIssueLog = "INSERT INTO issue_log(BookId, MemberId, Action, ActionTime) VALUES (?, ?, 'RETURN', NOW())";
	
}
