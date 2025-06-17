package com.library.dao;

import java.sql.*;
import java.util.*;
import com.library.controller.Member;
import com.library.controller.Member.Gender;
import com.library.util.AlertMsg;
import com.library.util.DBConnect;

public class MemberDao {
    private Connection getConnection() throws Exception {
        return DBConnect.getConnection();
    }

    public void addMember(Member member) {
        String s = "insert into Members (MemberName, Email, Mobile, Gender, Address) values (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(s)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.executeUpdate();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }

    public void updateMember(Member member) {
        String s = "update Members set MemberName=?, Email=?, Mobile=?, Gender=?, Address=? where MemberId=?";
        try (Connection conn = getConnection();
        		PreparedStatement ps = conn.prepareStatement(s)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setLong(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getMemberId());
            ps.executeUpdate();
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String s = "select * from Members";
        try (Connection conn = getConnection(); 
        		Statement stmt = conn.createStatement(); 
        		ResultSet rs = stmt.executeQuery(s)) {
            while (rs.next()) {
            	Gender gender=rs.getString("Gender").equalsIgnoreCase("M")?Gender.MALE:Gender.FEMALE;
                list.add(new Member(
                    rs.getInt("MemberId"),
                    rs.getString("MemberName"),
                    rs.getString("Email"),
                    rs.getLong("Mobile"),
                    gender,
                    rs.getString("Address")
                ));
            }
        } catch (Exception e) {
        	AlertMsg.showError(e.getMessage());
        }
        return list;
    }

    public Member getMemberById(int memberId) {
        String s = "select * from Members where MemberId=?";
        try (Connection conn = getConnection(); 
        		PreparedStatement ps = conn.prepareStatement(s)) {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	Gender gender=rs.getString("Gender").equalsIgnoreCase("M")?Gender.MALE:Gender.FEMALE;
                return new Member(
                    rs.getInt("MemberId"),
                    rs.getString("MemberName"),
                    rs.getString("Email"),
                    rs.getLong("Mobile"),
                    gender,
                    rs.getString("Address")
                );
            }
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
        return null;
    }
}