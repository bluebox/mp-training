package dao.implimentions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MemberDao;
import models.Member;
import utils.PreparedStatementManager;
import utils.SQLQueries;

public class MemberDaoImpl implements MemberDao {

	@Override
	public void addMember(Member member) {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.INSERT_PERSON);

			stmt.setString(1, member.getName());
			stmt.setInt(2, member.getAge());
			stmt.setString(3, member.getContactDetails());

			int rows = stmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Member added successfully.");
			}

		} catch (SQLException e) {
			System.out.println("Error adding member: " + e.getMessage());
		}
	}

	@Override
	public Member getMemberById(int id) {
		Member member = null;
		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.SELECT_PERSON_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getInt("age"));
				member.setContactDetails(rs.getString("contact_details"));
			}

		} catch (SQLException e) {
			System.out.println("Error adding member: " + e.getMessage());
		}
		return member;
	}

	@Override
	public List<Member> getAllMembers() {

		List<Member> members = new ArrayList<>();

		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.SELECT_ALL_PERSONS);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getInt("age"));
				member.setContactDetails(rs.getString("contact_details"));

				members.add(member);
			}

		} catch (SQLException e) {
			System.out.println("Error retrieving all members: " + e.getMessage());
		}

		return members;
	}

	@Override
	public void updateMember(Member member) {

		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.UPDATE_PERSON);

			stmt.setString(1, member.getName());
			stmt.setInt(2, member.getAge());
			stmt.setString(3, member.getContactDetails());
			stmt.setInt(4, member.getId());

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated > 0) {
				System.out.println("Member updated successfully.");
			} else {
				System.out.println("No member found with ID: " + member.getId());
			}

		} catch (SQLException e) {
			System.out.println("Error updating member: " + e.getMessage());
		}

	}

	@Override
	public void deleteMember(int id) {

		try {
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.DELETE_PERSON);

			stmt.setInt(1, id);

			int rowsDeleted = stmt.executeUpdate();

			if (rowsDeleted > 0) {
				System.out.println("Member deleted successfully.");
			} else {
				System.out.println("No member found with ID: " + id);
			}

		} catch (SQLException e) {
			System.out.println("Error deleting member: " + e.getMessage());
		}
	}
}
