package com.casestudy.spring.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.casestudy.spring.library.beans.Gender;
import com.casestudy.spring.library.beans.Member;
import com.casestudy.spring.library.dao.models.MembersDaoModel;
import com.casestudy.spring.library.util.DBUtil;

@Repository
public class MembersDao implements MembersDaoModel{
    private static final Logger logger = LoggerFactory.getLogger(MembersDao.class);

    public void addMember(Member member) {
        if (member == null || member.getName() == null || member.getEmail() == null || member.getGender() == null) {
            throw new IllegalArgumentException("Invalid member data");
        }
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO `Member` (`name`, `email`, `mobile`, `gender`, `address`) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender().getCode());
            stmt.setString(5, member.getAddress());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding member: {}", member, e);
            throw new RuntimeException("Failed to add member", e);
        }
    }

    public boolean updateMember(Member member) {
        if (member == null || member.getMemberId() <= 0 || member.getName() == null || 
            member.getEmail() == null || member.getGender() == null) {
            throw new IllegalArgumentException("Invalid member data");
        }
        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            // 1. Fetch current member data
            String selectQuery = "SELECT `memberId`, `name`, `email`, `mobile`, `gender`, `address` FROM `Member` WHERE `memberId` = ?";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setInt(1, member.getMemberId());
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        return false;
                    }

                    // 2. Backup old data into MemberLog
                    String insertLog = "INSERT INTO `member_log` (`memberId`, `name`, `email`, `mobile`, `gender`, `address`) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement logStmt = conn.prepareStatement(insertLog)) {
                        logStmt.setInt(1, rs.getInt("memberId"));
                        logStmt.setString(2, rs.getString("name"));
                        logStmt.setString(3, rs.getString("email"));
                        logStmt.setLong(4, rs.getLong("mobile"));
                        logStmt.setString(5, rs.getString("gender"));
                        logStmt.setString(6, rs.getString("address"));
                        logStmt.executeUpdate();
                    }

                    // 3. Update Member with new data
                    String updateQuery = "UPDATE `Member` SET `name` = ?, `email` = ?, `mobile` = ?, `gender` = ?, `address` = ? WHERE `memberId` = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, member.getName());
                        updateStmt.setString(2, member.getEmail());
                        updateStmt.setLong(3, member.getMobile());
                        updateStmt.setString(4, member.getGender().getCode());
                        updateStmt.setString(5, member.getAddress());
                        updateStmt.setInt(6, member.getMemberId());
                        updateStmt.executeUpdate();
                    }

                    conn.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Error updating member: {}", member, e);
            try (Connection conn = DBUtil.getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                logger.error("Error during rollback for member: {}", member, rollbackEx);
            }
            throw new RuntimeException("Failed to update member", e);
        }
    }

    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        String selectQuery = "SELECT `memberId`, `name`, `email`, `mobile`, `gender`, `address` FROM `Member`";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectQuery);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int memberId = rs.getInt("memberId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                long mobile = rs.getLong("mobile");
                String genderCode = rs.getString("gender");
                Gender gender = genderCode != null ? Gender.fromCode(genderCode) : null;
                String address = rs.getString("address");
                Member member = new Member(memberId, name, email, mobile, gender, address);
                memberList.add(member);
            }
        } catch (SQLException e) {
            logger.error("Error fetching all members", e);
            throw new RuntimeException("Failed to fetch members", e);
        }
        return memberList;
    }

    public Member getMemberById(int id) {
        String query = "SELECT `memberId`, `name`, `email`, `mobile`, `gender`, `address` FROM `Member` WHERE `memberId` = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    long mobile = rs.getLong("mobile");
                    String genderCode = rs.getString("gender");
                    String address = rs.getString("address");
                    Gender gender = genderCode != null ? Gender.fromCode(genderCode) : null;
                    return new Member(id, name, email, mobile, gender, address);
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching member by ID: {}", id, e);
            throw new RuntimeException("Failed to fetch member", e);
        }
        return null;
    }

    public boolean findMember(int memberId) {
        String query = "SELECT 1 FROM `Member` WHERE `memberId` = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.error("Error checking member existence for ID: {}", memberId, e);
            throw new RuntimeException("Failed to check member existence", e);
        }
    }
}