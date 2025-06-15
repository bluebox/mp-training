package com.LibraryManagement.utilites;

public class DBQueries {
	public static String insertToBooks ="insert into books (Title,Author,Category,Statuss,Availability) values(?,?,?,'A','A')";
	public static String updateBook="update books set Availability=? where BookId=?";
	public static String getAllBooksLog="select BookId,Title,Author,Category,Statuss,Availability from books_log";
	public static String insertToBooksLog ="insert into books_log (BookId,Title,Author,Category,Statuss,Availability) values(?,?,?,?,?,?)";
	public static String getBookWithId="select BookId,Title,Author,Category,Statuss,Availability from books where BookId=?";
	public static String getBook="select Title,Author,Category from books where Title=? && Author=? && Category=?";
	public static String getAllBooks="select BookId,Title,Author,Category,Statuss,Availability from books";

	//Member& MemberLog Table Queries

	public static String insertToMembers="insert into members(MemberName, Email, Mobile,Gender) values(?,?,?,?)";
	public static String getMemberWithId="select MemberId,MemberName, Email, Mobile,Gender from members where MemberId=?";
	public static String getMember="select MemberName,Email,Mobile from members where MemberName=? && Email=? && Mobile=?";
	public static String getAllMembers="select MemberId,MemberName,Email,Mobile,Gender from members";
	public static String getAllMembersLog="select MemberId,MemberName, Email, Mobile,Gender from members_log";
	public static String updateMember="update members set MemberName=?,Email=?,Mobile=? where memberId=?";
	public static String insertToMembersLog="insert into members_log(MemberId,MemberName, Email, Mobile,Gender) values(?,?,?,?,?)";

	//IssueREcord Queries
	public static String insertToIssueRecords="insert into issue_records (BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,'I',?,null)";
	public static String updateIssueRecord="update issue_records set Statuss='A', ReturnDate=? where IssueId=?";
	public static String getAllIssueRecords="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records";
	public static String getIssueRecordWithId="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records where IssueId=?";
	public static String insertToIssueRecordsLog="insert into issue_records_log (IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
	public static String getAllIssueRecordsLog="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records_log";
	
	public DBQueries() {}


}