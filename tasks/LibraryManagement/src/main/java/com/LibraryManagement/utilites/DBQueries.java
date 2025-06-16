package com.LibraryManagement.utilites;

public class DBQueries {
	
	public DBQueries() {}
	
	public static final String INSERT_TO_BOOKS ="insert into books (title,author,category,status,availability) values(?,?,?,'A','A')";
	public static final  String UPDATE_BOOK="update books set status=? where bookid=?";
	public static final String GET_ALL_BOOKS_LOG ="select bookid,title,author,category,status,availability from books_log";
	public static final String INSERT_TO_BOOKS_LOG="insert into books_log (bookid,title,author,category,status,availability) values(?,?,?,?,?,?)";
	public static final String GET_BOOK_WITH_ID ="select bookid,title,author,category,status,availability from books where bookid=?";
	public static final String GET_BOOK ="select title,author, category from books where title=? && author=? && category=?";
	public static final String GET_ALL_BOOKS ="select bookid,title,author,category,status,availability from books";

	//Member& MemberLog Table Queries

	public static final String INSERT_TO_MEMBERS ="insert into members(name, email, mobile,gender) values(?,?,?,?)";
	public static final String GET_MEMBER_WITH_ID ="select memberid,name, email, mobile,gender from members where memberid=?";
	public static final String GET_MEMBER ="select name,email,mobile from members where name=? && email=? && mobile=?";
	public static final String GET_ALL_MEMBERS="select memberid,name,email,mobile,gender from members";
	public static final String GET_ALL_MEMBERS_LOG="select memberid,name, email, mobile,gender from members_log";
	public static final String UPDATE_MEMBER ="update members set name=?,email=?,mobile=? where memberid=?";
	public static final String INSERT_TO_MEMBERS_LOG="insert into members_log(memberid,name, email, mobile,gender) values(?,?,?,?,?)";

	//IssueREcord Queries
	public static final String INSERT_TO_ISSUE_RECORDS="insert into issue_records (bookid,memberid,status,issuedate,returndate) values(?,?,'I',?,null)";
	public static final String UPDATE_ISSUE_RECORD="update issue_records set status='R', returndate=? where issueid=?";
	public static final String GET_ALL_ISSUE_RECORDS="select issueid,bookid,memberid,status,issuedate,returndate from issue_records";
	public static final String GET_ISSUE_RECORD_WITH_ID="select issueid,bookid,memberid,status,issuedate,returndate from issue_records where issueid=?";
	public static final String INSERT_TO_ISSUE_RECORDS_LOG="insert into issue_records_log (issueid,bookid,memberid,status,issuedate,returndate) values(?,?,?,?,?,?)";
	public static final String GET_ALL_ISSUE_RECORDS_LOG="select issueid,bookid,memberid,status,issuedate,returndate from issue_records_log";
	
	


}