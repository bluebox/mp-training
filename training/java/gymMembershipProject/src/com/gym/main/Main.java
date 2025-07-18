package com.gym.main;

import java.util.Scanner;

import com.gym.models.Member;
import com.gym.service.Gym;

public class Main {
	public static void main(String[] args) {
		Gym gym = new Gym();
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
				} catch (Exception e) {
					//TODO : handle exception better
					System.out.println("Invalid date exception");
				}
				
				break;

			// Remove member
			case "4":
				int memberId = Utils.takeMemberIdForDeleteInput();
				gym.deleteMember(memberId);
				
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
