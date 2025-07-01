package com.library.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.app.model.*;
import com.library.app.utilities.DBUtil;

public class MemberDAO {
    private Connection conn;

    public MemberDAO() throws SQLException {
        conn = DBUtil.getConnection();
    }

    public void addMember(Member member) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        String insertSQL = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        String logSQL = "INSERT INTO members_log (Name, Email, Mobile, Gender, Address, OperationType) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(insertSQL);
             PreparedStatement ps2 = conn.prepareStatement(logSQL)) {

            ps2.setString(1, member.getName());
            ps2.setString(2, member.getEmail());
            ps2.setLong(3, member.getMobile());
//            ps2.setString(4, member.getGender().name());
            ps2.setString(4, member.getGender().getValue());
            ps2.setString(5, member.getAddress());
            ps2.setString(6, "Insert");
            ps2.executeUpdate();

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
//            ps.setString(4, member.getGender().name());
            ps.setString(4, member.getGender().getValue()); 
            ps.setString(5, member.getAddress());
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    public void updateMember(Member member) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        String updateSQL = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        String logSQL = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address, OperationType) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(updateSQL);
             PreparedStatement ps2 = conn.prepareStatement(logSQL)) {

            ps2.setInt(1, member.getMemberId());
            ps2.setString(2, member.getName());
            ps2.setString(3, member.getEmail());
            ps2.setLong(4, member.getMobile());
//            ps2.setString(5, member.getGender().name());
            ps2.setString(5, member.getGender().getValue());
            ps2.setString(6, member.getAddress());
            ps2.setString(7, "Update");
            ps2.executeUpdate();

            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
//            ps.setString(4, member.getGender().name());
            ps.setString(4, member.getGender().getValue()); 
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getMemberId());
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Member m = new Member();
                m.setMemberId(rs.getInt("MemberId"));
                m.setName(rs.getString("Name"));
                m.setEmail(rs.getString("Email"));
                m.setMobile(rs.getLong("Mobile"));
//                m.setGender(Gender.valueOf(rs.getString("Gender")));
                m.setGender(Gender.fromValue(rs.getString("Gender"))); 
                m.setAddress(rs.getString("Address"));
                list.add(m);
            }
        }
        return list;
    }

    public Member getMemberById(int memberId) throws SQLException {
        if (conn == null || conn.isClosed()) conn = DBUtil.getConnection();
        Member m = null;
        String sql = "SELECT * FROM members WHERE MemberId=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Member();
                m.setMemberId(rs.getInt("MemberId"));
                m.setName(rs.getString("Name"));
                m.setEmail(rs.getString("Email"));
                m.setMobile(rs.getLong("Mobile"));
//                m.setGender(Gender.valueOf(rs.getString("Gender")));
                m.setGender(Gender.fromValue(rs.getString("Gender")));
                m.setAddress(rs.getString("Address"));
            }
        }
        return m;
    }
}

