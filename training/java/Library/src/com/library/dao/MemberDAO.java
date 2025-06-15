package com.library.dao;

import com.library.util.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	public boolean updateMember(Member member) {
		String sql = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
		try (Connection conn = DB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender()));
			stmt.setString(5, member.getAddress());
			stmt.setInt(6, member.getMemberId());

			return stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean exists(Connection conn, int memberId) throws SQLException {
		String query = "SELECT 1 FROM members WHERE MemberId = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
	}


	public List<Member> fetchAllmembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        try (Connection conn = DB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
            while (rs.next()) {
				Member m = new Member(rs.getInt("memberId"),  rs.getString("name"),rs.getString("email"),
						rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address"));
                members.add(m);
            }
        }
        return members;
    }

}