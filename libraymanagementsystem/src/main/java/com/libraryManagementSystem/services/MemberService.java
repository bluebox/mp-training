package com.libraryManagementSystem.services;

import java.util.List;

import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;

public interface MemberService {

	int registerMember(Member member) throws InvalidException;

	List<Member> getMembers() throws InvalidException;

	int updateMember(Member newMember, Member oldMember) throws InvalidException;

	int deleteMember(Member memberData) throws InvalidException;
}
