package daoImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PersonMemberShipDAO;
import models.PersonMembership;
import utils.PreparedStatementManager;
import utils.SQLQueries;

public class PersonMembershipDAOImpl implements PersonMemberShipDAO {

	@Override
	public void addMembership(PersonMembership membership) {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.INSERT_PERSON_MEMBERSHIP);

			stmt.setInt(1, membership.getPersonId());
			stmt.setInt(2, membership.getPlanId());
			stmt.setDate(3, Date.valueOf(membership.getJoiningDate()));
			stmt.setDate(4, Date.valueOf(membership.getPlanStartedDate()));
			stmt.setDate(5, Date.valueOf(membership.getPlanEndDate()));

			stmt.executeUpdate();
			System.out.println("Membership added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding membership: " + e.getMessage());
		}
	}

	@Override
	public PersonMembership getMembershipById(int id) {
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.SELECT_PERSON_MEMBERSHIP_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new PersonMembership(rs.getInt("id"), rs.getInt("person_id"), rs.getInt("plan_id"),
						rs.getDate("joining_date").toLocalDate(), rs.getDate("plan_start_date").toLocalDate(),
						rs.getDate("plan_end_date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PersonMembership> getAllMemberships() {
		List<PersonMembership> list = new ArrayList<>();
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.SELECT_ALL_PERSON_MEMBERSHIPS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PersonMembership pm = new PersonMembership(rs.getInt("id"), rs.getInt("person_id"),
						rs.getInt("plan_id"), rs.getDate("joining_date").toLocalDate(),
						rs.getDate("plan_start_date").toLocalDate(), rs.getDate("plan_end_date").toLocalDate());
				list.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<PersonMembership> getActiveMemberships() {

		List<PersonMembership> list = new ArrayList<>();
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.SELECT_ACTIVE_MEMBERSHIPS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PersonMembership pm = new PersonMembership(rs.getInt("id"), rs.getInt("person_id"),
						rs.getInt("plan_id"), rs.getDate("joining_date").toLocalDate(),
						rs.getDate("plan_start_date").toLocalDate(), rs.getDate("plan_end_date").toLocalDate());
				list.add(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteMembership(int id) {
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.DELETE_PERSON_MEMBERSHIP_BY_ID);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteExpiredMemberships() {
		try {
			PreparedStatement stmt = PreparedStatementManager
					.getPreparedStatement(SQLQueries.DELETE_EXPIRED_MEMBERSHIPS);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
