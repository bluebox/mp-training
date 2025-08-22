package com.library.service;

import java.util.List;

import com.library.model.Member;

public interface MemberService {
	
	public int registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	Member fetchMemberById(int id) throws Exception;
}
