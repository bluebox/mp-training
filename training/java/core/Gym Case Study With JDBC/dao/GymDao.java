package gymCaseStudyWithJdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import gymCaseStudyWithJdbc.Member;
import gymCaseStudyWithJdbc.MembershipPlan;

public class GymDao {
	Connection connection = null;
	
	public GymDao() {
		String url = "jdbc:mysql://localhost:3306/gym";
		String username = "devuser1";
		String password = "Kaushik@8946"; 
		try {
			connection = DriverManager.getConnection(url, username, password);
			createTables();
			intialisePlans();
		} catch (SQLException e) {
			throw new RuntimeException("error establishing database connection", e);
		}
	}

	public void createTables() {
		String createPlansQuery ="CREATE TABLE IF NOT EXISTS plans ("
				+ "planId INT PRIMARY KEY,"
				+ "name VARCHAR(100),"
				+ "duration INT,"
				+ "fee INT );";
		String createMemberQuery = "CREATE TABLE IF NOT EXISTS members ("
				+ "memberId INT,"
				+ "name VARCHAR(100),"
				+ "planId INT,"
				+ "age INT,"
				+ "FOREIGN KEY(planId) REFERENCES plans(planId)"
				+ ");";
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(createPlansQuery);
			stmt.execute(createMemberQuery);
		} catch (SQLException e) {
			throw new RuntimeException("error creating tables", e);
		}
	}
	
	public void intialisePlans() {
		try(Statement stmt=connection.createStatement();) {
			ResultSet rs=stmt.executeQuery("SELECT COUNT(*) FROM plans");
			if(rs.next()&&rs.getInt(1)==0) {
				String insertSQL="INSERT INTO plans (planId, name, duration, fee) VALUES "
						+ "(?, ? ,? ,?)";
				PreparedStatement pstmt=connection.prepareStatement(insertSQL);
				pstmt.setInt(1, 1);
                pstmt.setString(2, "Basic");
                pstmt.setInt(3, 3);
                pstmt.setDouble(4, 10000);
                pstmt.addBatch();

                pstmt.setInt(1, 2);
                pstmt.setString(2, "Premium");
                pstmt.setInt(3, 6);
                pstmt.setDouble(4, 15000);
                pstmt.addBatch();

                pstmt.setInt(1, 3);
                pstmt.setString(2, "Gold");
                pstmt.setInt(3, 12);
                pstmt.setDouble(4, 20000);
                pstmt.addBatch();

                pstmt.executeBatch();                
			}
		} catch (SQLException e) {
			throw new RuntimeException("error intialising values", e);
		}
	}
	
	public void addMember(Member member) throws SQLException {
		String sql="INSERT INTO members (memberId, name, age, planId) "
				+ "VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, member.getMemberId());
		pstmt.setString(2, member.getName());
		pstmt.setInt(3, member.getAge());
		if (member.getMembershipPlan() != null) {
			pstmt.setInt(4, member.getMembershipPlan().getPlanId());
		} else {
			pstmt.setNull(4, Types.INTEGER);
		}
		pstmt.execute();
	}
	public Member findMemberById(int memberId) throws SQLException {
		String sql = "SELECT m.memberId, m.name, m.age, p.planId, p.name AS planName, p.duration, p.fee " +
                "FROM members m LEFT JOIN plans p ON m.planId = p.planId " +
                "WHERE m.memberId = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return null;
		}
		Member member = new Member(rs.getInt("memberId"), rs.getString("name"), rs.getInt("age"));
		int planId = rs.getInt("planId");
		if (!rs.wasNull()) {
			MembershipPlan plan = new MembershipPlan(planId, rs.getString("planName"), rs.getInt("duration"),
					rs.getDouble("fee"));
			member.setMembershipPlan(plan);
		}
		return member;
	}

	public List<Member> getAllMember() throws SQLException {
		List<Member> members = new ArrayList<>();
		String sql = "SELECT m.memberId, m.name, m.age, p.planId, p.name AS planName, p.duration, p.fee "
				+ "FROM members m LEFT JOIN plans p ON m.planId = p.planId ORDER BY m.memberId";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Member member = new Member(rs.getInt("memberId"), rs.getString("name"), rs.getInt("age"));
			int planId = rs.getInt("planId");
			if (!rs.wasNull()) {
				MembershipPlan plan = new MembershipPlan(planId, rs.getString("planName"), rs.getInt("duration"),
						rs.getDouble("fee"));
				member.setMembershipPlan(plan);
			}
			members.add(member);
		}

		return members;
	}

	public void assignPlanToMember(int memberId, int planId) throws SQLException {
		String sql = "UPDATE members SET planId = ? WHERE memberId = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, planId);
		pstmt.setInt(2, memberId);
		pstmt.executeUpdate();
	}

	public List<MembershipPlan> getAllPlans() throws SQLException {
		List<MembershipPlan> plans = new ArrayList<>();
		String sql = "SELECT * FROM plans ORDER BY planId";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			plans.add(new MembershipPlan(rs.getInt("planId"), rs.getString("name"), rs.getInt("duration"),
					rs.getDouble("fee")));
		}

		return plans;
	}

	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}