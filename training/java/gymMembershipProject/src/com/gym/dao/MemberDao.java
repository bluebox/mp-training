package com.gym.dao;

import java.util.ArrayList;
import java.util.List;

import com.gym.models.Member;

public interface MemberDao {
	
	boolean saveMembers(List<Member> members);
	boolean saveNewMember(Member member);
	boolean updateMember(Member member);
	boolean deleteMember(int memberId);
	ArrayList<Member> loadMembers();
	
}


//todo: implementation should be impl at the end
