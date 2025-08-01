package com.library_management.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library_management.dao.MemberDaoInterface;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.utilities.DBConnection;



public class MemberDaoImplementation implements MemberDaoInterface {

	@Override

	public int insertMember(Member member) throws SQLException, DatabaseException {
		String query = "insert into members (name, email, mobile, gender, address) VALUES (?, ?, ?, ?, ?)";
		Connection con=DBConnection.getConn();
		int id=-1;
		try {
			PreparedStatement ps=con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,member.getMemberName());
			ps.setString(2,member.getMemberMail());
			ps.setString(3, member.getMobileNo());
			ps.setString(4,member.getGender());
			ps.setString(5, member.getMemberAddress());
			ps.execute();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getInt(1);
			}
		}catch(SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		return id;

	}
	
	

	@Override
	public List<Member> getAllMembers() throws SQLException {
	    List<Member> members = new ArrayList<>();
	    String query = "SELECT * FROM members";
	    Connection con = DBConnection.getConn();
	    PreparedStatement ps = con.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        int id=rs.getInt("member_id"); 
	        String name=rs.getString("name");
	        String email = rs.getString("email");
	        String mobile = rs.getString("mobile");
	        String gender = rs.getString("gender");
	        String address = rs.getString("address");

	        Member member = new Member(id, name, email, mobile, gender, address);
	        members.add(member);
	    }

	    return members;
	}


	@Override
	public void updateMember(Member oldMember,Member newMember) throws DatabaseException {
		Connection conn=DBConnection.getConn();
		String updateMembersQuery="update lms.members set name=?, email=?, mobile=?, address=? where member_id=?";
		String insertMembersLogQuery="insert into lms.members_log(member_id,name,email,mobile,gender,address) values(?,?,?,?,?,?)";
		try(PreparedStatement psInsertLog=conn.prepareStatement(insertMembersLogQuery);
				PreparedStatement psUpdate=conn.prepareStatement(updateMembersQuery);) {
			
			conn.setAutoCommit(false);
			psInsertLog.setInt(1, oldMember.getMemberId());
			psInsertLog.setString(2, oldMember.getMemberName());
			psInsertLog.setString(3,oldMember.getMemberMail());
			psInsertLog.setString(4, oldMember.getMobileNo());
			psInsertLog.setString(5, oldMember.getGender());
			psInsertLog.setString(6,oldMember.getMemberAddress());
			psInsertLog.executeUpdate();
			
			psUpdate.setString(1,newMember.getMemberName() );
			psUpdate.setString(2,newMember.getMemberMail() );
			psUpdate.setString(3,newMember.getMobileNo());
			psUpdate.setString(4,newMember.getMemberAddress());
			psUpdate.setInt(5,newMember.getMemberId());
			
			psUpdate.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			}catch (SQLException ex) {
				throw new DatabaseException("Member Updation Rollback Failed...");
			}
			throw new DatabaseException("Error Occurred while updating data...");
		}
		
	}
	
	@Override
	public Member selectMemberById(int id) throws DatabaseException {
		Member currentMember=null;
		Connection conn=DBConnection.getConn();
		String selectOneQuery="select * from lms.members where member_id=?";
		try(PreparedStatement psSelectOne=conn.prepareStatement(selectOneQuery);) {
			psSelectOne.setInt(1, id);
			psSelectOne.execute();
			ResultSet resultSet=psSelectOne.getResultSet();
			if(resultSet.next()) {
				int memberId=resultSet.getInt(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				String mobileNo=resultSet.getString(4);
				String gender=resultSet.getString(5);
				String  address=resultSet.getString(6);
				
				currentMember=new Member(memberId,name,email,mobileNo,gender,address);
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return currentMember;
	}

	
	 public boolean deleteMember(int memberId) throws DatabaseException {
	        Connection conn=DBConnection.getConn();
	        String selectQuery="select * from lms.members where member_id = ?";
	        String logInsertQuery="insert into lms.members_log(member_id, name, email, mobile, gender, address) values (?, ?, ?, ?, ?, ?)";
	        String deleteQuery="delete from lms.members where member_id = ?";

	        try(PreparedStatement psSelect=conn.prepareStatement(selectQuery);
		            PreparedStatement psInsertLog=conn.prepareStatement(logInsertQuery);
		            PreparedStatement psDelete=conn.prepareStatement(deleteQuery);){
	        	conn.setAutoCommit(false);
	            psSelect.setInt(1,memberId);
	            ResultSet rs=psSelect.executeQuery();

	            if (!rs.next()) {
	                throw new DatabaseException("Member not found.");
	            }
               
	            

	            int id=rs.getInt("member_id");
	           String name=rs.getString("name");
	           String email=rs.getString("email");
	           String mobile=rs.getString("mobile");
	           String gender=rs.getString("gender");
	           String address=rs.getString("address");

	        
	            psInsertLog.setInt(1, id);
	            psInsertLog.setString(2, name);
	            psInsertLog.setString(3, email);
	            psInsertLog.setString(4, mobile);
	            psInsertLog.setString(5, gender);
	            psInsertLog.setString(6, address);
	            psInsertLog.executeUpdate();

	          
	            psDelete.setInt(1, memberId);
	            int rowsAffected = psDelete.executeUpdate();
	            
	            conn.commit();
	            conn.setAutoCommit(true);
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	        	try {
					conn.rollback();
				}catch (SQLException ex) {
					throw new DatabaseException("Member Deletion Rollback Failed...");
				}
	            throw new DatabaseException("Failed to delete member: " + e.getMessage());
	        }
	    }
	}







	

