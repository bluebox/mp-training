package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dataBase.*;
import models.Member;
import models.MembershipPlan;

public class GymService {
	public void addMember(Member member) {
		
		try(Connection connect=DatabaseConnection.getConnection()){
			
			PreparedStatement insertion=connect.prepareStatement("INSERT INTO members (Name,age,gender) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			insertion.setString(1, member.getName());
			insertion.setInt(2, member.getAge());
			insertion.setString(3, member.getGender());
			int insertionSuccess=insertion.executeUpdate();
			if(insertionSuccess > 0) {
				try(ResultSet generatedId=insertion.getGeneratedKeys()){
					if(generatedId.next()) {
						System.out.println(member.getName()+" is succesfully added and member Id alloted is : "+generatedId.getInt(1));	
					}
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPlans() {
		try(Connection connect=DatabaseConnection.getConnection()){
			
			PreparedStatement viewplans=connect.prepareStatement("select * from membership_plans");
			ResultSet plans=viewplans.executeQuery();
			while(plans.next()) {
				System.out.print("plan Id : "+plans.getInt("plan_id")+"\nplan name : "+plans.getString("plan_name")+"\nduration : "+plans.getString("duration")
				+"\ncost : "+plans.getDouble("cost"));
				System.out.println("\n");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member getMemberById(int memberId) {
	    String query = "SELECT m.memberId, m.Name, m.gender, m.age, p.plan_id, p.plan_name, p.cost, p.duration " +
	                   "FROM members m left JOIN membership_plans p ON m.plan_ids = p.plan_id WHERE m.memberId = ?";

	    try (Connection connect = DatabaseConnection.getConnection();
	         PreparedStatement getMembers = connect.prepareStatement(query)) {
	        	getMembers.setInt(1, memberId);
	        	ResultSet members = getMembers.executeQuery();
	        	
	        	if (members.next()) {
	        		String name = members.getString("name");
	        		String gender = members.getString("gender");
	        		int age = members.getInt("age");
	        		MembershipPlan plan = null;
	        		int planId = members.getInt("plan_id");
	        		if (planId > 0) {
	                plan = new MembershipPlan(planId,members.getString("plan_name"),members.getString("duration"),members.getDouble("cost"));
	            }
	        		Member member = new Member(name, gender, age);
	        		member.setMemberId(memberId);
	        		member.setMembershipPlan(plan);
	        	return member;
	        }

	    } catch (SQLException | IOException e) {
	        System.err.println("Error fetching member: " + e.getMessage());
	    }
	    return null;
	}
	
	public void assignPlanToMember(int memberId, int planId) {

	    try (Connection connect = DatabaseConnection.getConnection();){
	        
	        String updateQuery = "UPDATE members SET plan_ids = ? WHERE memberId = ?";
	        try (PreparedStatement updateStatement = connect.prepareStatement(updateQuery)) {
	            updateStatement.setInt(1, planId);
	            updateStatement.setInt(2, memberId);

	            int rowsAffected = updateStatement.executeUpdate();
	            if(rowsAffected > 0) {
	            	System.out.println("Assigned the plan ");
	            }
	            else {
	            	System.out.println("failed in assigning");
	            }
	            	
	        }

	    } catch (SQLException | IOException e) {
	        System.err.println("Error assigning plan: " + e.getMessage());
	    }
	    return;
	}
	
	public List<Member> getAllMembers() {
	    List<Member> members = new ArrayList<>();
	    String query = "SELECT m.memberId, m.name, m.gender, m.age, " +
	                   "p.plan_id, p.plan_name, p.duration, p.cost " +
	                   "FROM members m LEFT JOIN membership_plans p ON m.plan_ids = p.plan_id ORDER BY m.name ASC";

	    try (Connection connect = DatabaseConnection.getConnection();
	         PreparedStatement selectStatement = connect.prepareStatement(query);
	         ResultSet resultSet = selectStatement.executeQuery()) {

	        while (resultSet.next()) {
	            int memberId = resultSet.getInt("memberId");
	            String name = resultSet.getString("Name");
	            String gender = resultSet.getString("gender");
	            int age = resultSet.getInt("age");

	            MembershipPlan plan = null;
	            if (resultSet.getInt("plan_id") > 0) {
	                plan = new MembershipPlan(resultSet.getInt("plan_id"),resultSet.getString("plan_name"),resultSet.getString("duration"),resultSet.getDouble("cost"));
	            }

	            Member member = new Member(name, gender, age);
	            member.setMemberId(memberId);
	            member.setMembershipPlan(plan);
	            members.add(member);
	        }
	    } catch (SQLException | IOException e) {
	        System.err.println("Error fetching members: " + e.getMessage());
	    }

	    return members;
	}

	public void updateMember(Member member) {
	    String query = "UPDATE members SET Name = ?, gender = ?, age = ? WHERE memberId = ?";

	    try (Connection connect = DatabaseConnection.getConnection();
	         PreparedStatement updateStatement = connect.prepareStatement(query)) {

	    	updateStatement.setString(1, member.getName());
	    	updateStatement.setString(2, member.getGender());
	    	updateStatement.setInt(3, member.getAge());
	    	updateStatement.setInt(4, member.getMemberId());
	    	updateStatement.executeUpdate();

	    } catch (SQLException | IOException e) {
	        System.err.println("Error updating member: " + e.getMessage());
	    }
	}
	
	public  void deleteMember(Member member) {
	    String query = "DELETE FROM members WHERE memberId = ?";

	    try (Connection connect = DatabaseConnection.getConnection();
	         PreparedStatement deleteStatement = connect.prepareStatement(query)) {

	        deleteStatement.setInt(1, member.getMemberId());

	        int rowsAffected = deleteStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Member with ID " + member.getMemberId() + " deleted successfully.");
	        } else {
	            System.out.println("Deletion failed. Member may not exist.");
	        }

	    } catch (SQLException | IOException e) {
	        System.err.println("Failed in deleting member: " + e.getMessage());
	    }
	}

	public void recentlyDeleted(Member member) {
		
		String query = "INSERT INTO recently_deleted(Id,name,age,gender) values(?,?,?,?) ";
		try (Connection connect = DatabaseConnection.getConnection();
		        PreparedStatement recentStatement = connect.prepareStatement(query)) {
				recentStatement.setInt(1, member.getMemberId());
				recentStatement.setString(2, member.getName());
				recentStatement.setInt(3, member.getAge());
				recentStatement.setString(4, member.getGender());
				
				recentStatement.executeUpdate();
				if(member.getMembershipPlan()!=null) {
					PreparedStatement updateStatement = connect.prepareStatement("update recently_deleted set planu_Id=?");
					updateStatement.setInt(1, member.getMembershipPlan().getPlanId());
					updateStatement.executeUpdate();
				}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Member> getAllRecentDeletedMembers() {
		
		List<Member> members = new ArrayList<>();
	    String query = "SELECT m.Id, m.name, m.gender, m.age, " +
	                   "p.plan_id, p.plan_name, p.duration, p.cost " +
	                   "FROM recently_deleted m LEFT JOIN membership_plans p ON m.planu_Id = p.plan_id";

	    try (Connection connect = DatabaseConnection.getConnection();
	         PreparedStatement selectStatement = connect.prepareStatement(query);
	         ResultSet resultSet = selectStatement.executeQuery()) {
	    	
	    	if(resultSet==null) {
	    		System.out.println("No deletions found");
	    	}
	        while (resultSet.next()) {
	            int memberId = resultSet.getInt("Id");
	            String name = resultSet.getString("name");
	            String gender = resultSet.getString("gender");
	            int age = resultSet.getInt("age");

	            MembershipPlan plan = null;
	            if (resultSet.getInt("plan_id") > 0) {
	                plan = new MembershipPlan(resultSet.getInt("plan_id"),resultSet.getString("plan_name"),resultSet.getString("duration"),resultSet.getDouble("cost"));
	            }

	            Member member = new Member(name, gender, age);
	            member.setMemberId(memberId);
	            member.setMembershipPlan(plan);
	            members.add(member);
	        }
	    } catch (SQLException | IOException e) {
	        System.err.println("Error fetching members: " + e.getMessage());
	    }

	    return members;
	}


	public void recentUpdate(int memberId, String Field, String oldField, String newField, String dateTime) {
		String query="insert into recent_updates(Id,field,old_field,new_field,time) values (?,?,?,?,?)";
		try(Connection connect=DatabaseConnection.getConnection()){
			PreparedStatement updateStatement=connect.prepareStatement(query);
			updateStatement.setInt(1, memberId);
			updateStatement.setString(2, Field);
			updateStatement.setString(3, oldField);
			updateStatement.setString(4, newField);
			updateStatement.setString(5, dateTime);
			updateStatement.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	public void showRecentUpdates() {
		
		String query="select * from recent_updates";
		try(Connection connect=DatabaseConnection.getConnection()){
			PreparedStatement updateStatement=connect.prepareStatement(query);
			ResultSet results =updateStatement.executeQuery();
			if(results==null) {
				System.out.println("There are no updates");
			}
			System.out.println("-".repeat(35));
			while(results.next()) {
				System.out.print(results.getInt("Id")+" field Updated : "+results.getString("field")
				+" , old field : "+results.getString("old_field")+" , new field : "+results.getString("new_field")+" , Updated at : "+results.getString("time")+"\n");
				System.out.println();
			}
			System.out.println("-".repeat(35));
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}



























//public List<MembershipPlan> getAllPlans() {
//	
//	List<MembershipPlan> membershipPlans=new ArrayList<>();
//	try(Connection connect=DatabaseConnection.getConnection()){
//		PreparedStatement plansStatement=connect.prepareStatement("SELECT * FROM membership_plans");
//		ResultSet plans=plansStatement.executeQuery();
//		while(plans.next()) {
//			membershipPlans.add(new MembershipPlan(plans.getInt("plan_id"), plans.getString("plan_name"), plans.getString("duration"), plans.getDouble("cost")));
//		}
//	} catch (SQLException | IOException e) {
//		e.printStackTrace();
//	}
//	return membershipPlans;
//}