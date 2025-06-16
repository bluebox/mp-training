package dao;

import model.Member;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import enums.Gender;

public class MemberDAO {

    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (memberId,Name, Email, Mobile, Gender, Address) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, member.getMemberId());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getEmail());
            stmt.setLong(4, member.getMobile());
            stmt.setString(5, String.valueOf(member.getGender().toString().charAt(0)));
            stmt.setString(6, member.getAddress());
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
                m.setGender(rs.getString("Gender").charAt(0)=='M'?Gender.Male:Gender.Female);
                m.setAddress(rs.getString("Address"));
                members.add(m);
            }
        }
        return members;
    }
}
