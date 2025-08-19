package com.library.library_management_system.repository;

import java.util.List;

import com.library.library_management_system.domain.Member;

public interface MemberRepository {

	public int addMember(Member member);

	public boolean getMemberByMobile(Long mobile);

	public boolean getMemberByEmail(String email);

	public int UpdateMember(Member member);

	public boolean getMemberByMobileExceptId(Long mobile, int id);

	public boolean getMemberByEmailExceptId(String email, int id);

	public Member getMemberById(int id);

	public List<Member> getAllMembers();

	public int deleteMember(Member member);

	public int memberLog(Member member);

}
