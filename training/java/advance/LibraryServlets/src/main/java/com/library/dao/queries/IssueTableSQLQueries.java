package com.library.dao.queries;

public class IssueTableSQLQueries {
	protected String isBookIssued = "SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I' AND ReturnDate IS NULL";
	protected String updateReturnBook = "UPDATE issue_records SET ReturnDate = NOW(), Status = 'R' WHERE BookId = ? AND MemberId = ? AND ReturnDate IS NULL";
	protected String returnIssueLog = "INSERT INTO issue_log(BookId, MemberId, Action, ActionTime) VALUES (?, ?, 'RETURN', NOW())";
	protected String insertIntoIssueRecords = "INSERT INTO issue_records(BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', NOW())";
	protected String showAllIssuedRecords = "SELECT * FROM issue_records";

}
