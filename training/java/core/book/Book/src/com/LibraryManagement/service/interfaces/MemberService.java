package com.LibraryManagement.service.interfaces;

import java.util.List;

import com.LibraryManagement.models.Member;

public interface MemberService {
	
	public int registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	Member fetchMemberById(int id) throws Exception;
}
