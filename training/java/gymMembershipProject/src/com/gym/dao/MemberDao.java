package com.gym.dao;

import java.util.ArrayList;
import java.util.List;

import com.gym.models.Member;

public interface MemberDao {
	
	boolean saveToFile(List<Member> members);
	boolean saveNewMember(Member member);
	ArrayList<Member> loadFromFile();
	
}


//todo: implementation should be impl at the end
