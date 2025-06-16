package com.LibraryManagement.utilites;

public class DBQueries {
	public static final String insertToBooks ="insert into books (Title,Author,Category,Statuss,Availability) values(?,?,?,'A','A')";
	public static final String updateBook="update books set Availability=? where BookId=?";
	public static final String getAllBooksLog="select BookId,Title,Author,Category,Statuss,Availability from books_log";
	public static final String insertToBooksLog ="insert into books_log (BookId,Title,Author,Category,Statuss,Availability) values(?,?,?,?,?,?)";
	public static final String getBookWithId="select BookId,Title,Author,Category,Statuss,Availability from books where BookId=?";
	public static final String getBook="select Title,Author,Category from books where Title=? && Author=? && Category=?";
	public static final String getAllBooks="select BookId,Title,Author,Category,Statuss,Availability from books";

	//Member& MemberLog Table Queries

	public static final String insertToMembers="insert into members(MemberName, Email, Mobile,Gender) values(?,?,?,?)";
	public static final String getMemberWithId="select MemberId,MemberName, Email, Mobile,Gender from members where MemberId=?";
	public static final String getMember="select MemberName,Email,Mobile from members where MemberName=? && Email=? && Mobile=?";
	public static final String getAllMembers="select MemberId,MemberName,Email,Mobile,Gender from members";
	public static final String getAllMembersLog="select MemberId,MemberName, Email, Mobile,Gender from members_log";
	public static final String updateMember="update members set MemberName=?,Email=?,Mobile=? where memberId=?";
	public static final String insertToMembersLog="insert into members_log(MemberId,MemberName, Email, Mobile,Gender) values(?,?,?,?,?)";

	//IssueREcord Queries
	public static final String insertToIssueRecords="insert into issue_records (BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,'I',?,null)";
	public static final String updateIssueRecord="update issue_records set Statuss='A', ReturnDate=? where IssueId=?";
	public static final String getAllIssueRecords="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records";
	public static final String getIssueRecordWithId="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records where IssueId=?";
	public static final String insertToIssueRecordsLog="insert into issue_records_log (IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
	public static final String getAllIssueRecordsLog="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records_log";
	
	public DBQueries() {}


}