package com.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.Utils.DatabaseUtil;
import com.lms.exceptions.DBConstrainsException;
import com.lms.model.Member;


public class MemberDaoImpl implements MemberDao {

    private static final String url = DatabaseUtil.getUrl();
    private static final String user = DatabaseUtil.getUser();
    private static final String password = DatabaseUtil.getPassword();


    @Override
    public int insertMember(Member newMember) {
    	int rowsAffected = 0;
        String query = "INSERT INTO members(Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, newMember.getMember_Name());
            statement.setString(2, newMember.getEmail());
            statement.setString(3, newMember.getMobile_No());
            statement.setString(4, String.valueOf(newMember.getGender()));
            statement.setString(5, newMember.getAddress());
            
            rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("SQL ERROR: Failed to insert member");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    newMember.setMember_Id(generatedId);
                } else {
                    throw new SQLException("Failed to retrieve generated Member ID.");
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rowsAffected;
    }

    @Override
    public List<Member> fetchAllMembers() {
        String query = "SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members;";
        List<Member> members = new ArrayList<>();
        
        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet membersList = statement.executeQuery();
            
            while (membersList.next()) {
                int memberId = membersList.getInt("MemberId");
                String name = membersList.getString("Name");
                String phone = membersList.getString("Mobile");
                String email = membersList.getString("Email");
                String address = membersList.getString("Address");
                char gender = membersList.getString("Gender").charAt(0);
                
                Member member = new Member(memberId, name, email, phone, gender,address);
                members.add(member);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return members;
    }
    
    @Override
    public int updateMember(Member member) {
    	int rowsAffected=0;
        String query = "UPDATE members SET Name=?, Email=?, Mobile=?, Gender=?, Address=? WHERE MemberId=?";
        
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, member.getMember_Name());
            statement.setString(2, member.getEmail());
            statement.setString(3, member.getMobile_No());
            statement.setString(4, String.valueOf(member.getGender()));
            statement.setString(5, member.getAddress());
            statement.setInt(6, member.getMember_Id());
            
            rowsAffected = statement.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("Failed to update member: Member not found");
            }
            addMemberLogs(member);
        } catch (SQLException e) {
            System.out.println("Error updating member: " + e.getMessage());
        }
        return rowsAffected;
    }
    
    @Override
    public void addMemberLogs(Member member) {
    	int id=member.getMember_Id();
    	try {
    		if(getMemberById(id)==null) {
        		String query = "INSERT INTO members_log(MemberId,Name,Email,Mobile,Gender,Address) VALUES (?,?,?,?,?,?);";
            	try (Connection connection = DriverManager.getConnection(url, user, password);){
        			PreparedStatement statement = connection.prepareStatement(query);
        			statement.setInt(1, member.getMember_Id());
        			statement.setString(2, member.getMember_Name());
                    statement.setString(3, member.getEmail());
                    statement.setString(4, member.getMobile_No());
                    statement.setString(5, String.valueOf(member.getGender()));
                    statement.setString(6, member.getAddress());
                    
                    int rowsAffected = statement.executeUpdate();
                    
                    if (rowsAffected == 0) {
                        throw new SQLException("Add member log failed, no rows affected.");
                    } 
        			
            	} 
            	
            }
    		else {
        		throw new DBConstrainsException("Member Id already exists" +id);
        	}
    	}
    	catch (SQLException |DBConstrainsException e) {
			System.out.println(e.getMessage());
		}
    }
    
	@Override
	public Member getMemberById(int memberId) {
		Member member=null;
		String query="SELECT MemberId,Name,Email,Mobile,Gender,Address FROM members WHERE MemberId=?";
		try (Connection connection = DriverManager.getConnection(url, user, password);){
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, memberId);
            ResultSet resultMember = statement.executeQuery();
            if(resultMember.next()) {
            	int member_Id = resultMember.getInt("MemberId");
                String name = resultMember.getString("Name");
                String phone = resultMember.getString("Mobile");
                String email = resultMember.getString("Email");
                String address = resultMember.getString("Address");
                char gender = resultMember.getString("Gender").charAt(0);
                
                member = new Member(member_Id, name, email, phone, gender,address);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}
