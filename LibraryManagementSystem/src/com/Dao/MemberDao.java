package com.Dao;

import com.Models.Member;
import com.utilities.DbUtil;

import java.sql.*;
import java.util.*;

public class MemberDao {
    public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
            	if(rs.next()) {
            		member.setMemberId(rs.getInt(1));
            	}
            }
        }
        String sql2 = "INSERT INTO memberlogs (memberid,Name, Email, Mobile, Gender, Address) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql2)) {
        	ps.setInt(1, member.getMemberId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getMobile());
            ps.setString(5, String.valueOf(member.getGender()));
            ps.setString(6, member.getAddress());
            ps.executeUpdate();
        }
    }

    public void updateMember(Member member) throws SQLException {
        String sql = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getMemberId());
            ps.executeUpdate();
            try(ResultSet rs=ps.getGeneratedKeys()){
            	if(rs.next()) {
            		member.setMemberId(rs.getInt(1));
            	}
            }
        }
        String sql2 = "INSERT INTO memberlogs (memberid,Name, Email, Mobile, Gender, Address) VALUES (?,?, ?, ?, ?, ?)";
        try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql2)) {
        	ps.setInt(1, member.getMemberId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getMobile());
            ps.setString(5, String.valueOf(member.getGender()));
            ps.setString(6, member.getAddress());
            ps.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection conn = DbUtil.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Member m = new Member();
                m.setMemberId(rs.getInt("MemberId"));
                m.setName(rs.getString("Name"));
                m.setEmail(rs.getString("Email"));
                m.setMobile(rs.getString("Mobile"));
                m.setGender(rs.getString("Gender").charAt(0));
                m.setAddress(rs.getString("Address"));
                list.add(m);
            }
        }
        return list;
    }
}
