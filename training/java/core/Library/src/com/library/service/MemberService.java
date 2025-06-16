package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.Member;
import com.library.enums.Gender;

public class MemberService {
	public static Connection conn;
	public static void BookDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addMember(Member member) throws SQLException {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, String.valueOf(member.getGender().toString().charAt(0)));
            stmt.setString(5, member.getAddress());
            stmt.executeUpdate();
        }
    }
	public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Member m = new Member(rs.getInt("MemberId"),rs.getString("Name"),rs.getString("Email"),rs.getLong("Mobile"),rs.getString("Gender").charAt(0)=='M'?Gender.Male:Gender.Female,rs.getString("Address"));
                members.add(m);
            }
        }
        return members;
    }
}
