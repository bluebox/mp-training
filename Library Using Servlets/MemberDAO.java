package com.library.dao;

import java.util.List;

import com.library.model.Member;

public interface MemberDAO {

	public int registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	 Member getMemberById(int memberId);
}
