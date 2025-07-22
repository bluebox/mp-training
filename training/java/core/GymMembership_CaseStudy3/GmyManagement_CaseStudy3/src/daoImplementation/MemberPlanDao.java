package daoImplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.MemberPlanDaoInterface;
import dbConnection.DBConnection;
import model.MembershipPlan;

public class MemberPlanDao implements MemberPlanDaoInterface {
	
	 public void assignPlan(String memberId, String planId, String planName) throws SQLException {
	        String query = "insert into member_plans(member_id, plan_id,plan_name,Date_of_planAssigned) values (?, ?, ?,?)";
	            Connection con=DBConnection.getConnection();
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, memberId);
	            ps.setString(2, planId);
	            ps.setString(3, planName);
	            ps.setDate(4,new java.sql.Date(System.currentTimeMillis()));
	            ps.executeUpdate();
	        }

	    public List<MembershipPlan> getPlansForMember(String memberId) throws SQLException {
	        String query = "select plan_name,Date_of_planAssigned from member_plans where member_id = ?";
	             Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, memberId);
	            ResultSet rs = ps.executeQuery();
	                List<MembershipPlan> plans = new ArrayList<>();
	                while (rs.next()) {
	                	MembershipPlan plan=new MembershipPlan();
	                	plan.setPlanName(rs.getString("plan_name"));
	                	plan.setDateAssigend(rs.getDate("Date_of_planAssigned"));
	                	plans.add(plan);
	                
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
