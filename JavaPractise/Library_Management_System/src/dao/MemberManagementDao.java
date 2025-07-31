package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Gender;
import domain.Member;
import exceptions.ManagementException;
import util.DBUtil;

public class MemberManagementDao implements MemberManagementDaoInterface {

	public boolean registerNewMember(Member m) throws Exception {
		String sql = "INSERT INTO members(Name, Email, Mobile, Gender, Address, AddedBy) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			int index = 1;
			ps.setString(index++, m.getName());
			ps.setString(index++, m.getEmail());
			ps.setString(index++, m.getMobile());
			ps.setString(index++, m.getGender().getCode());
			ps.setString(index++, m.getAddress());
			ps.setString(index++, m.getAddedBy());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						int generatedId = rs.getInt(1);
						System.out.println("Member added successfully. Member ID: " + generatedId);
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean updaterMemberDetails(Member m) throws Exception {
		String fetchSql = "SELECT MemberId, Name, Email, Mobile, Gender, Address, AddedBy, DateAdded FROM members WHERE MemberId = ?";
		String logSql = "INSERT INTO members_log (MemberId, Name, Email, Mobile, Gender, Address, AddedBy) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE MemberId = ?";

		try (Connection conn = DBUtil.getConnection()) {
			conn.setAutoCommit(false);

			try (PreparedStatement fetchPs = conn.prepareStatement(fetchSql)) {
				fetchPs.setInt(1, m.getMemberId());

				try (ResultSet rs = fetchPs.executeQuery()) {
					if (rs.next()) {
						try (PreparedStatement logPs = conn.prepareStatement(logSql)) {
							int count = 1;
							logPs.setInt(count++, rs.getInt("MemberId"));
							logPs.setString(count++, rs.getString("Name"));
							logPs.setString(count++, rs.getString("Email"));
							logPs.setString(count++, rs.getString("Mobile"));
							logPs.setString(count++, rs.getString("Gender"));
							logPs.setString(count++, rs.getString("Address"));
							logPs.setString(count++, "ADMIN");

							logPs.executeUpdate();
						}

						try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
							int count = 1;
							updatePs.setString(count++, m.getName());
							updatePs.setString(count++, m.getEmail());
							updatePs.setString(count++, m.getMobile());
							updatePs.setString(count++, m.getGender().getCode());
							updatePs.setString(count++, m.getAddress());
							updatePs.setInt(count++, m.getMemberId());

							int updated = updatePs.executeUpdate();
							if (updated > 0) {
								conn.commit();
								System.out.println(
										"Member details updated successfully for Member ID: " + m.getMemberId());
								return true;
							}
						}
					} else {
						System.out.println("No member found with ID: " + m.getMemberId());
					}
				}
			} catch (Exception e) {
				conn.rollback();
				throw e;
			}
		}
		return false;
	}

	public List<Member> viewAllMembers() throws Exception {
		List<Member> members = new ArrayList<>();
		String query = "SELECT MemberId,Name,Email,Mobile,Gender,Address,AddedBy,DateAdded  FROM members";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("memberId"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setGender(Gender.fromCode(rs.getString("gender")));
				member.setAddress(rs.getString("address"));
				member.setAddedBy(rs.getString("AddedBy"));
				member.setDateAdded(rs.getTimestamp("DateAdded").toLocalDateTime());
				members.add(member);
			}
		} catch (SQLException e) {
			throw new ManagementException("Failed to retrieve member list", e);
		}
		return members;
	}

	public boolean isEmailOrMobileDuplicate(String email, String mobile, int currentMemberId) throws Exception {
		String query = "SELECT COUNT(*) FROM members WHERE (email = ? OR mobile = ?) AND memberId <> ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, email);
			stmt.setString(2, mobile);
			stmt.setInt(3, currentMemberId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	public boolean memberExists(int memberId) throws Exception {
		String query = "SELECT 1 FROM members WHERE MemberId = ?";
		try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, memberId);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

}