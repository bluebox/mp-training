package com.LibraryManagement.dao;

import com.LibraryManagement.model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {

    private final Connection connection;
    public MemberDAOImpl(Connection connection) {
        this.connection = connection;
    }
	@Override
	public void addMember(Member member) throws SQLException {
		// TODO Auto-generated method stub
	   String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
       try (PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setString(1, member.getName());
           ps.setString(2, member.getEmail());
           ps.setString(3, member.getMobile());
           ps.setString(4, String.valueOf(member.getGender()));
           ps.setString(5, member.getAddress());
           ps.executeUpdate();
       }
	}
	public void updateMember(Member member) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getMobile());
            ps.setString(4, String.valueOf(member.getGender()));
            ps.setString(5, member.getAddress());
            ps.setInt(6, member.getMemberId());
            ps.executeUpdate();
        }
		
	}
	@Override
	public Member getMemberById(int memberId) throws SQLException {
	   String sql = "SELECT * FROM members WHERE MemberId = ?";
       try (PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setInt(1, memberId);
           try (ResultSet rs = ps.executeQuery()) {
               if (rs.next()) {
                   Member member = new Member();
                   member.setMemberId(rs.getInt("MemberId"));
                   member.setName(rs.getString("Name"));
                   member.setEmail(rs.getString("Email"));
                   member.setMobile(rs.getString("Mobile"));
                   member.setGender(rs.getString("Gender").charAt(0));
                   member.setAddress(rs.getString("Address"));
                   return member;
               }
           }
       }
       return null; // Member not found
	}
	@Override
	public List<Member> getAllMembers() throws SQLException {
		List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("MemberId"));
                member.setName(rs.getString("Name"));
                member.setEmail(rs.getString("Email"));
                member.setMobile(rs.getString("Mobile"));
                member.setGender(rs.getString("Gender").charAt(0));
                member.setAddress(rs.getString("Address"));
                members.add(member);
            }
        }
        return members;
	}
}
