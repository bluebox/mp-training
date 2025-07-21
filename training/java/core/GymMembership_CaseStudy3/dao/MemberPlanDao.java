package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;

public class MemberPlanDao {
	
	 public void assignPlan(String memberId, String planId, String planName) throws SQLException {
	        String query = "insert into member_plans(member_id, plan_id,plan_name) values (?, ?, ?)";
	            Connection con=DBConnection.getConnection();
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, memberId);
	            ps.setString(2, planId);
	            ps.setString(3, planName);
	            ps.executeUpdate();
	        }

	    public List<String> getPlansForMember(String memberId) throws SQLException {
	        String query = "select plan_name from member_plans where member_id = ?";
	             Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, memberId);
	            ResultSet rs = ps.executeQuery();
	                List<String> plans = new ArrayList<>();
	                while (rs.next()) {
	                    plans.add(rs.getString("plan_name"));
	                }
	                return plans;
	            }
	 public void updatePlan(String memberId,String newPlanId,String newPlanname) throws SQLException
	 {
		 String query="update member_plans set plan_id=?,plan_name=? where member_id=?";
		 Connection con=DBConnection.getConnection();
		 PreparedStatement ps=con.prepareStatement(query);
		 ps.setString(1,newPlanId);
		 ps.setString(2,newPlanname);
		 ps.setString(3,memberId);
		 int c=ps.executeUpdate();
		 if(c>0)
			 System.out.println("Plan updated Successfully");
		 else
			 System.out.println("No plan for this member");
		 
	 }

}
