package Dao;

import java.sql.*;
import Utilities.DBUtil;
import Pojo.*;
import java.util.*;

public class MemberDAO {
	private Connection conn;

	public MemberDAO() throws SQLException {
		conn = DBUtil.getConnection();
	}

	public void addMember(Member member) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)")) {
			PreparedStatement ps2 = conn.prepareStatement(
					"INSERT INTO members_log (Name, Email, Mobile, Gender, Address, OperationType) VALUES (?, ?, ?, ?, ?, ?)");
			ps2.setString(1, member.getName());
			ps2.setString(2, member.getEmail());
			ps2.setLong(3, member.getMobile());
			ps2.setString(4, String.valueOf(member.getGender()));
			ps2.setString(5, member.getAddress());
			ps2.setString(6, "Insert");
			ps2.executeUpdate();
            ps2.close();
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public void updateMember(Member member) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn.prepareStatement(
				"UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?")) {
			PreparedStatement ps2 = conn.prepareStatement(
			        "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address, OperationType) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps2.setInt(1, member.getMemberId());
		    ps2.setString(2, member.getName());
		    ps2.setString(3, member.getEmail());
		    ps2.setLong(4, member.getMobile());
		    ps2.setString(5, String.valueOf(member.getGender()));
		    ps2.setString(6, member.getAddress());
		    ps2.setString(7, "Update");
		    ps2.executeUpdate();
            ps2.close();
			ps.setString(1, member.getName());
			ps.setString(2, member.getEmail());
			ps.setLong(3, member.getMobile());
			ps.setString(4, String.valueOf(member.getGender()));
			ps.setString(5, member.getAddress());
			ps.setInt(6, member.getMemberId());
			ps.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public List<Member> getAllMembers() throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		List<Member> list = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM members")) {
			while (rs.next()) {
				Member m = new Member();
				m.setMemberId(rs.getInt("MemberId"));
				m.setName(rs.getString("Name"));
				m.setEmail(rs.getString("Email"));
				m.setMobile(rs.getLong("Mobile"));
				m.setGender(rs.getString("Gender").charAt(0));
				m.setAddress(rs.getString("Address"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Member getMemberById(int memberId) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		Member member = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM members WHERE MemberId = ?")) {
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getInt("MemberId"));
				member.setName(rs.getString("Name"));
				member.setEmail(rs.getString("Email"));
				member.setMobile(rs.getLong("Mobile"));
				member.setGender(rs.getString("Gender").charAt(0));
				member.setAddress(rs.getString("Address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

}
