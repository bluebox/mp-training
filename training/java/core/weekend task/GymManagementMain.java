package GymManagement;

import java.util.Scanner;

public class GymManagementMain {
public static void main(String []args)
{
	Gym gym=new Gym();
	Scanner sc=new Scanner(System.in);
	int select;
	System.out.println("Welcome To Gym");
	do {
		System.out.println("1. Add New Gym Member");
		System.out.println("2. Assign membership plan to a member");
		System.out.println("3. View All Registred Members and Their Details");
		System.out.println("4. View Membership Plans");
		System.out.println("5. Exit the System");
		try {
			select=sc.nextInt();
			sc.nextLine();
			switch(select)
			{
			case 1:System.out.print("Enter member name: ");
			       String name=sc.nextLine();
			       System.out.print("enter member age: ");
			       int age=sc.nextInt();
			       sc.nextLine();
			       gym.addMember(name,age);
			       break;
			       
			case 2:System.out.println("Enter Member Id to assign a plan :");
			       int memberId=sc.nextInt();
				   System.out.print("Enter plan name to assign (Basic, Premium, Gold): ");
				   sc.nextLine();
				   String planName=sc.nextLine();
			       gym.assignPlanToMember(memberId,planName);
			       break;
			       
			case 3:gym.viewAllMembers();
			       break;
			
			case 4:gym.viewAllPlans();
			       break;
			          
			case 5:System.out.println("Exiting the system thankyou");
			       break;
			       
			default:System.out.print("Invalid choice please enter btwn 1 to 5");;        
			       
			}
		}catch(java.util.InputMismatchException e){
			System.err.println("Invalid input please enter a number:");
			sc.nextLine();
			select=0;
			
		}
		
	}while(select!=5);
	sc.close();
	
	
}
}
