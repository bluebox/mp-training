package com.medplus.lms.service;

import java.util.List;

import com.medplus.lms.domain.Member;

public interface MemberServiceInterface {
	public String registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	 public void deleteMember(int memberId);
	 public Member getMemberById(int memberId) ;
}
