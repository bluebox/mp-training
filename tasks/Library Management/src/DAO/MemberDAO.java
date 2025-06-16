package DAO;

import casestudy.Member; import casestudy.DatabaseUtil; import casestudy.LibraryException;
import java.sql.*; import java.util.ArrayList; import java.util.List;


public class MemberDAO { 
	
	public void addMember(Member member) throws LibraryException { 
		
		String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)"; 
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
		{ pstmt.setString(1, member.getName());
		pstmt.setString(2, member.getEmail()); 
		pstmt.setLong(3, member.getMobile()); 
		pstmt.setString(4, String.valueOf(member.getGender())); 
		pstmt.setString(5, member.getAddress()); pstmt.executeUpdate();
		try (ResultSet rs = pstmt.getGeneratedKeys()) {
    if (rs.next()) {
        logMemberAction(rs.getInt(1), "ADD");
    }
}
} 
		catch (SQLException e) {
throw new LibraryException("Error adding member: " + e.getMessage());
}
}

public void updateMember(Member member) throws LibraryException {
String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
pstmt.setString(1, member.getName());
pstmt.setString(2, member.getEmail());
pstmt.setLong(3, member.getMobile());
pstmt.setString(4, String.valueOf(member.getGender()));
pstmt.setString(5, member.getAddress());
pstmt.setInt(6, member.getMemberId());
int rows = pstmt.executeUpdate();
if (rows > 0) {
    logMemberAction(member.getMemberId(), "UPDATE");
} else {
    throw new LibraryException("Member not found");
}
} catch (SQLException e) {
throw new LibraryException("Error updating member: " + e.getMessage());
}
}

public List<Member> getAllMembers() throws LibraryException {
List<Member> members = new ArrayList<>();
String sql = "SELECT * FROM members";
try (Connection conn = DatabaseUtil.getConnection();
 Statement stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery(sql)) {
while (rs.next()) {
    Member member = new Member();
    member.setMemberId(rs.getInt("MemberId"));
    member.setName(rs.getString("Name"));
    member.setEmail(rs.getString("Email"));
    member.setMobile(rs.getLong("Mobile"));
    member.setGender(rs.getString("Gender").charAt(0));
    member.setAddress(rs.getString("Address"));
    members.add(member);
}
} catch (SQLException e) {
throw new LibraryException("Error retrieving members: " + e.getMessage());
}
return members;
}

private void logMemberAction(int memberId, String action) throws SQLException {
String logSql = "INSERT INTO members_log (MemberId, Action, ActionDate) VALUES (?, ?, NOW())";
try (Connection conn = DatabaseUtil.getConnection();
 PreparedStatement pstmt = conn.prepareStatement(logSql)) {
pstmt.setInt(1, memberId);
pstmt.setString(2, action);
pstmt.executeUpdate();
}
}}