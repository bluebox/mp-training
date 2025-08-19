package com.example.library.serviceimplementation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.daoImpl.MemberDaoImpl;
import com.example.library.domain.Member;
import com.example.library.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDaoImpl memberDao;
	@Override
	public int addMember(Member member) {
		return memberDao.addMember(member);
	}
	@Override
	public void updateMember(int id, Member member) {
		member.setMemberId(id);
		memberDao.updateMember(member);
	}
	@Override
	public Member getMemberById(int id) {
		return memberDao.findById(id);
	}
	@Override
	public List<Member> getAllMembers() {
		return memberDao.findAllMembers();
	}

}
