package com.medplus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class MemberController {
	private static ArrayList<Member> tempMembers = Gym.getArrayListMembers();

	static Scanner scanner = new Scanner(System.in);

	public static void getMyDetails(int tempId) {
		boolean flag = false;
		for (var i : tempMembers) {
			if (i.getMemberId() == tempId) {
				renew(i);
				flag = true;
			}
		}
		if (!flag) {
			getMyDetails(tempId, 2);
		}
	}

	public static void getMyDetails(int tempId, int count) {
		if (count <= 3) {
			System.out.print("\nInvalid Membership Id \n Enter a valid id : ");
			tempId = scanner.nextInt();
			boolean flag = false;
			for (var i : tempMembers) {
				if (i.getMemberId() == tempId) {
					renew(i);
					flag = true;
				}
			}
			if (!flag) {
				count = count + 1;
				getMyDetails(tempId, count);
			}
		} else {
			System.out.println("\nYour no of tries exceeded");
		}
	}

	public static void renew(Member i) {
		i.getPersonDetails();
		System.out.println("\n1.Renew  2.Recharge  \n3.Go Back to Main Menu");
		int temp = Input.getNumberInRange(1, 3);
		if (temp == 2) {
			if (i.getEndDate().isBefore(LocalDate.now())) {
				i.setEndDate(LocalDate.now().plusMonths(1));
				System.out.println("\nYour Recharge is Succesful !");
			} else {
				i.setEndDate(i.getEndDate().plusMonths(1));
				System.out.println("\nYour Recharge is Succesful !");

			}
		}
		if (temp == 1) {
			if (i.getEndDate().isBefore(LocalDate.now())) {
				i.setPlan(Input.getValidatedMembershipPlan());
				i.setEndDate(LocalDate.now().plusMonths(1));
			} else {
				System.out.println("Please try After this membership Expires or contact trainier ");
			}
		}

	}
}
