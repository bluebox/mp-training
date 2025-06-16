package com.library.sqlQueryLoader;

public class sqlQueryStore {
	protected static final String insertMember = ;
	protected static final String updateMember = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
	protected static final String getMemberById = "SELECT * FROM members WHERE MemberId=?";
	protected static final String insertIntoMemberLog = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String getAllMembers = "SELECT * FROM members";

	protected static final String getAllBooks = "SELECT * FROM books";
	protected static final String insertIntoBook =  "INSERT INTO books (Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?)";
	protected static final String getBookAvailability ="SELECT Availability FROM books WHERE BookId = ?";
	protected static final String getBookById = "SELECT * FROM books WHERE BookId = ?";
	protected static final String insertIntoBookLog = "INSERT INTO books_log (book_id, title, author, category, status, availablity) VALUES (?, ?, ?, ?, ?, ?)";
	
	
	protected static final String insertIntoIssueRecords= "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
	protected static final String getBookByIdAndStatusI=  "SELECT * FROM issue_records WHERE BookId = ? AND Status = 'I' ORDER BY IssueId DESC LIMIT 1";
	protected static final String insertIntoIssueRecordsLog= "INSERT INTO issue_records_log (issue_id, bookid, memberid, status, issuedate, returndate) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String updateIssueRecordStatusToR= "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
	protected static final String getIssueRecordByBookId= "SELECT * from issue_records where BookId=?";

	

}
