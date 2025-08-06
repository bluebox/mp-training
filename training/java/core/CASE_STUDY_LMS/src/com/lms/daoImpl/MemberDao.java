package com.lms.daoImpl;

import com.lms.model.Member;
import com.lms.util.DBUtil;
import com.lms.exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Add this import at the top

public class MemberDao {

    public static boolean addMember(Member member) throws DAOException {
        String sql = "INSERT INTO members (name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getMobile());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getAddress());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Failed to add member", e);
        }
    }

    public static boolean updateMember(Member member) throws DAOException {
        String sql = "UPDATE members SET name=?, email=?, mobile=?, gender=?, address=? WHERE member_id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setString(3, member.getMobile());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, member.getMemberId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DAOException("Failed to update member", e);
        }
    }

    public static Member getMemberById(int id) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE member_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("gender"),
                        rs.getString("address")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to fetch member by ID", e);
        }

        return null;
    }

    public static List<Member> getAllMembers() throws DAOException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Member member = new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("gender"),
                        rs.getString("address")
                );
                members.add(member);
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to load all members", e);
        }

        return members;
    }

    public static int generateNewMemberId() throws DAOException {
        String sql = "SELECT MAX(member_id) AS max_id FROM members";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to generate new member ID", e);
        }

        return 1;
    }

    public static Member getMemberByMobile(String mobile) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE mobile = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("gender"),
                        rs.getString("address")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to fetch member by mobile", e);
        }

        return null;
    }

    public static Member getMemberByEmail(String email) throws DAOException {
        String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE email = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("gender"),
                        rs.getString("address")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to fetch member by email", e);
        }

        return null;
    }
}