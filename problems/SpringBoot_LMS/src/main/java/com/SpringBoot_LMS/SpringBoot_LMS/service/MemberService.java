package com.SpringBoot_LMS.SpringBoot_LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Book;
import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;
import com.SpringBoot_LMS.SpringBoot_LMS.repository.BookRepository;
import com.SpringBoot_LMS.SpringBoot_LMS.repository.MemberRepository;

@Service
public class MemberService {
    
	@Autowired
	private MemberRepository memberrepository;
	
	public List<Member> getMembers() throws Exception{
		List<Member> members=memberrepository.getAllMembers();
		return members;
	}
	
	public Member getMemberById(int id) throws Exception {
		Member member=memberrepository.getMemberById(id);
		return member;
	}
	
	public int AddMember(Member member) throws Exception {
		int value=memberrepository.addMember(member);
		return value;
	}
	
	
	public Member updateMember(Member member) throws Exception {
		Member value=memberrepository.updateMember(member);
		return value;
	}
	
	
	
}
