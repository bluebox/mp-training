package com.gym.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.gym.classes.Member;
import com.gym.service.Gym;

public class Main {
	public static void main(String[] args) {
		Gym gym = new Gym();
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		
		while(!quit) {
			System.out.println("Welcome to the gym , entered your desired option");
			System.out.println("1.View Gym Members \n2.Add new member\n3.Assign membership plan to member.\n4.Remove Member \nq. Quit");
			
			String input = sc.next();
			String name;
			
			switch(input) {
				//View Members
				case "1":
					printAllMembers(gym.showAllMembers());
					break;
					
				//Add new member
				case "2":
					boolean repeat = false;
					
					//Take in valid name , check with regular expression
					do {
						if(repeat) {
							System.out.println("Name cannot be only numbers, Please enter again");
						}
						System.out.println("Enter name here: ");
						name = sc.next();

						repeat = true;
						
					}while(name.matches("\\d+"));
					
					//loop and take in the age, weight , height until its valid
					try{
						int age;
						while (true) {
						    try {
						        System.out.println("Enter age (12 - 120):");
						        age = sc.nextInt();
						        if (age >= 12 && age <= 120) break;
						        else System.out.println("Age must be between 12 and 120.");
						    } catch (InputMismatchException e) {
						        System.out.println("NOT VALID! Please enter a number.");
						        sc.nextLine(); 
						    }
						}

						int weight;
						while (true) {
						    try {
						        System.out.println("Enter weight in kgs (20-200) :");
						        weight = sc.nextInt();
						        if (weight >= 20 && weight<= 200) break;
						        else System.out.println("Weigth must be between 20 and 200.");
						    } catch (InputMismatchException e) {
						        System.out.println("NOT VALID! Please enter a number.");
						        sc.nextLine();
						    }
						}
						
						
						int height;

						while (true) {
						    try {
						        System.out.println("Enter height in cms (100-250) :");
						        height = sc.nextInt();
						        if (height>= 100 && height<= 250) break;
						        else System.out.println("Height must be between 100 and 250.");
						    } catch (InputMismatchException e) {
						        System.out.println("NOT VALID! Please enter a number.");
						        sc.nextLine(); 
						    }
						}
						
						gym.addNewMember(name,age,height,weight);
					}
					catch(InputMismatchException e) {
						System.out.println("NOT VALID! Please enter a number\n");
						sc.nextLine();
					}			
					break;
				
				
				//Assign membership to member
				case "3":
					printAllMembers(gym.showAllMembers());
					int memId=0;
					int planId=0;
					String date="";
					
					//take member id and store
					while(true) {
						try {
							System.out.println("Enter the Id of the member you want to assign membership");
							memId = sc.nextInt();
							if(memId > 0) {
								break;
							}
						}
						catch(Exception e) {
							System.out.print("Not a valid plan memberID!");
							sc.nextLine();
						}
					}

					//Take joining date and store
					boolean dateRepeat=false;
					do{
						if(dateRepeat){
							System.out.println("Please enter valid date in dd-mm-yyyy or dd/mm/yyyy format");
						}
						System.out.println("Enter joining date here: ");
						date = sc.next();
						dateRepeat = true;
						
						
						
					}while(!date.matches("^(0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[0-2])[-/](\\d{4})$"));
					
					//take plan id and call assignPlanToMember ( handle both input exception and user generated exceptions)
					
					boolean isAssignDone=false;
					while(true) {
						try {
							System.out.println("Enter the id Plan you want to assign\n");
							gym.displayPlans();
							planId = sc.nextInt();
							
							if(planId != 0) {
								gym.assignPlanToMember(memId,planId,date);
								
								break;
							}
						}catch(Exception e) {
							System.out.println(e);
							System.out.print("Not a valid plan ID!");
							sc.nextLine();
						}
					}
					break;
				
				//Remove member
				case "4":
					int memberId=0;
					
					while(true) {
						try {
							System.out.println("Enter the member id you want to delete\n");
							gym.showAllMembers();
							memberId = sc.nextInt();
							gym.deleteMember(memberId);
							break;
						}catch(Exception e) {
							System.out.print("Not a valid member ID!");
							sc.nextLine();
						}
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
	
	static void printAllMembers(List<Member> members) {
		if(members.isEmpty()) {
    		System.out.println("\nno members exist in the database\n");
    		return;
    	}
        System.out.println("\n=========================== Current Members =============================");
        for (Member member : members) {
        	member.showDetails();
        }
        System.out.println("=========================================================================\n");

	}
}
