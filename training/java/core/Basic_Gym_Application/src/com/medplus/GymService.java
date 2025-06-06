package com.medplus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class GymService {	
	private Gym gym ;
	public GymService() {
		this.gym= new Gym();
	}

	public  void newMember() {
		Random random = new Random();
		int newMembershipId = random.nextInt(100000,1000000);
		if (!gym.checkById(newMembershipId)) {
			String name = InputValidator.getValidatedString("Enter Your Name : ");
			int age = InputValidator.getValidatedInteger("Enter Your Age : ", 14, 80);
			showMembershipPlans();
			int choosenPlan = InputValidator.getValidatedInteger("Choose your Plan : ", 1, 3);
			MembershipPlans plan = switch(choosenPlan) {
			case(1)->MembershipPlans.BASIC;
			case(2)->MembershipPlans.GOLD;
			case(3)->MembershipPlans.PREMIUM;
			default -> MembershipPlans.BASIC;
			};
			Member newMember = new Member(newMembershipId,name,age,LocalDate.now(),plan);
			gym.add(newMember);
			System.out.println();
			newMember.getPersonDetails();
		}
	}

	public  void showMembers() {
		ArrayList<Member> tempMembers = gym.getAllMembers();
		if (tempMembers.size()==0) {
			System.out.println("No Members Avalable !!!");
		}else {
			printMembers(tempMembers);
		}
	}

	public  void findMyDetailsById() {
		int Id = InputValidator.getValidatedInteger("Enter Your MembershipId : ", 100000,1000000);
		if(gym.checkById(Id)) {
			gym.getMember(Id).getPersonDetails();
		}else {
			System.out.println("No Match For Membership Id");
		}
	}

	public  void RemoveMyMembership() {
		int Id = InputValidator.getValidatedInteger("Enter Your MembershipId : ", 100000,1000000);
		if(gym.checkById(Id)) {
			gym.remove(Id);
			System.out.println("Removed Member Succesfully");
		}else {
			System.out.println("No Match For Membership Id");
		}
	}

	public  void searchByName() {
		String name = InputValidator.getValidatedString("Enter Name to Search : ");
		ArrayList<Member> tempMembers = gym.serachByName(name);
		if (tempMembers.size()==0) {
			System.out.println("No Match Found for that Name");
		}else {
			printMembers(tempMembers);
		}
	}

	public void showMembershipPlans() {
		MembershipPlans.showPlans();
	}
	public void printMembers(ArrayList<Member> PrintingList) {
		System.out.printf("%10s %10s %10s %10s %15s %15s %s".formatted("Member Id", "Name", "Age", "Plan", "Start Date",
				"EndDate", "Status"));
		int count = 1;
		for (var i : PrintingList)
			System.out.printf("%n%d %10d %10s %10d %10s %15s %15s %s ".formatted(count, i.getMemberId(), i.getName(),
					i.getAge(), i.getPlan(), i.getStartDate(), i.getEndDate(), i.getStatus()));
		count++;
	}
	

}
