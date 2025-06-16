package com.LibraryManagement.utilites;

public class DBQueries {
	public static final String INSERT_TO_BOOKS = "insert into Book (Title,Author,Category,statuss,Availability) values(?,?,?,'A','A')";
	public static final String UPDATE_BOOK = "update Book set stauss=? where BookId=?";
	public static final String GET_ALL_BOOKS_LOG = "select BookId,Title,Author,Category,statuss,Availability from BookLog";
	public static final String INSERT_TO_BOOK_LOG = "insert into BookLog (BookId,Title,Author,Category,statuss,Availability) values(?,?,?,?,?,?)";
	public static final String GET_BOOK_WITH_ID = "select BookId,Title,Author,Category,statuss,Availability from Book where BookId=?";
	public static final String GET_BOOK = "select Title,Author,Category from Book where Title=? && Author=? && Category=?";
	public static final String GET_ALL_BOOKS = "select BookId,Title,Author,Category,statuss,Availability from Book";

	// Member& MemberLog Table Queries

	public static final String INSERT_TO_MEMBER = "insert into Members(Name, Email, Mobile,Gender) values(?,?,?,?)";
	public static final String GET_MEMBER_WITH_ID = "select MemberId,Name, Email, Mobile,Gender from Members where MemberId=?";
	public static final String GET_MEMBER = "select Name,Email,Mobile from Members where Name=? && Email=? && Mobile=?";
	public static final String GET_ALL_MEMBERS = "select MemberId,Name,Email,Mobile,Gender from Members";
	public static final String GET_ALL_MEMBERS_LOG = "select MemberId,Name, Email, Mobile,Gender from MembersLog";
	public static final String UPDATE_MEMBER = "update Members set Name=?,Email=?,Mobile=? where memberId=?";
	public static final String INSERT_TO_MEMBERS_LOG = "insert into MemebersLog(MemberId,Name, Email, Mobile,Gender) values(?,?,?,?,?)";

	// IssueREcord Queries
	public static final String INSERT_TO_ISSUE_RECORD = "insert into IssueRecord (BookId,MemberId,status,IssueDate,ReturnDate) values(?,?,'I',?,null)";
	public static final String UPDATE_ISSUE_RECORD = "update IssueRecord set status='A', ReturnDate=? where IssueId=?";
	public static final String GET_ALL_ISSUE_RECORD = "select IssueId,BookId,MemberId,status,IssueDate,ReturnDate from IssueRecord";
	public static final String GET_ISSUE_RECORD_WITH_ID = "select IssueId,BookId,MemberId,status,IssueDate,ReturnDate from IssueRecord where IssueId=?";
	public static final String INSERT_TO_ISSUE_RECORD_LOG = "insert into IssueRecordLog (IssueId,BookId,MemberId,status,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
	public static final String GET_ALL_ISSUE_RECORD_LOG = "select IssueId,BookId,MemberId,status,IssueDate,ReturnDate from IssueRecordLog";

	public DBQueries() {
	}

}