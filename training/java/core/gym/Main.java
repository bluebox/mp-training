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
			//Menu Based User Interaction
			while(true)
			{
				System.out.println("\nPress : \n1 -> To add a member\n2 -> View All Members Details\n3 -> To Search Member Details by Id\n4 -> Show Gym Plans\n5 -> Bonus Feature(Not Yet added)\n6 -> To remove a member\n0 -> To exit the Application\nEnter your value : ");
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
						System.out.println("Enter The Id of the Member : ");
						int mid=scanValue.nextInt();
						gym.listMemberDetailsById(mid);
						break;
						
					case 4:
						gym.showPlans();
						break;

					case 5:
						break;
						
					case 6:
						System.out.println("Enter the Name of the the Member : ");
						String memberName = scanValue.next();
						if(gym.removeMember(memberName)){
							System.out.println("Successfully removed the "+memberName+" from the gym");
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
	

}
