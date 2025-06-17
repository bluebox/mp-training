package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.domain.Member;
import com.library.queries.MemberSQLQueries;
import com.library.utilities.ConnectionMaker;

public class MemberDAO extends MemberSQLQueries {


	public boolean addMember(Member member,Connection conn) {
		int check = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(insertMember);
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			check = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check == 1;
	}



	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		String query = "SELECT * FROM member";

		try (Connection conn = ConnectionMaker.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Member member = new Member(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), rs.getString("gender").charAt(0), rs.getString("address"));
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return members;
	}
}
