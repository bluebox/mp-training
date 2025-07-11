package com.loanmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanmanagement.DAO.LoanDAO;
import com.loanmanagement.DAO.MemberDAO;
import com.loanmanagement.Exceptions.GeneralException;
import com.loanmanagement.Exceptions.NoMemberException;
import com.loanmanagement.model.Member;

@Service
public class MemberServiceImplementation implements MemberService {

	private final MemberDAO memberDAO;

	@Autowired
	public MemberServiceImplementation(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}

	@Override
	public void addMember(Member member) throws Exception {
		//get by mail
		if(member==null)
			throw new NoMemberException("invalid member details");
		if(memberDAO.getMemberByEmail(member.getEmail())!=null)
			throw new NoMemberException("this email id already exists");
		memberDAO.addMember(member);
	}
	
	@Override 
	public Member getMemberById(int id)throws Exception{
		Member member= memberDAO.getMemberById(id);
		if(member==null)
			throw new NoMemberException("Member not found");
		return member;
	}
	@Override
	public List<Member> getAllMembers() throws Exception{
		return memberDAO.getAllMembers();
	}
}
