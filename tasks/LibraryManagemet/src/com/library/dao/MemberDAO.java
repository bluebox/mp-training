package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.library.domain.Member;
import com.library.utilities.ConnectionMaker;

public class MemberDAO {

	public void addMember(Member member) {
		try (Connection conn = ConnectionMaker.getConnection();)
			{
			PreparedStatement ps = conn.prepareStatement("INSERT INTO member(name,email,mobile,gender,address) VALUES(?,?,?,?,?)");
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			System.out.println(ps.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
