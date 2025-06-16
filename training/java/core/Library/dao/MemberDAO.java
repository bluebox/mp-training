package dao;

import model.Member;
import utils.DBConnection;
import enums.Gender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public boolean insertMemberWithTransaction(Member member) throws Exception {
        Connection con = null;
        PreparedStatement ps1 = null, ps2 = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            ps1 = con.prepareStatement(
                "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, member.getName());
            ps1.setString(2, member.getEmail());
            ps1.setLong(3, member.getMobile());
            ps1.setString(4, member.getGender().toString());
            ps1.setString(5, member.getAddress());

            ps1.executeUpdate();

            rs = ps1.getGeneratedKeys();
            int memberId = 0;
            if (rs.next()) {
                memberId = rs.getInt(1);
            }

            ps2 = con.prepareStatement(
                "INSERT INTO user_log (user_id, action, performed_by) VALUES (?, ?, ?)");
            ps2.setInt(1, memberId);
            ps2.setString(2, "INSERT");
            ps2.setString(3, "admin");
            ps2.executeUpdate();

            con.commit();
            return true;

        } catch (Exception e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (ps1 != null) ps1.close();
            if (ps2 != null) ps2.close();
            if (con != null) con.setAutoCommit(true);
            if (con != null) con.close();
        }
    }

    public boolean updateMemberDetails(Member member) throws Exception {
        // Debug: Check if the Member object has the correct memberId
        if (member.getMemberId() == 0) {
            throw new IllegalArgumentException("MemberId is missing or zero. Cannot update member details.");
        }
        String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
            ps.setString(4, member.getGender().toString());
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getMemberId());

            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                insertLog(member.getMemberId(), "Updated Member Details", "Admin");
            }

            return rows > 0;
        }
    }

    public boolean deleteMember(int memberId) throws Exception {
        String sql = "DELETE FROM members WHERE MemberId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, memberId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                insertLog(memberId, "Deleted Member", "Admin");
            }

            return rows > 0;
        }
    }

    public List<Member> getAllMembers() throws Exception {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("MemberId"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getLong("Mobile"),
                    Gender.valueOf(rs.getString("Gender")),
                    rs.getString("Address")
                );
                members.add(member);
            }
        }
        return members;
    }

    private void insertLog(int memberId, String action, String performedBy) throws Exception {
        String sql = "INSERT INTO user_log (user_id, action, performed_by) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ps.setString(2, action);
            ps.setString(3, performedBy);
            ps.executeUpdate();
        }
    }
}
