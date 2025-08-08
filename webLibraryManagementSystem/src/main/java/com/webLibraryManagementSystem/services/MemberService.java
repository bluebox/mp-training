package com.webLibraryManagementSystem.services;

import java.util.List;

import com.webLibraryManagementSystem.domain.Member;
import com.webLibraryManagementSystemexceptions.InvalidException;

public interface MemberService {

	int registerMember(Member member) throws InvalidException;

	List<Member> getMembers() throws InvalidException;

	int updateMember(Member newMember, Member oldMember) throws InvalidException;

	int deleteMember(Member memberData) throws InvalidException;

	Member getMemberId(int memberId) throws InvalidException;
}
