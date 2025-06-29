package com.gym.driver;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.gym.classes.Gym;

public class Main {
	public static void main(String[] args) {
		Gym gym = new Gym();
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		
		while(true) {
			System.out.println("Welcome to the gym , entered your desired option");
			System.out.println("1.View Gym Members \n2.Add new member\n3.Assign membership plan to member. \nq. Quit");
			String input = sc.next();
			
			switch(input) {
				case "1":
					gym.showAllMembers();
					break;
				case "2":
					
					System.out.println("enter name : ");
					String name = sc.next();
					try {
						
						int age;
						do {
							System.out.println("enter age (12 - 120): ");
							age =  sc.nextInt();
						}while(age<12 || age > 120);
						
						
						
						int weight ;
						do {
							System.out.println("enter weight in kgs (20-200) : ");
							weight = sc.nextInt();
						}while(weight < 20 || weight > 200);
						
						
						
						int height;
						do {
							System.out.println("enter height in cms (100-250): ");
							height = sc.nextInt();
						}while(height < 100 || height > 250);
						
						gym.addNewMember(name,age,height,weight);
						
						
					}
					catch(InputMismatchException e) {
						System.out.println("NOT VALID! Please enter a number\n");
						sc.nextLine();
						
					}

									
					break;
				case "3":
					try {
						gym.showAllMembers();
						System.out.println("Enter the Id of the member you want to assign membership");
						int memId = sc.nextInt();
						gym.assignPlanToMember(memId);
					}
					catch(InputMismatchException e) {
						System.out.println("NOT VALID! Please enter a number\n");
						sc.nextLine();
					}
					break;
				case "q":
					quit = true;
					break;
				default:
					System.out.println("Not a valid option\n");
					break;
			}
			
			if(quit) break;
		}
		
	}
}
