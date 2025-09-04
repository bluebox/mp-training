package com.example.web.service;

import java.util.List;

import com.example.web.model.Member;

public interface MemberService {
	void addNewMember(Member member);
	List<Member> getAllMembers();
	void updateMember(Member member);
	String getMemberNameById(int id);
	Member getMemberById(int memberId);
	Member getMemberByEmail(String email);
}
