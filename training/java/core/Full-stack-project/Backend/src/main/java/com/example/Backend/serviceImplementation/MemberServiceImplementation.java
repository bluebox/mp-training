package com.example.Backend.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Backend.daoImplementation.MemberDaoImpl;
import com.example.Backend.domain.Member;

@Service
public class MemberServiceImplementation {

	@Autowired
	private MemberDaoImpl memberDaoImpl;

	public int addMember(Member member) {

		return memberDaoImpl.addMember(member);
	}

	public void updateMember(int id, Member member) {
		member.setMemberId(id);
		memberDaoImpl.updateMember(member);
	}

	public Member getMemberById(int id) {
		return memberDaoImpl.findById(id);
	}

	public List<Member> getAllMembers() {
		return memberDaoImpl.findAllMembers();
	}

}
