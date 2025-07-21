package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnection.DBConnection;

import model.MembershipPlan;

public class PlanDao {

	public List<MembershipPlan> getAllPlans() throws SQLException
	{
		List<MembershipPlan> plans=new ArrayList<>();
		String query="select * from plans";
		Connection con=DBConnection.getConnection();
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			plans.add(new MembershipPlan(rs.getString("plan_id"),rs.getString("plan_name"),rs.getInt("duration_in_months"),rs.getDouble("fee")));
		}
		return plans;
	}
	
}
