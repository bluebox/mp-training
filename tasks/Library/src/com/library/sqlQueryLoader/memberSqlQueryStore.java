package com.library.sqlQueryLoader;

public class memberSqlQueryStore {
	protected static final String updateMember = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
	protected static final String getMemberById = "SELECT * FROM members WHERE MemberId=?";
	protected static final String insertIntoMemberLog = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String getAllMembers = "SELECT * FROM members";
}
