package gym;

import java.util.Scanner;

public class Main {
		public static void main(String[] args) {
			System.out.println("--------------------------------------------------");
			System.out.println("             Gym Management System");
			System.out.println("--------------------------------------------------");
			Gym gym =new Gym("Gym1");
			//Configuring Gym plans
			gym.addPlan("Basic",1,1000);
			gym.addPlan("Premium",3,2000);
			gym.addPlan("Gold",12,6000);
			
			//Adding some members to the gym 
			gym.addMember("Ravi",25,"Basic",001);
			gym.addMember("Ram",24,"Premium",002);
			gym.addMember("Sam",26, "Gold", 003);
			//gym.showPlans();
			
			System.out.println("Gym name : "+gym.getName());
			Scanner scanValue = new Scanner(System.in);
			while(true)
			{
				System.out.println("Press : \n1 -> To add a member\n2 -> View Member Details\n3 -> Show Gym Plans\n4 -> To change gym plans\n5 -> To renew plan of a member\n6 -> To remove a member\n 0-> To exit the Application\nEnter your value : ");
				int value=scanValue.nextInt();
				switch(value) {
				case 0:
					scanValue.close();
					System.out.println("Exited the Application.");
					return;
				case 1:
					System.out.println("Enter the member Name : ");
					String name=scanValue.nextLine();
					int age=scanValue.nextInt();
					int id=scanValue.nextInt();
					String plan=scanValue.nextLine();
					gym.addMember(name, age, plan, id);
					break;
				case 2:
					System.out.println("Enter 1 to show detailsof all members in the gym \nEnter 2 to show details of a member by id : \n");
					while(true) {
						int choice=scanValue.nextInt();
						if(choice==1) {
							
							break;
						}else if(choice==2) {
							
							break;
						}else {
							System.out.println("Invalid choice selected.Select the choice again : ");
							
						}
					 }
					
				 case 3:
					 gym.showPlans();
					 break;
				 case 4:
					 break;
					
				 default:
					 System.out.println("Invalid Value selected. Please select valid value.");
					
					
					
					
					
				}
				
				
				
			
			
			
			}
			
		}
	

}

