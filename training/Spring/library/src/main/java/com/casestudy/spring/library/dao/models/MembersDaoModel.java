package com.casestudy.spring.library.dao.models;

import java.util.List;

import com.casestudy.spring.library.beans.Member;

public interface MembersDaoModel {
	
	public abstract void addMember(Member member);
	
	public abstract boolean updateMember(Member member) ;
	
	public abstract List<Member> getAllMembers();
	
	public abstract Member getMemberById(int id) ;
	
	public abstract  boolean findMember(int memberId);
	
	
}

