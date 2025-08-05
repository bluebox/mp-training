package com.lms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.lms.Utils.ValidatorsUtil;
import com.lms.dao.MemberDao;
import com.lms.dao.MemberDaoImpl;
import com.lms.model.Member;
import com.lms.service.MemberService;

public class MemberServiceImpl implements MemberService{

	MemberDao memberDao;
	
	public MemberServiceImpl() {
		this.memberDao = new MemberDaoImpl();
	}
	
	
	
    @Override
    public void addNewMember(Member member) throws IllegalArgumentException{
    	
    		ValidatorsUtil.validateMember(member);
    		memberDao.insertMember(member);
    	
    }
    
    @Override
    public Member getMemberById(int memberId) {
	    if (memberId <= 0) {
	        throw new IllegalArgumentException("Member ID must be greater than 0");
	    }
	    
	    Member member = memberDao.getMemberById(memberId);
	    if (member == null) {
	        throw new IllegalArgumentException("No member found with ID: " + memberId);
	    }
	    return member;
	}


    
	@Override
	public List<Member> getAllMembers() {
		return memberDao.fetchAllMembers();
	}
	@Override
	public String getMemberNameById(int id) {
		return memberDao.getMemberById(id).getMember_Name();
	}
	
	@Override
	public void updateMember(Member member) throws IllegalArgumentException {
		ValidatorsUtil.validateMember(member);
		memberDao.updateMember(member);
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
