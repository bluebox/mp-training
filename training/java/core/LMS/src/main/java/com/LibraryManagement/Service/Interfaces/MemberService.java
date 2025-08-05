package com.LibraryManagement.Service.Interfaces;

import java.util.List;

import com.LibraryManagement.Models.Member;

public interface MemberService {
	
	public int registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	Member fetchMemberById(int id) throws Exception;
}
