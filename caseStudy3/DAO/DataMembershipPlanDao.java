package gym.membership_Management.dao;

import gym.membership_Management.model.MembershipPlan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataMembershipPlanDao implements MembershipPlanDao {
	
	
	private static final String url="jdbc:mysql://localhost:3306/mydatabase";
	private static final String user="root";
	private static final String password="root";
	
	public DataMembershipPlanDao(){ 
		List<MembershipPlan> plan=getAllPlans();
    	if(plan.size()==0) {
			addPlan(new MembershipPlan("Basic", 30, 999));
			addPlan(new MembershipPlan("Gold", 90, 2499));
			addPlan(new MembershipPlan("Premium", 180, 4999));
		}
	}

    @Override
    public MembershipPlan addPlan(MembershipPlan plan) {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        String query="INSERT INTO Membership_Plans(plan_Name,duration_In_Days,plan_Fee) VALUES (?,?,?)";
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
    
    public void removePlanByName(String name) {
		String query="DELETE FROM membership_plans WHERE plan_Name = ?";
		
		try(Connection connection=DriverManager.getConnection(url,user,password);){
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, name);
			
			int rowsEffected=statement.executeUpdate();
			if(rowsEffected>0) {
				System.out.println("Deleted "+name+" plan.");
			}
			else {
				System.out.println("Can't delete "+name+" plan");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public MembershipPlan getPlanByName(String name) {
        String query="SELECT * FROM Membership_Plans WHERE plan_Name = ?";
        MembershipPlan plan=null;
        
        try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query);
        	
        	statement.setString(1, name);
        	
        	try(ResultSet membership_Plan=statement.executeQuery()){
        		if(membership_Plan.next()) {
        			int plan_Id=membership_Plan.getInt("plan_Id");
        			String plan_Name=membership_Plan.getString("plan_Name");
        			int duration_In_Days=membership_Plan.getInt("duration_In_Days");
        			double plan_Fee=membership_Plan.getDouble("plan_Fee");
        			
        			plan=new MembershipPlan(plan_Id,plan_Name,duration_In_Days,plan_Fee);
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
    	String query="SELECT * FROM Membership_Plans";
    	
    	try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query);
        	
        	try(ResultSet plans=statement.executeQuery()){
        		while(plans.next()) {
        			int plan_Id=plans.getInt("plan_Id");
        			String plan_Name=plans.getString("plan_Name");
        			int duration_In_Days=plans.getInt("duration_In_Days");
        			double plan_Fee=plans.getDouble("plan_Fee");
        			plansList.add(new MembershipPlan(plan_Id,plan_Name,duration_In_Days,plan_Fee));
        		}
        	}
    	}
    	catch(SQLException e) {
        	e.printStackTrace();
        }
    	return plansList;
    }
}

