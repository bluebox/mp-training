package com.gym.service;

import java.util.HashMap;

import com.gym.domain.Member;


public class GymService {

	private HashMap<Long, Member> members;
	private static String name = "MEDPLUS GYM";
	private static String description = "Excuses don’t burn calories.";
	

	public GymService() {
		super();
		members = new HashMap<>();
		System.out.println("Welcome to " + name);
		System.out.println("**** " + description+" ****");
		System.out.println("-".repeat(20));
	}

	public void addMember(Member member) {
		members.put(member.getId(), member);

	}

	public void deleteUser(long id) {
		if(members.containsKey(id))
		{
			members.remove(id);
			System.out.println("User deleted with user id "+id);
			
		}
		else
		{
			System.out.println("User Not Found");
		}
	}

	public void printDetails() {
		members.forEach((l, member) -> {
			System.out.println();
			System.out.println(member);
			System.out.println( member.getMemberShip() !=null ?member.getMemberShip():"memeber has no plan");

			
		});
	}

	public Member getMember(long id) {
		if (members.containsKey(id)) {
			return members.get(id);
		} else {

			return null;
		}

	}

}
