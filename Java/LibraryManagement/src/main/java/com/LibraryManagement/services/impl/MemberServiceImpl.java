package com.LibraryManagement.services.impl;

import java.util.ArrayList;

import com.LibraryManagement.dao.impl.MemberDaoImpl;
import com.LibraryManagement.services.MemberService;
import com.LibraryManagement.utilites.pojos.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDaoImpl md=new MemberDaoImpl();

	@Override
	public boolean addMemberService(Member member) {
		try {
			if(md.verifyMember(member)) {
				md.addMember(member);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean updateMemberService(int memberId,String name,String email, Long mobile ) {
 		try {
			if(md.verifyMember(memberId,name,email,mobile)) {
				md.updateMemberDetails(memberId,name,email,mobile);
			}
		} catch (Exception e) {
			e.printStackTrace();
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