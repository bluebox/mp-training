package com.casestudy.spring.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.casestudy.spring.library.beans.Gender;
import com.casestudy.spring.library.beans.Member;
import com.casestudy.spring.library.util.DBUtil;
@Repository
public class MembersDao {
	private Connection conn;
	private PreparedStatement temp;
	private ResultSet rs;

	public void addMember(Member member) {
		try {
			conn = DBUtil.getConnection();
			String adding = "INSERT INTO Member (name,email,mobile,gender,address) VALUES(?,?,?,?,?)";
			temp = conn.prepareStatement(adding);
			temp.setString(1, member.getName());
			temp.setString(2, member.getEmail());
			temp.setLong(3, member.getMobile());
			temp.setString(4, member.getGender().getCode()); // returns 'M' or 'F'
			temp.setString(5, member.getAddress());
			int num = temp.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (temp != null)
					temp.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}
	}

	public boolean updateMember(Member member) {
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 1. Fetch current member data
			String currentStatus = "SELECT memberId, name, email, mobile, gender,address FROM Member WHERE memberId = ?";
			temp = conn.prepareStatement(currentStatus);
			temp.setInt(1, member.getMemberId());
			rs = temp.executeQuery();

			if (!rs.next())
				return false;

			// 2. Backup old data into MemberLog
			String insertLog = "INSERT INTO member_log (memberId, name, email, mobile, gender,address) VALUES (?, ?, ?, ?, ?,?)";
			PreparedStatement logStmt = conn.prepareStatement(insertLog);
			logStmt.setInt(1, rs.getInt("memberId"));
			logStmt.setString(2, rs.getString("name"));
			logStmt.setString(3, rs.getString("email"));
			logStmt.setLong(4, rs.getLong("mobile"));
			logStmt.setString(5, rs.getString("gender"));
			logStmt.setString(6, rs.getString("address"));
			logStmt.executeUpdate();
			logStmt.close();

			// 3. Update Member with new data
			String updateMember = "UPDATE Member SET name = ?, email = ?, mobile = ?, gender = ?,address = ? WHERE memberId = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateMember);
			updateStmt.setString(1, member.getName());
			updateStmt.setString(2, member.getEmail());
			updateStmt.setLong(3, member.getMobile());
			updateStmt.setString(4, member.getGender().getCode());
			updateStmt.setString(5, member.getAddress());
			updateStmt.setInt(6, member.getMemberId());
			updateStmt.executeUpdate();
			updateStmt.close();

			conn.commit();
			return true;

		} catch (SQLException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (temp != null)
					temp.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException ignored) {
			}
		}
	}

	public List<Member> getAllMembers() {
		List<Member> memberList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String selectQuery = "SELECT memberId, name, email, mobile, gender,address FROM Member";
			temp = conn.prepareStatement(selectQuery);
			rs = temp.executeQuery();

			while (rs.next()) {
				int memberId = rs.getInt("memberId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile");
				Gender gender = Gender.fromCode(rs.getString("gender"));
				String address = rs.getString("address");

				Member member = new Member(memberId, name, email, mobile, gender, address); // null if no address
				memberList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (temp != null)
					temp.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}

		return memberList;
	}

	public Member getMemberById(int id) {
		Member member = null;
		String query = "SELECT memberId, name, email, mobile, gender,address FROM Member WHERE memberId = ?";
		try {
			conn = DBUtil.getConnection();
			temp = conn.prepareStatement(query);

			temp.setInt(1, id);
			try (ResultSet rs = temp.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					long mobile = rs.getLong("mobile");
					String genderCode = rs.getString("gender");
					String address = rs.getString("address");

					Gender gender = Gender.fromCode(genderCode);

					member = new Member(id, name, email, mobile, gender, address);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	public boolean findMember(int memberId) {
		String query = "SELECT 1 FROM Member WHERE memberId = ?";

		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}

