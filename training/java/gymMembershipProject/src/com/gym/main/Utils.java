package com.gym.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.gym.service.Gym;

public class Utils {
	
	static Scanner sc;
	static Gym gym;
	
	static {
		gym = new Gym();
		 sc = new Scanner(System.in);
	}

	public static String takeNameInput() {				
		boolean repeat = false;
		String name;

		do {
			if(repeat) {
				System.out.println("Name cannot be only numbers, Please enter again");
			}
			System.out.println("Enter name here: ");
			name = sc.next();

			repeat = true;
			
		}while(name.matches("\\d+"));
		
		return name;
	}
	
	public static int takeAgeInput() {
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
		return age;
	}
	
	public static int takeWeightInput() {
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
		
		return weight;
	}
	
	public static int takeHeightInput() {
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
		return height;
	}
	
	//---------------------------------------------------------------------
	
	public static int takeMemberIdInput() {
		int memId;
		
		while (true) {
			try {
				System.out.println("Enter the Id of the member you want to assign membership");
				memId = sc.nextInt();
				if (memId > 0) {
					break;
				}
			} catch(InputMismatchException e) {
				System.out.print("Not a valid plan memberID!");
				sc.nextLine();
			}
		}
		return memId;
	}
	
	public static String takeDateInput() {
		String date;
		boolean dateRepeat = false;
		do {
			if (dateRepeat) {
				System.out.println("Please enter valid date in dd-mm-yyyy or dd/mm/yyyy format");
			}
			System.out.println("Enter joining date here: ");
			date = sc.next();
			dateRepeat = true;

		} while (!date.matches("^(0[1-9]|[12][0-9]|3[01])[-/](0[1-9]|1[0-2])[-/](\\d{4})$"));
		return date;
	}
	
	public static int takePlanIdInput() {
		
		int planId;
		while (true) {
			try {
				System.out.println("Enter the id Plan you want to assign\n");
				gym.displayPlans();
				planId = sc.nextInt();

				if (planId > 0 && planId <= 3) {
					break;
				}else {
					System.out.println("Please enter a number between 1-3\n");
				}
			} catch (InputMismatchException e) {
				System.out.print("Not a valid plan ID!");
				sc.nextLine();
			}
		}
		return planId;
	}
	
	public static int takeMemberIdForDeleteInput() {
		int memberId;
		while (true) {
			try {
				System.out.println("Enter the member id you want to delete\n");
				gym.showAllMembers();
				memberId = sc.nextInt();
				break;
			} catch (Exception e) {
				System.out.print("Not a valid member ID!");
				sc.nextLine();
			}
		}
		return memberId;

	}
}

