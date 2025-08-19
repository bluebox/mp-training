package com.library.library_management_system.service;

import java.util.List;

import com.library.library_management_system.domain.Member;

import jakarta.transaction.Transactional;

public interface MemberService {

	public int addMember(Member member);

	@Transactional
	public Member updateMember(Member newMember, Member oldMember);

	public Member getMemberById(int id);

	public List<Member> getMembers();

	public int deleteMember(Member member);
}
