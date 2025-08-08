package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
	private static List<Member> members = new ArrayList<>();

	static {
		members.add(createMember("Kaushik", 22));
		members.add(createMember("Kashyap", 18));
	}

	private static Member createMember(String name, Integer age) {
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		return member;
	}

	public static boolean addMember(Member member) {
		if (member == null || member.getName() == null || member.getName().trim().isEmpty()) {
			return false;
		}
		return members.add(member);
	}

	public static boolean addMember(String name, Integer age) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}
		Member member = createMember(name, age);
		return members.add(member);
	}

	public static List<Member> getAllMembers() {
		return new ArrayList<>(members);
	}

	public static void clearAllMembers() {
		members.clear();
	}

	public static int getMemberCount() {
		return members.size();
	}

}