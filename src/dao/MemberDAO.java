package dao;

import model.Member;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender()));
            stmt.setString(5, member.getAddress());
            stmt.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Member m = new Member();
                m.setMemberId(rs.getInt("MemberId"));
                m.setName(rs.getString("Name"));
                m.setEmail(rs.getString("Email"));
                m.setMobile(rs.getLong("Mobile"));
                m.setGender(rs.getString("Gender").charAt(0));
                m.setAddress(rs.getString("Address"));
                members.add(m);
            }
        }
        return members;
    }
}
