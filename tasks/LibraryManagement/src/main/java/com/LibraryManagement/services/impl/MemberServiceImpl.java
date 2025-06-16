package com.LibraryManagement.services.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.LibraryManagement.dao.MemberDao;
import com.LibraryManagement.dao.impl.MemberDaoImpl;
import com.LibraryManagement.services.MemberService;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.pojos.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao md=new MemberDaoImpl();

	@Override
	public boolean addMemberService(Member member) throws Exception {
		Connection con =DBConnection.connectDB();
		con.setAutoCommit(false);
		if(md.verifyMember(member)) {
			if(md.addMember(member)) {
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
		}
		con.rollback();
		con.setAutoCommit(true);
		return false;
	}

	@Override
	public boolean updateMemberService(int memberId,String name,String email, Long mobile ) throws Exception {
		Connection con =DBConnection.connectDB();
		con.setAutoCommit(false);
 		try {
			if(md.verifyMember(memberId,name,email,mobile)) {
				if(md.updateMemberDetails(memberId,name,email,mobile)) {
					con.commit();
					con.setAutoCommit(true);
					return true;
				}
			}
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
		}
 		finally {
 			con.setAutoCommit(true);
 		}
		return false;
	}

	@Override
	public ArrayList<Member> viewAllMembersService() throws Exception {
 		return md.viewAllMembers();
	}

	@Override
	public ArrayList<Member> viewAllMembersLogService() throws Exception {
		return md.viewAllMembersLog();
	}
	
	

}
