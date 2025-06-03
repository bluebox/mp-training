package gym;

import java.util.Scanner;

public class Main {
		public static void main(String[] args) {
			System.out.println("--------------------------------------------------------");
			System.out.println("                Gym Management System                   ");
			System.out.println("--------------------------------------------------------");
			
			Gym gym =new Gym("Gym1"); 
			
			//Configuring Gym plans
			gym.addPlan("Basic  ",1,1000);
			gym.addPlan("Premium",3,2000);
			gym.addPlan("Gold   ",12,6000);
			
			//Adding some members to the gym 
			gym.addMember("Ravi",25,"Basic",001);
			gym.addMember("Ram",24,"Premium",002);
			gym.addMember("Sam",26, "Gold", 003);
			
			System.out.println("Gym name : "+gym.getName());
			Scanner scanValue = new Scanner(System.in);
			while(true)
			{
				System.out.println("\nPress : \n1 -> To add a member\n2 -> View Member Details\n3 -> Show Gym Plans\n4 -> To change gym plans\n5 -> To renew plan of a member\n6 -> To remove a member\n0 -> To exit the Application\nEnter your value : ");
				int value=scanValue.nextInt();
				switch(value) {
					case 0:
						scanValue.close();
						System.out.println("Exited the Application.");
						return;
					case 1:
						System.out.println("Enter the member Name : ");
						String name=scanValue.next();
						System.out.println("Enter the member Age: ");
						int age=scanValue.nextInt();
						System.out.println("Enter the member Id : ");
						int id=scanValue.nextInt();
						System.out.println("Enter the plan chosen by member : ");
						String plan=scanValue.next();
						gym.addMember(name, age, plan, id);
						break;
					case 2:				
						gym.listMemberDetails();
						break;
						
					case 3:
						gym.showPlans();
						break;
					case 4:
						break;
						
					default:
						System.out.println("Invalid value selected. Please select valid value.");
						
				}
				
			}
			
		}
	

}
