package com.LMS.LibMS.service.interfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.repository.interfaceImpl.MemberRepositoryImpl;
import com.LMS.LibMS.service.interfaces.MemberService;

@Service
public class MemberServiceImpl  implements MemberService{
	private MemberRepositoryImpl memberRepository;
	
	@Autowired
	public MemberServiceImpl(MemberRepositoryImpl memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public List<Member> findMembers(){
		return memberRepository.findMembers();
	}
	
}
