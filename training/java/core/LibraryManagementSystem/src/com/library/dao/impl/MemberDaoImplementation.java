package com.library.dao.impl;

import com.library.dao.interfaces.MemberDao;
import com.library.model.Member;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImplementation implements MemberDao {

    @Override
    public boolean addMember(Member member) {
        String query = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getAddress());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error in addMember: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateMember(Member member) {
        String updateQuery = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        String logQuery = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address, LogAction, LogTimeStamp) VALUES (?, ?, ?, ?, ?, ?, 'UPDATE', NOW())";

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // log before update
            Member existing = getMemberById(member.getMemberId());
            if (existing == null) {
                throw new SQLException("Member not found for update.");
            }

            try (PreparedStatement logStmt = conn.prepareStatement(logQuery)) {
                logStmt.setInt(1, existing.getMemberId());
                logStmt.setString(2, existing.getName());
                logStmt.setString(3, existing.getEmail());
                logStmt.setLong(4, existing.getMobile());
                logStmt.setString(5, existing.getGender());
                logStmt.setString(6, existing.getAddress());
                logStmt.executeUpdate();
            }

            // actual update
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, member.getName());
                stmt.setString(2, member.getEmail());
                stmt.setLong(3, member.getMobile());
                stmt.setString(4, member.getGender());
                stmt.setString(5, member.getAddress());
                stmt.setInt(6, member.getMemberId());
                int updated = stmt.executeUpdate();

                conn.commit();
                return updated == 1;
            }

        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            throw new RuntimeException("Failed to update member: " + e.getMessage(), e);
        } finally {
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM members";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                members.add(mapResultToMember(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching members: " + e.getMessage(), e);
        }

        return members;
    }

    @Override
    public Member getMemberById(int id) {
        String query = "SELECT * FROM members WHERE MemberId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultToMember(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching member by ID: " + e.getMessage(), e);
        }

        return null;
    }

    private Member mapResultToMember(ResultSet rs) throws SQLException {
        return new Member(
                rs.getInt("MemberId"),
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getLong("Mobile"),
                rs.getString("Gender"),
                rs.getString("Address")
        );
    }
}
