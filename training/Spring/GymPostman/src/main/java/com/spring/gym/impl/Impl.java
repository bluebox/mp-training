package com.spring.gym.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.gym.beans.Member;
import com.spring.gym.dao.MemberDao;

public class Impl {
	
	private MemberDao memberDao;
	
	@Autowired
	public Impl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public boolean addMember(Member member) {
		return memberDao.addMember(member);
	}

}
