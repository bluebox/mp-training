package com.gym.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.gym.models.Member;
import com.gym.models.MembershipPlan;

public class MemberSqlDaoImpl implements MemberDao {
    private static final String URL = "jdbc:mysql://localhost:3306/gym_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Zoro*3swords";
    
    private static ArrayList<MembershipPlan> plans = new ArrayList<>();
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            initializePlans();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }
    
    private static void initializePlans() {
        String sql = "INSERT IGNORE INTO membership_plans (plan_name, duration, fee) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Initialize default plans
            Object[][] defaultPlans = {
                {"Basic", 3, 5000},
                {"Premium", 6, 8000},
                {"Gold", 12, 12000}
            };
            
            for (Object[] plan : defaultPlans) {
                pstmt.setString(1, (String)plan[0]);
                pstmt.setInt(2, (Integer)plan[1]);
                pstmt.setInt(3, (Integer)plan[2]);
                pstmt.executeUpdate();
            }
            
            loadPlans();
        } catch (SQLException e) {
            System.out.println("Error initializing plans: " + e.getMessage());
        }
    }
    
    private static void loadPlans() {
        plans.clear();
        String sql = "SELECT * FROM membership_plans";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                plans.add(new MembershipPlan(
                    rs.getString("plan_name"),
                    rs.getInt("duration"),
                    rs.getInt("fee")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading plans: " + e.getMessage());
        }
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    @Override
    public boolean saveNewMember(Member member) {
        String sql = "INSERT INTO members (member_name, member_age, member_height, member_weight, plan_id, join_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, member.getMemberName());
            pstmt.setInt(2, member.getMemberAge());
            pstmt.setInt(3, member.getMemberHeight());
            pstmt.setInt(4, member.getMemberWeight());
            pstmt.setString(5, member.getMembershipPlan() != null ? member.getMembershipPlan().planName : null);
            pstmt.setString(6, member.getJoinDate());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error saving member: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<>();
        String sql = "SELECT m.*, mp.plan_name, mp.duration, mp.fee FROM members m " +
                    "LEFT JOIN membership_plans mp ON m.plan_id = mp.plan_id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Member m = new Member(
                    rs.getInt("member_id"),
                    rs.getString("member_name"),
                    rs.getInt("member_age"),
                    rs.getInt("member_height"),
                    rs.getInt("member_weight"),
                    rs.getString("join_date")
                );
                
                String planName = rs.getString("plan_name");
                if (planName != null) {
                    m.setMemPlan(new MembershipPlan(
                        planName,
                        rs.getInt("duration"),
                        rs.getInt("fee")
                    ));
                }
                members.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
        return members;
    }
    
    @Override
    public boolean saveMembers(List<Member> members) {
        String sql = "UPDATE members SET member_name=?, member_age=?, member_height=?, member_weight=?, " +
                    "plan_id=(SELECT plan_id FROM membership_plans WHERE plan_name=?), join_date=? WHERE member_id=?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            conn.setAutoCommit(false);
            
            for (Member m : members) {
                pstmt.setString(1, m.getMemberName());
                pstmt.setInt(2, m.getMemberAge());
                pstmt.setInt(3, m.getMemberHeight());
                pstmt.setInt(4, m.getMemberWeight());
                pstmt.setString(5, m.getMembershipPlan() != null ? m.getMembershipPlan().planName : null);
                pstmt.setString(6, m.getJoinDate());
                pstmt.setInt(7, m.getMemberId());
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            conn.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating members: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateMember(Member member) {
        String sql = "UPDATE members SET plan_id=(SELECT plan_id FROM membership_plans WHERE plan_name=?), join_date=? WHERE member_id=?";
                    
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
          
            pstmt.setString(1, member.getMembershipPlan() != null ? member.getMembershipPlan().planName : null);
            pstmt.setString(2, member.getJoinDate());
            pstmt.setInt(3, member.getMemberId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
            return false;
        }
    }

	@Override
	public boolean deleteMember(int memberId) {
		String sql = "DELETE FROM members WHERE member_id = ?";
		
		try (Connection conn = getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	           	pstmt.setInt(1, memberId);
	            return pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.out.println("Error updating member: " + e.getMessage());
	            return false;
	        }
	}
    
}
