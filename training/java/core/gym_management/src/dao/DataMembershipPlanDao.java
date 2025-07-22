package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.MembershipPlan;

public class DataMembershipPlanDao implements MembershipPlanDao {
	private static final String url="jdbc:mysql://localhost:3306/gym";
	private static final String user="root";
	private static final String password="kavi@2";
	
    public DataMembershipPlanDao() {
    	List<MembershipPlan> plan=getAllPlans();
    	if(plan.size()==0) {
    		 addPlan(new MembershipPlan("Basic", 30, 999));
    	     addPlan(new MembershipPlan("Gold", 90, 2499));
    	     addPlan(new MembershipPlan("Premium", 180, 4999));
    	}
    }

    @Override
    public MembershipPlan addPlan(MembershipPlan plan) {
    	String query="INSERT INTO membership_plans(plan_Name, duration_in_days, fee) VALUES (?,?,?)";

//        String query="INSERT INTO membership_plans(plan_Name,duration_In_Days,plan_Fee) VALUES (?,?,?)";
        try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        	
        	
        	statement.setString(1, plan.getNameOfPlan());
        	statement.setInt(2, plan.getDurationInDays());
        	statement.setDouble(3, plan.getFee());
        	statement.executeUpdate();
        	
        	try(ResultSet plan_Id=statement.getGeneratedKeys()){
        		if(plan_Id.next()) {
        			plan.setId(plan_Id.getInt(1));
        		}
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        return plan;
    }

    @Override
    public MembershipPlan getPlanByName(String name) {
        String query="SELECT * FROM membership_plans WHERE plan_Name = ?";
        MembershipPlan plan=null;
        
        try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query);
        	
        	statement.setString(1, name);
        	
        	try(ResultSet membership_Plan=statement.executeQuery()){
        		
        		if(membership_Plan.next()) {
        			int plan_Id=membership_Plan.getInt("plan_Id");
        			String plan_Name=membership_Plan.getString("plan_Name");
        			int duration_In_Days=membership_Plan.getInt("duration_in_Days");
        			double fee=membership_Plan.getDouble("fee");
        			
        			plan=new MembershipPlan(plan_Id,plan_Name,duration_In_Days,fee);
        		}
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        return plan;
    }

    @Override
    public List<MembershipPlan> getAllPlans() {
    	List<MembershipPlan> plansList = new ArrayList<>();
    	String query="SELECT * FROM membership_plans";
    	
    	try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query);
        	
        	try(ResultSet plans=statement.executeQuery()){
        		while(plans.next()) {
        			int plan_Id=plans.getInt("plan_Id");
        			String plan_Name=plans.getString("plan_Name");
        			int duration_In_Days=plans.getInt("duration_In_Days");
        			double fee=plans.getDouble("Fee");
        			plansList.add(new MembershipPlan(plan_Id,plan_Name,duration_In_Days,fee));
        		}
        	}
    	}
    	catch(SQLException e) {
        	e.printStackTrace();
        }
    	return plansList;
    }
}
