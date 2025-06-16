package com.LibraryManagement.utilites;

public class DBQueries {
	
	public DBQueries() {}
	
	public static final String INSERT_TO_BOOKS ="insert into books (Title,Author,Category,Statuss,Availability) values(?,?,?,'A','A')";
	public static final  String UPDATE_BOOK="update books set Statuss=? where BookId=?";
	public static final String GET_ALL_BOOKS_LOG ="select BookId,Title,Author,Category,Statuss,Availability from books_log";
	public static final String INSERT_TO_BOOKS_LOG="insert into books_log (BookId,Title,Author,Category,Statuss,Availability) values(?,?,?,?,?,?)";
	public static final String GET_BOOK_WITH_ID ="select BookId,Title,Author,Category,Statuss,Availability from books where BookId=?";
	public static final String GET_BOOK ="select Title,Author,Category from books where Title=? && Author=? && Category=?";
	public static final String GET_ALL_BOOKS ="select BookId,Title,Author,Category,Statuss,Availability from books";

	//Member& MemberLog Table Queries

	public static final String INSERT_TO_MEMBERS ="insert into members(MemberName, Email, Mobile,Gender) values(?,?,?,?)";
	public static final String GET_MEMBER_WITH_ID ="select MemberId,MemberName, Email, Mobile,Gender from members where MemberId=?";
	public static final String GET_MEMBER ="select MemberName,Email,Mobile from members where MemberName=? && Email=? && Mobile=?";
	public static final String GET_ALL_MEMBERS="select MemberId,MemberName,Email,Mobile,Gender from members";
	public static final String GET_ALL_MEMBERS_LOG="select MemberId,MemberName, Email, Mobile,Gender from members_log";
	public static final String UPDATE_MEMBER ="update members set MemberName=?,Email=?,Mobile=? where memberId=?";
	public static final String INSERT_TO_MEMBERS_LOG="insert into members_log(MemberId,MemberName, Email, Mobile,Gender) values(?,?,?,?,?)";

	//IssueREcord Queries
	public static final String INSERT_TO_ISSUE_RECORDS="insert into issue_records (BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,'I',?,null)";
	public static final String UPDATE_ISSUE_RECORD="update issue_records set Statuss='A', ReturnDate=? where IssueId=?";
	public static final String GET_ALL_ISSUE_RECORDS="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records";
	public static final String GET_ISSUE_RECORD_WITH_ID="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records where IssueId=?";
	public static final String INSERT_TO_ISSUE_RECORDS_LOG="insert into issue_records_log (IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate) values(?,?,?,?,?,?)";
	public static final String GET_ALL_ISSUE_RECORDS_LOG="select IssueId,BookId,MemberId,Statuss,IssueDate,ReturnDate from issue_records_log";
	
	


}