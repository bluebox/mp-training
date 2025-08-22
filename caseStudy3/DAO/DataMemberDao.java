package gym.membership_Management.dao;

import gym.membership_Management.model.Member;
import gym.membership_Management.model.MemberStatus;
import gym.membership_Management.model.MembershipPlan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataMemberDao implements MemberDao {
	
	
	private static final String url="jdbc:mysql://localhost:3306/mydatabase";
	private static final String user="root";
	private static final String password="root";

    @Override
    public Member addMember(Member member) {
        String query="INSERT INTO member(member_Name,member_Age,plan_Id,member_status,removal_Reason) VALUES (?,?,?,?,?)";
        try(Connection connection=DriverManager.getConnection(url,user,password);){
        	PreparedStatement statement=connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        	statement.setString(1, member.getName());
        	statement.setInt(2, member.getAge());
        	statement.setInt(3, member.getPlan().getId());
        	statement.setString(4, member.getStatus().name());
        	statement.setString(5,member.getRemovalReason());
        	
        	int rows_Effected=statement.executeUpdate();
        	
        	if(rows_Effected == 0) {
        		throw new SQLException("Interting the member get failed. Please try again");
        	}
        	
        	try(ResultSet generatedKey=statement.getGeneratedKeys()){
        		if(generatedKey.next()) {
        			member.setMembershipId(generatedKey.getInt(1));
        		}
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        return member;
    }

    @Override
    public Member getMemberById(int id) {
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
        String query="SELECT * FROM member m JOIN membership_plans p ON m.plan_Id=p.plan_Id WHERE m.member_Id=?";
        try(Connection connection=DriverManager.getConnection(url,user,password)){
        	PreparedStatement statement=connection.prepareStatement(query);
        	statement.setInt(1, id);
        	try(ResultSet member=statement.executeQuery()){
        		if(member.next()) {
        			String member_Name=member.getString("member_Name");
        			int member_Age=member.getInt("member_Id");
        			String plan_Name=member.getString("plan_Name");
        			int duration_In_Days=member.getInt("duration_In_Days");
        			double plan_Fee=member.getDouble("plan_Fee");
        			MemberStatus status=MemberStatus.valueOf(member.getString("member_status"));
        			String removal_Reason=member.getString("removal_Reason");
        			
        			MembershipPlan plan=new MembershipPlan(plan_Name,duration_In_Days,plan_Fee);
        			
        			return new Member(id,member_Name,member_Age,plan,status,removal_Reason);
        		}
        	}
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
    	List<Member> membersList = new ArrayList<>();
    	String query="SELECT * FROM Member m JOIN membership_plans p ON m.plan_Id=p.plan_Id";
    	
    	try(Connection connection=DriverManager.getConnection(url,user,password)){
    		PreparedStatement statement=connection.prepareStatement(query);
    		ResultSet members=statement.executeQuery();
    		
    		while(members.next()) {
    			int member_Id=members.getInt("member_Id");
    			String member_Name=members.getString("member_Name");
    			int member_Age=members.getInt("member_Age");
    			String plan_Name=members.getString("plan_Name");
    			int duration_In_Days=members.getInt("duration_In_Days");
    			double plan_Fee=members.getDouble("plan_Fee");
    			MemberStatus status=MemberStatus.valueOf(members.getString("member_status"));
    			String removal_Reason=members.getString("removal_Reason");
    			
    			MembershipPlan plan=new MembershipPlan(plan_Name,duration_In_Days,plan_Fee);
    			
    			Member member=new Member(member_Id,member_Name,member_Age,plan,status,removal_Reason);
    			membersList.add(member);
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return membersList;
    }

    @Override
    public boolean updateMemberStatus(int id, MemberStatus newStatus, String reason) {
        String query="UPDATE Member SET member_status = ?, removal_Reason = ? where member_Id = ?";
        
        try(Connection connection=DriverManager.getConnection(url,user,password)){
        	PreparedStatement statement=connection.prepareStatement(query);
    		statement.setString(1,newStatus.name());
    		statement.setString(2,reason);
    		statement.setInt(3, id);
    		
    		int rows_Effected=statement.executeUpdate();
    		return rows_Effected>0;
        }
        catch (SQLException e) {
    		e.printStackTrace();
            return false;
    	}
    }
}