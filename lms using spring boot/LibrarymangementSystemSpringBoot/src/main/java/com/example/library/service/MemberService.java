package com.example.library.service;

import java.util.List;

import com.example.library.domain.Member;

public interface MemberService {

	int addMember(Member member);

	void updateMember(int id, Member member);

	Member getMemberById(int id);

	List<Member> getAllMembers();

}
