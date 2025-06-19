package com.library.dao.queries;

public class MemberSQLQueries {
	protected final String insertMember="INSERT INTO member(name, email, mobile, gender, address) VALUES(?,?,?,?,?)";
	protected String isMemberExists = "SELECT * FROM member WHERE id = ?";
	protected String viewAllMembers = "SELECT * FROM member";
	protected String updateMemberQuery = "UPDATE member SET name = ?, email = ?, mobile = ?, gender = ?, address = ? WHERE id = ?";
}
