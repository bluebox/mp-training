package com.gym.dao;

import java.util.ArrayList;

import com.gym.classes.Member;

public interface MemberDao {
	
	boolean saveToFile(ArrayList<Member> members); 
	ArrayList<Member> loadFromFile();
	
}
