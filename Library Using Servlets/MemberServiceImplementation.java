package com.library.service;

import java.util.List;

import com.library.Exception.MemberDAOException;
import com.library.dao.MemberDAOImplementation;
import com.library.model.Member;

public class MemberServiceImplementation implements MemberService {

	MemberDAOImplementation memberService = new MemberDAOImplementation();

	@Override
	public int registerMember(Member member) {
		if (member == null || member.getName() == null || member.getEmail() == null || member.getGender() == null
				|| member.getAddress() == null) {
			throw new MemberDAOException("Invalid , Member Data can't be null");
		}
		return memberService.registerMember(member);
	}

	@Override
	public void updateMember(Member member) {
		if (member == null || member.getMemberId() <= 0) {
			throw new MemberDAOException("Invalid book ID for update.");
		}
		memberService.updateMember(member);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberService.getAllMembers();
	}
	@Override

	public Member fetchMemberById(int id) throws Exception {

		if (id <= 0)
			throw new Exception("Invalid Member ID");

		Member member = memberService.getMemberById(id);
		if (member == null)
			throw new Exception("Member not found");

		return member;
	}

	public boolean isMobileUnique(long mobile) {
	    return memberService.isMobileUnique(mobile);
	}
}
