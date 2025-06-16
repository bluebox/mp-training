package com.LibraryManagement.utilites;

public class DBQueries {
	public static final String insertToBooks ="insert into Book (Title,Author,Category,statuss,Availability) values(?,?,?,'A','A')";
	public static final String updateBook="update Book set Availability=? where BookId=?";
	public static final String getAllBooksLog="select BookId,Title,Author,Category,Statuss,Availability from BookLog";
	public static final String insertToBooksLog ="insert into BookLog (BookId,Title,Author,Category,statuss,Availability) values(?,?,?,?,?,?)";
	public static final String getBookWithId="select BookId,Title,Author,Category,Statuss,Availability from Book where BookId=?";
	public static final String getBook="select Title,Author,Category from Book where Title=? && Author=? && Category=?";
	public static final String getAllBooks="select BookId,Title,Author,Category,Statuss,Availability from Book";

	//Member& MemberLog Table Queries

	public static final String insertToMembers="insert into Members(Name, Email, Mobile,Gender) values(?,?,?,?)";
	public static final String getMemberWithId="select MemberId,Name, Email, Mobile,Gender from Members where MemberId=?";
	public static final String getMember="select Name,Email,Mobile from Members where Name=? && Email=? && Mobile=?";
	public static final String getAllMembers="select MemberId,Name,Email,Mobile,Gender from Members";
	public static final String getAllMembersLog="select MemberId,Name, Email, Mobile,Gender from MembersLog";
	public static final String updateMember="update Members set Name=?,Email=?,Mobile=? where memberId=?";
	public static final String insertToMembersLog="insert into MemebersLog(MemberId,Name, Email, Mobile,Gender) values(?,?,?,?,?)";

	//IssueREcord Queries
	public static final String insertToIssueRecords="insert into IssueRecord (BookId,MemberId,status,IssueDate,ReturnDate) values(?,?,'I',?,null)";
	public static final String updateIssueRecord="update IssueRecord set Statuss='A', ReturnDate=? where IssueId=?";
	public static final String getAllIssueRecords="select IssueId,BookId,MemberId,status,IssueDate,ReturnDate from IssueRecord";
	public static final String getIssueRecordWithId="select IssueId,BookId,MemberId,status,IssueDate,ReturnDate from IssueRecord where IssueId=?";
	public static final String insertToIssueRecordsLog="insert into IssueRecordLog (IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
	public static final String getAllIssueRecordsLog="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from IssueRecordLog";
	
	public DBQueries() {}


}