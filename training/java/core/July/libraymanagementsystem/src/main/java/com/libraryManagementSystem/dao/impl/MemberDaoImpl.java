package com.libraryManagementSystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libraryManagementSystem.dao.MemberDao;
import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.utilities.MemberGender;
import com.libraryManagementSystem.utilities.PreparedStatementManager;
import com.libraryManagementSystem.utilities.SQLQueries;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int RegisterMember(Member member)
			throws DatabaseConnectionException, StatementPreparationException, DatabaseOperationException {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBER_INSERT);

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, member.getGender().getDbName());
			stmt.setString(5, member.getAddress());

			int rows = stmt.executeUpdate();

			if (rows <= 0) {
				throw new DatabaseOperationException("Member not added to server");
			}
			return rows;

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error adding member: " + e.getMessage());
		}

	}

	@Override
	public boolean getMemberByMobile(Long mobile) throws DatabaseConnectionException, StatementPreparationException {
		PreparedStatement stmt;
		try {

			stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBER_SELECT_BY_MOBILE);
			stmt.setLong(1, mobile);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return true;
			}

			return false;

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public boolean getMemberByEmail(String email) throws DatabaseConnectionException, StatementPreparationException {
		PreparedStatement stmt;
		try {

			stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBER_SELECT_BY_EMAIL);
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return true;
			}

			return false;

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public int UpdateMember(Member member, Member oldMember)
			throws DatabaseConnectionException, StatementPreparationException {

		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBER_UPDATE);

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, member.getGender().getDbName());
			stmt.setString(5, member.getAddress());
			stmt.setLong(6, member.getMemberId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated < 0) {
				throw new StatementPreparationException("Member not added to server");
			}

			memberLog(oldMember);

			return rowsUpdated;

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public List<Member> getAllMembers() throws DatabaseConnectionException, StatementPreparationException {

		List<Member> members = new ArrayList<>();
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.SELECT_ALL_MEMBERS);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberGender gender = MemberGender.fromDbName(rs.getString("gender"));

				Member member = new Member(rs.getInt("member_id"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), gender, rs.getString("address"));
				members.add(member);
			}

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}

		return members;
	}

	@Override
	public int deleteMember(Member memberData) throws DatabaseConnectionException, StatementPreparationException {

		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBER_DELETE);
			stmt.setInt(1, memberData.getMemberId());

			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted <= 0) {

				throw new StatementPreparationException("No Member found with Name: " + memberData.getName());
			}

			memberLog(memberData);

			return rowsDeleted;

		} catch (SQLException e) {
			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public void memberLog(Member member) throws DatabaseConnectionException, StatementPreparationException {
		PreparedStatement stmt;
		try {

			stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.MEMBERS_LOG_INSERT);
			stmt.setInt(1, member.getMemberId());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getEmail());
			stmt.setLong(4, member.getMobile());
			stmt.setString(5, member.getGender().getDbName());
			stmt.setString(6, member.getAddress());

			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted <= 0) {
				throw new SQLException("Failed to insert member log.");
			}

		} catch (SQLException e) {

			throw new DatabaseConnectionException("Error in Server" + e.getMessage());
		}

	}
}
