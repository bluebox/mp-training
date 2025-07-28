package com.LibraryManagement.service.implementation;

import java.util.List;

import com.LibraryManagement.DAO.Implementation.MemberDAOImplementation;
import com.LibraryManagement.exception.MemberDAOException;
import com.LibraryManagement.models.Member;
import com.LibraryManagement.service.interfaces.MemberService;

public class MemberServiceImplementation implements MemberService {

	MemberDAOImplementation memberService = new MemberDAOImplementation();

	@Override
	public void registerMember(Member member) {
		if (member == null || member.getName() == null || member.getEmail() == null || member.getGender() == null
				|| member.getAddress() == null) {
			throw new MemberDAOException("Invalid , Member Data can't be null");
		}
		memberService.registerMember(member);
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

}
