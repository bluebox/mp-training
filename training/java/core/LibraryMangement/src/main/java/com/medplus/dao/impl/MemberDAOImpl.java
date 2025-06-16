package com.medplus.dao.impl;

import com.medplus.dao.MemberDAO;
import com.medplus.model.Member;
import com.medplus.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MemberDAOImpl implements MemberDAO {
    public void addMember(Member member) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setInt(3, member.getMobile());
        stmt.setString(4, String.valueOf(member.getGender()));
        stmt.setString(5, member.getAddress());
        stmt.executeUpdate();
    }

    public List<Member> getAllMembers() throws Exception {
        List<Member> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM members";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(new Member(
                rs.getInt("MemberId"),
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getInt("Mobile"),
                rs.getString("Gender").charAt(0),
                rs.getString("Address")
            ));
        }
        return list;
    }

    public void updateMember(Member member) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setInt(3, member.getMobile());
        stmt.setString(4, String.valueOf(member.getGender()));
        stmt.setString(5, member.getAddress());
        stmt.setInt(6, member.getMemberId());
        stmt.executeUpdate();
    }
}