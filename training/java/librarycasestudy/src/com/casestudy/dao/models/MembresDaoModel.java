package com.casestudy.dao.models;

import java.util.List;

import com.casestudy.domain.Member;

public interface MembresDaoModel {
	
	public abstract void addMember(Member member);
	
	public abstract boolean updateMember(Member member) ;
	
	public abstract List<Member> getAllMembers();
	
	public abstract Member getMemberById(int id) ;
	
	public abstract  boolean findMember(int memberId);
	
	
}
