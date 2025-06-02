package com.medplus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gym {
	Random random = new Random();
	private int id;
	Scanner scanner = new Scanner(System.in);
	
	private static ArrayList<Member> members = new ArrayList<Member>();
	
	
	public void addMember(String name, int age,LocalDate StartDate,MembershipPlan plan) {
		
		id = random.nextInt(10000,100000);
		for (var i : members) {
			if (i.getMemberId() == id) {
				addMember( name,  age, StartDate, plan);
			}
		}
		Member temp = new Member(id,name,age,StartDate,plan);
		members.add(temp);
		System.out.println("\nMember added successfully.");
		temp.getPersonDetails();
	}
	

	
	


		public static ArrayList<Member> getArrayListMembers(){
			return members;
		}


}
