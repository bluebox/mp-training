package com.lms.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lms.model.Member;

@Service
public interface MemberService {
	
	public Member registerMember(Member member);

	public Member updateMember(Member member);

	public List<Member> getAllMembers();

	public Optional<Member>  fetchMemberById(int id);
}
