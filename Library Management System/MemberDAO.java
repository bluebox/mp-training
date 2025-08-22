package com.LibraryManagement.DAO;

import java.util.List;

import com.LibraryManagement.models.Member;

public interface MemberDAO {

	public void registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	
}
