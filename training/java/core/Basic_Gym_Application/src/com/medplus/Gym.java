package com.medplus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Gym {
	private final LinkedHashMap<Integer, Member> gymMembers = new LinkedHashMap<Integer, Member>();

	public void add(Member member) {
		gymMembers.put(member.getMemberId(), member);
	}

	public void remove(Member member) {
		gymMembers.remove(member.getMemberId());
	}

	public ArrayList<Member> serachByName(String name) {
		ArrayList<Member> arrayListByName = new ArrayList<>();
		for (var i : gymMembers.values()) {
			if (i.getName() == name) {
				arrayListByName.add(i);
			}
		}
		return arrayListByName;
	}

	public ArrayList<Member> getAllMembers() {
		return new ArrayList<Member>(gymMembers.values());
	}

	public boolean checkById(int id) {
		return (gymMembers.containsKey(id));
	}
	
	public void remove(int id) {
		gymMembers.remove(id);
	}
	public Member getMember(int id) {
		return gymMembers.get(id);
	}
}
