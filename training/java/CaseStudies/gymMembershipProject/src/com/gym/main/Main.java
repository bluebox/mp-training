package com.gym.main;

import java.util.Scanner;

import com.gym.exceptions.InvalidDateException;
import com.gym.models.Member;
import com.gym.service.GymServiceImpl;

public class Main {
	public static void main(String[] args) {
		GymServiceImpl gym = new GymServiceImpl();
		
		Scanner sc = new Scanner(System.in);
		boolean quit = false;

		while (!quit) {
			System.out.println("Welcome to the gym , entered your desired option");
			System.out.println(
					"1.View Gym Members \n"
					+ "2.Add new member\n"
					+ "3.Assign membership plan to member.\n"
					+ "4.Remove Member \nq. Quit"
					);

			String input = sc.next();

			switch (input) {
			
			// View Members
			case "1":
				gym.showAllMembers();
				break;

			// Add new member
			case "2":
				
				String name = Utils.takeNameInput();
				int age = Utils.takeAgeInput();
				int weight = Utils.takeWeightInput();
				int height = Utils.takeHeightInput();

				gym.addNewMember(new Member(name, age, height, weight));
				break;

			// Assign membership to member
			case "3":
				gym.showAllMembers();
				int memId = Utils.takeMemberIdInput();
				String date = Utils.takeDateInput();
				int planId = Utils.takePlanIdInput();
				
				try {
					gym.assignPlanToMember(memId, planId, date);
				} catch (InvalidDateException e) {
					System.out.println("Cannot enter date earlier than the previous date\n");
				}
				
				break;

			// Remove member
			case "4":
				int memberId = Utils.takeMemberIdForDeleteInput();
				boolean isDeleted = gym.deleteMember(memberId);
				if(isDeleted) {
					System.out.println("Successfully deleted member\n");
				}else {
					System.out.println("Cannot delete the user\n");
				}
				break;
			case "q":
				quit = true;
				break;
	
			default:
				System.out.println("Not a valid option\n");
				break;
			}

		}

	}

}
