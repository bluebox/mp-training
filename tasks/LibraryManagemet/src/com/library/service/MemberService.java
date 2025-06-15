package com.library.service;

import com.library.dao.MemberDAO;
import com.library.dao.UpdateMemberDetailsDAO;
import com.library.domain.Member;

import java.util.List;

public class MemberService {

	MemberDAO memberdao=new MemberDAO();
	
	public void addMember(Member member)
	{
		memberdao.addMember(member);
	}

	public List<Member> viewAllMembers() {
		return memberdao.getAllMembers();
	}

	public void updateMember(Member member) {
		memberdao.updateMember(member);
	}

}