package com.medplus;

public class Main {

	public static void main(String[] args) {
		GymService service = new GymService();
		int selection;

		System.out.println("\nWelcome to the Gym ");
		while (true) {
			System.out.println("""
					
					1.To Enroll New MemberShip
					2.To See Our Membership Plans
					3.To Check Your Membership Details
					4.To See All Members Enrolled
					5.To Remove your Membership
					6.Search By name
					7.To Exit
					""");
			selection = InputValidator.getValidatedInteger("Enter Your Selection  : ", 1, 7);
			switch (selection) {
			case (1) -> service.newMember();
			case (2) -> service.showMembershipPlans();
			case (3) -> service.findMyDetailsById();
			case (4) -> service.showMembers();
			case (5) -> service.RemoveMyMembership();
			case (6) -> service.searchByName();
			case (7) -> System.out.println("Have a nice Day !!");
			default -> System.out.println("Enter a valid Input");
			}
			if (selection == 7) {
				break;
			}
		}

	}
}
