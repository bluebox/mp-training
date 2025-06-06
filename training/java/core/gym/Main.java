package gym;

import java.util.Scanner;

public class Main {
		public static void main(String[] args) {
			System.out.println("--------------------------------------------------------");
			System.out.println("                Gym Management System                   ");
			System.out.println("--------------------------------------------------------");
			
			Gym gym =new Gym("Gym1"); 
			
			//Configuring Gym plans
			gym.addPlan("Basic",1,1000);
			gym.addPlan("Premium",3,2000);
			gym.addPlan("Gold",12,6000);
			
			//Adding some members to the gym 
			gym.addMember("Ravi",25,"Basic",001);
			gym.addMember("Ram",24,"Premium",002);
			gym.addMember("Sam",26, "Gold", 003);
			
			System.out.println("Gym name : "+gym.getName());
			
			Scanner scanValue = new Scanner(System.in);
			
			//Menu Based User Interaction
			while(true)
			{
				printMenu();
				
				int value=scanValue.nextInt();
				switch(value) {
				
					case 0:
						scanValue.close();
						System.out.print("Exiting");
						animateLoading(7);
						
						System.out.println("Exited the Application.");
						return;
						
					case 1:
						//taking user details
						System.out.println("Enter the member Name : ");
						String name=scanValue.next();
						System.out.println("Enter the member Age: ");
						int age=scanValue.nextInt();
						System.out.println("Enter the member Id : ");
						int id=scanValue.nextInt();
						System.out.println("Enter the plan chosen by member : ");
						String plan=scanValue.next();
						
						System.out.print("Adding Member Details");
						animateLoading(21);
						
						gym.addMember(name, age, plan, id);
						
						System.out.println(name+" Added Successfully.");
						wait(1500);
						
						break;
						
					case 2:				
						gym.listAllMembersDetails();
						wait(1500);
						break;
						
					case 3:
						System.out.println("Enter The Id of the Member : ");
						int memberId=scanValue.nextInt();
						System.out.print("Fetching Member details");
						animateLoading(22);
						
						gym.listMemberDetailsById(memberId);
						wait(1000);
						break;
						
					case 4:
						gym.showPlans();
						wait(1500);
						break;

					case 5:
						System.out.println("Enter the Name of the new Plan : ");
						String planName= scanValue.next();
						System.out.println("Enter the duration: ");
						int duration=scanValue.nextInt();
						System.out.println("Enter the fee : ");
						int fee=scanValue.nextInt();
						gym.addPlan(planName,duration,fee);
						System.out.println("Plan Added Successfulyt.");
						break;
						
					case 6:
						System.out.println("Enter the Name of the Member : ");
						String memberName = scanValue.next();
						if(gym.removeMember(memberName)){
							System.out.print("Removing the Member");
							animateLoading(19);
							System.out.println("Successfully removed the "+memberName+" from the gym.");
							wait(1500);
							}
						else {
							System.out.println(memberName+" doesn't exist in the gym.");
							}
						
						break;
						
					default:
						System.out.println("Invalid value selected. Please select valid value.");
						
				}
				
			}
			
		}
		
		public static void animateLoading(int n) {
			for(int i=0;i<4;i++) {
			System.out.print(".");
			wait(400);
			}
			System.out.print("\b".repeat(n+4));
		}
		
		public static void printMenu() {
			wait(1000);
			System.out.println("\nPress : \n"
					+ "1 -> To add a member\n"
					+ "2 -> View All Members Details\n"
					+ "3 -> To Search Member Details by Id\n"
					+ "4 -> Show Gym Plans\n"
					+ "5 -> To Add a new Gym Plan\n"
					+ "6 -> To remove a member\n"
					+ "0 -> To exit the Application\n"
					+ "Enter your value : ");
		}
		public static void wait(int ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	

}
