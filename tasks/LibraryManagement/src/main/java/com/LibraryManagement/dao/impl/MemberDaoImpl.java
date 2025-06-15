package com.LibraryManagement.dao.impl;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import com.LibraryManagement.dao.MemberDao;
import com.LibraryManagement.exceptions.DataBaseException;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.DBQueries;
import com.LibraryManagement.utilites.pojos.Member;

public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean addMember(Member member) {
		try{
			Connection con=DBConnection.connectDB();
			con.setAutoCommit(false);
			PreparedStatement pst=con.prepareStatement(DBQueries.insertToMembers);
			pst.setString(1,member.getName());
			pst.setString(2, member.getEmail());
			pst.setLong(3,member.getMobile());
			pst.setString(4, String.valueOf(member.getGender()));
			try {
				int countAffectedRows=pst.executeUpdate();
				System.out.println(countAffectedRows);
			}
			catch(SQLIntegrityConstraintViolationException e) {
				System.out.println("Email Already Exists");
			}
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	public boolean addMemberToLog(int memberId){
		try {
			Connection con=DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.getMemberWithId);
			pst.setInt(1, memberId);
			ResultSet rs=pst.executeQuery();
			rs.next();
			Reader ch=rs.getCharacterStream(5);
			Member member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),(char)(ch.read()));
			pst=con.prepareStatement(DBQueries.insertToMembersLog);
			pst.setInt(1, member.getMemberId());
			pst.setString(2,member.getName());
			pst.setString(3, member.getEmail());
			pst.setLong(4,member.getMobile());
			pst.setString(5,String.valueOf(member.getGender()));
			int countAffectedRows=pst.executeUpdate();
			System.out.println(countAffectedRows);
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}

	@Override
	public boolean updateMemberDetails(int memberId, String name,String email, Long mobile ) throws Exception {
		addMemberToLog(memberId);
		Connection con=DBConnection.connectDB();
		PreparedStatement pst=con.prepareStatement(DBQueries.updateMember);
		pst.setString(1,name);
		pst.setString(2, email);
		pst.setLong(3, mobile);
		pst.setInt(4, memberId);
		pst.executeUpdate();
		return false;
	}
	@Override
	public boolean verifyMember(int memberId,String name,String email, Long mobile ) throws Exception {
		Connection con=DBConnection.connectDB();
		PreparedStatement pst=con.prepareStatement(DBQueries.getMemberWithId);
		pst.setInt(1, memberId);
		ResultSet rs=pst.executeQuery();
		if(rs.next() && (!rs.getString(2).equals(name) || !rs.getString(3).equals(email) || !(rs.getLong(4)==mobile))) return true;
		return false;
	}

	@Override
	public boolean verifyMember(Member member) throws DataBaseException{
		if(member.getName()==null || member.getEmail()==null || member.getMobile()==null) {
			System.out.println("Enter the Member Details correctly");
			return false;
		}
		Connection con;
		try {
			con = DBConnection.connectDB();
			PreparedStatement pst=con.prepareStatement(DBQueries.getMember);
			pst.setString(1, member.getName());
			pst.setString(2, member.getEmail());
			pst.setLong(3,member.getMobile());
			ResultSet rs=pst.executeQuery();
			if(!rs.next()) return true;
			if(rs.getString(1).equals(member.getName()) && rs.getString(2).equals(member.getEmail()) && rs.getLong(3)==member.getMobile()) {
				System.out.println("Already Exists");
				return false;}
		}catch (Exception e) {
			throw new DataBaseException("Error with DataBase", e);
		}
		return false;
	}
	@Override
	public ArrayList<Member> viewAllMembers() throws Exception {
		ArrayList<Member> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllMembers);
		while(rs.next()) {
			Reader ch=rs.getCharacterStream(5);
			Member member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),(char)(ch.read()));
			arr.add(member);
		}
		return arr;
	}

	@Override
	public ArrayList<Member> viewAllMembersLog() throws Exception {
		ArrayList<Member> arr = new ArrayList<>();
		Connection con=DBConnection.connectDB();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(DBQueries.getAllMembersLog);
		while(rs.next()) {
			Reader ch=rs.getCharacterStream(5);
			Member member = new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getLong(4),(char)(ch.read()));
			arr.add(member);
		}
		return arr;
	}	

}
