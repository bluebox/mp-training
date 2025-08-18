package com.lms.service.interfaces.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.model.Member;
import com.lms.repositories.MemberRepository;
import com.lms.service.interfaces.MemberService;

@Service
public class MemberServiceImplementation implements MemberService {

	private final MemberRepository memberRepo;

	@Autowired
	public MemberServiceImplementation(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}

	@Override
	public Member registerMember(Member member) {
		if (member == null || member.getName() == null || member.getEmail() == null || member.getAddress() == null) {
			throw new RuntimeException("Invalid , Member Data can't be null");
		}
		return memberRepo.registerMember(member);
	}

	@Override
	public Member updateMember(Member member) {
		if (member == null || member.getMemberId() <= 0) {
			throw new RuntimeException("Invalid book ID for update.");
		}
		return memberRepo.updateMember(member);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepo.getAllMembers();
	}

	@Override
	public Optional<Member> fetchMemberById(int id) {
		if (id <= 0)
			throw new RuntimeException("Invalid Member ID");
		return memberRepo.fetchMemberById(id);
	}

}
