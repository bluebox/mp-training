package com.webLibraryManagementSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webLibraryManagementSystem.dao.MemberDao;
import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.MemberGender;
import com.webLibraryManagementSystem.utilities.SQLQueries;
import com.webLibraryManagementSystemexceptions.InvalidException;

public class MemberDaoImpl implements MemberDao {

	@Override
	public int RegisterMember(Member member) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.MEMBER_INSERT);) {

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender().toString().charAt(0)));
			stmt.setString(5, member.getAddress());

			int rows = stmt.executeUpdate();

			if (rows <= 0) {
				throw new InvalidException("Member not added to server");
			}
			return rows;

		} catch (SQLException e) {
			throw new InvalidException("Error adding member: " + e.getMessage());
		}

	}

	@Override
	public boolean getMemberByMobile(Long mobile) throws InvalidException {
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.MEMBER_SELECT_BY_MOBILE);) {

			stmt.setLong(1, mobile);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return true;
			}

			return false;

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public boolean getMemberByEmail(String email) throws InvalidException {

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.MEMBER_SELECT_BY_EMAIL);) {

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				return true;
			}

			return false;

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}
	}

	@Override
	public int UpdateMember(Member member, Member oldMember) throws InvalidException {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.MEMBER_UPDATE);
			stmt1 = con.prepareStatement(SQLQueries.MEMBERS_LOG_INSERT);

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setLong(3, member.getMobile());
			stmt.setString(4, String.valueOf(member.getGender().toString().charAt(0)));
			stmt.setString(5, member.getAddress());
			stmt.setLong(6, member.getMemberId());

			con.setAutoCommit(false);
			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated < 0) {
				throw new InvalidException("Member not added to server");
			}

			memberLog(oldMember, con, stmt1);

			con.commit();
			con.setAutoCommit(true);

			return rowsUpdated;

		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new InvalidException("Error in Server" + e.getMessage());
			}
			throw new InvalidException("Error in Server" + e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				stmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Member> getAllMembers() throws InvalidException {

		List<Member> members = new ArrayList<>();

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.SELECT_ALL_MEMBERS);) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberGender gender = rs.getString("gender").equalsIgnoreCase("F") ? MemberGender.FEMALE
						: MemberGender.MALE;
				Member member = new Member(rs.getInt("member_id"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), gender, rs.getString("address"));
				members.add(member);
			}

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}

		return members;
	}

	@Override
	public int deleteMember(Member memberData) throws InvalidException {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.MEMBER_DELETE);
			stmt1 = con.prepareStatement(SQLQueries.MEMBERS_LOG_INSERT);

			stmt.setInt(1, memberData.getMemberId());
			con.setAutoCommit(false);
			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted <= 0) {

				throw new InvalidException("No Member found with Name: " + memberData.getName());
			}

			memberLog(memberData, con, stmt1);

			con.commit();
			con.setAutoCommit(true);

			return rowsDeleted;

		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new InvalidException("Error in Server" + e.getMessage());
			}
			throw new InvalidException("Error in Server" + e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				stmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void memberLog(Member member, Connection con, PreparedStatement stmt) throws InvalidException {

		try {
			stmt.setInt(1, member.getMemberId());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getEmail());
			stmt.setLong(4, member.getMobile());
			stmt.setString(5, String.valueOf(member.getGender().toString().charAt(0)));
			stmt.setString(6, member.getAddress());

			int rowsInserted = stmt.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("log added ");
			}

		} catch (SQLException e) {

			throw new InvalidException("Error in Server" + e.getMessage());
		}

	}

	@Override
	public Member getMemberId(int memberId) throws InvalidException {
		Member member = null;

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(SQLQueries.MEMBER_SELECT_BY_ID);) {

			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberGender gender = rs.getString("gender").equalsIgnoreCase("F") ? MemberGender.FEMALE
						: MemberGender.MALE;
				member = new Member(rs.getInt("member_id"), rs.getString("name"), rs.getString("email"),
						rs.getLong("mobile"), gender, rs.getString("address"));

			}

		} catch (SQLException e) {
			throw new InvalidException("Error in Server" + e.getMessage());
		}

		return member;
	}
}
