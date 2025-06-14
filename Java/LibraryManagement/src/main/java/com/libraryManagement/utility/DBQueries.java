package com.libraryManagement.utility;

public class DBQueries {
	public static final String verifybook = "select * from Book where Title=? and Author=? and Category=?";
	public static final String addbook = "insert into Book value(?,?,?,?,?)";
	public static final String addToBookLog = "insert into BookLog value(?,?,?,?,?,?)";
	public static final String updatebookavailability = "update BookLog set Availability=? where BookId=?";
	public static final String allBooks = "select BookId,Title,Author,Category,statuss,Availability from Book";
	public static final String updatebook = "update Book set BookId=?,Title=?,Author=?,Category=?,statuss=?,Availability=? where BookId=?";
	public static final String addmember="insert into Member value(?,?,?,?)";
	public static final String addmemberlog="insert into MemberLog value(?,?,?,?,?)";
	public static final String updatemember="update Member set MemberId=?,Name=?,Email=?,Mobile=?,Gender=? where MemberId=?";
	public static final String allmember="select MemberId,Name,Email,Mobile,Gender from Member";
}
