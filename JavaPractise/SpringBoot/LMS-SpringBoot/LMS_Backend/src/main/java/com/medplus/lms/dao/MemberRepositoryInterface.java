package com.medplus.lms.dao;

import java.util.List;

import com.medplus.lms.domain.Member;

public interface MemberRepositoryInterface {
	public Member getMemberByEmailOrMobile(String email, String mobile);
	public void addMember(Member member);
	public List<Member> getAllMembers();
	public Member findMemberById(int memberId);
	public void updateMember(Member member);
	public boolean hasActiveIssuedBooks(int memberId);
	public void updateMemberStatus(Member member);
}
