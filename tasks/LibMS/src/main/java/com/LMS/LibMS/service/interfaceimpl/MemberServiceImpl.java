package com.LMS.LibMS.service.interfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.repository.MemberRepository;
import com.LMS.LibMS.service.interfaces.MemberService;

@Service
public class MemberServiceImpl  implements MemberService{
	private MemberRepository memberRepository;
	
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public List<Member> findMembers(){
		return memberRepository.findMembers();
	}
	
}
