package com.example.web.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.annotations.ValidateMembers;
import com.example.web.dao.MemberDao;
import com.example.web.model.Member;
import com.example.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao memberDao;
	
	@Override
	@ValidateMembers
	public void addNewMember(Member member) {
		memberDao.insertMember(member);
	}

	@Override
	public List<Member> getAllMembers() {
		List<Member> members = memberDao.fetchAllMembers();
		return members;
	}

	@Override
	@ValidateMembers
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}

	@Override
	public String getMemberNameById(int id) {
		String memberName = memberDao.getMemberById(id).getMember_Name();
		return memberName;
	}

	@Override
	public Member getMemberById(int memberId) {
		Member member =  memberDao.getMemberById(memberId);
		return member;
	}

	@Override
	public Member getMemberByEmail(String email) {
		List<Member> members = getAllMembers();
		
		members = members.stream()
				.filter(m->m.getEmail().equals(email))
				.collect(Collectors.toList());
		
		if(members == null || members.isEmpty()) {
			return null;
		}
		
		return members.get(0);		
	}

}
