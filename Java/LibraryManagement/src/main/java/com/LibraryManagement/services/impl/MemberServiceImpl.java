package com.LibraryManagement.services.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LibraryManagement.dao.MemberDao;
import com.LibraryManagement.dao.impl.MemberDaoImpl;
import com.LibraryManagement.services.MemberService;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.pojos.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao md=new MemberDaoImpl();

	@Override
	public boolean addMemberService(Member member) throws ClassNotFoundException, Exception, SQLException {
//		Connection con=DBConnection.getConnection();
		System.out.println(member.getEmail());
		try {
			if(md.verifyMember(member)) {
				System.out.println("verified");
				if(md.addMember(member)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	   return false;
	}

	@Override
	public boolean updateMemberService(int memberId,String name,String email, Long mobile ) throws ClassNotFoundException, IOException, SQLException {
		Connection con=DBConnection.getConnection();
 		try {
 			con.setAutoCommit(false);
			if(md.verifyMember(memberId,name,email,mobile)) {
				if(md.updateMemberDetails(memberId,name,email,mobile)) {
					con.commit();
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