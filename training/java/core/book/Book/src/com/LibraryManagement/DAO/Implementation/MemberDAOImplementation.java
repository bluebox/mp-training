package com.LibraryManagement.DAO.Implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.LibraryManagement.DAO.MemberDAO;
import com.LibraryManagement.exception.MemberDAOException;
import com.LibraryManagement.models.Member;

import util.DatabaseConnection;

public class MemberDAOImplementation implements MemberDAO {

	@Override
	public int registerMember(Member member) {

		String insert = "INSERT INTO member(name,email,mobile,gender,address) VALUES(?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);

			try {
				PreparedStatement insertStatement = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, member.getName());
				insertStatement.setString(2, member.getEmail());
				insertStatement.setLong(3, member.getMobile());
				insertStatement.setString(4, member.getGender());
				insertStatement.setString(5, member.getAddress());

				int insertionSuccess = insertStatement.executeUpdate();
				if (insertionSuccess > 0) {
					try (ResultSet generatedId = insertStatement.getGeneratedKeys()) {
						if (generatedId.next()) {
							return generatedId.getInt(1);
						}
					}
				}
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new MemberDAOException("Failed in registering a new member", e);
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException | IOException e) {
			throw new MemberDAOException("Failed in registering a new member", e);
		}
		return -1;
	}

	@Override
	public void updateMember(Member member) {

		String updateMember = "UPDATE member SET name=?, email=?, mobile=? , gender=? , address=? WHERE memberId=?";
		String updateMember_log = "INSERT INTO member_log(memberId,name,email,mobile,gender,address,operation_type) VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement updateStatementLog = conn.prepareStatement(updateMember_log);
				updateStatementLog.setInt(1, member.getMemberId());
				updateStatementLog.setString(2, member.getName());
				updateStatementLog.setString(3, member.getEmail());
				updateStatementLog.setLong(4, member.getMobile());
				updateStatementLog.setString(5, member.getGender());
				updateStatementLog.setString(6, member.getAddress());
				updateStatementLog.setString(7, "Update");

				updateStatementLog.execute();

				PreparedStatement updateStatement = conn.prepareStatement(updateMember);
				updateStatement.setString(1, member.getName());
				updateStatement.setString(2, member.getEmail());
				updateStatement.setLong(3, member.getMobile());
				updateStatement.setString(4, member.getGender());
				updateStatement.setString(5, member.getAddress());
				updateStatement.setInt(6, member.getMemberId());

				updateStatement.execute();
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				throw new MemberDAOException("Failed to update member", e);
			} finally {
				conn.setAutoCommit(true);
			}
		} catch (SQLException | IOException e) {
			throw new MemberDAOException("Failed to update member", e);
		}
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<>();
		String querey = "SELECT * FROM member";

		try (Connection conn = DatabaseConnection.getConnection()) {
			conn.setAutoCommit(false);
			try {
				PreparedStatement getStatement = conn.prepareStatement(querey);
				ResultSet rs = getStatement.executeQuery();
				conn.commit();
				while (rs.next()) {
					int id = rs.getInt("memberId");
					String name = rs.getString("name");
					String email = rs.getString("email");
					Long mobile = rs.getLong("mobile");
					char gender = rs.getString("gender").charAt(0);
					String address = rs.getString("address");
					members.add(new Member(id, name, email, mobile, String.valueOf(gender), address));
				}
			} catch (SQLException e) {
				conn.rollback();
				throw new MemberDAOException("Failed ", e);
			}

		} catch (SQLException | IOException e) {
			throw new MemberDAOException("Failed ", e);
		}

		if (members.isEmpty()) {
			System.out.println("No books ");
		}

		return members;
	}

	@Override
	public Member getMemberById(int id) {
		String query = "SELECT * FROM member WHERE MemberId = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapResultToMember(rs);
				}
			}
		} catch (SQLException | IOException e) {
			throw new RuntimeException("Error fetching member by ID: " + e.getMessage(), e);
		}

		return null;
	}

	private Member mapResultToMember(ResultSet rs) throws SQLException {
		return new Member(rs.getInt("MemberId"), rs.getString("Name"), rs.getString("Email"), rs.getLong("Mobile"),
				rs.getString("Gender"), rs.getString("Address"));
	}
}
